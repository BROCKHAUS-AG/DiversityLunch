package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import com.google.common.collect.Lists;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.WeightedDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.WeightedDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.DimensionService;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WeightedDimensionService implements DimensionService<WeightedDimension, WeightedDimensionSelectableOption> {

    private final WeightedDimensionRepository repository;
    private final WeightedDimensionSelectableOptionRepository selectableRepository;
    private final ProfileService profileService;

    public WeightedDimension getDimension(DimensionCategory category) {
        return repository.getByDimensionCategory(category);
    }

    @Override
    public List<WeightedDimension> getAllDimensions() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public WeightedDimension getDimension(String categoryDescription) {
        return repository.getByDimensionCategory_Description(categoryDescription);
    }

    @Override
    public boolean addSelectableOption(WeightedDimensionSelectableOption option) {
        try {
            selectableRepository.save(option);
            return true;
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            return false;
        }
    }

    @Override
    public boolean updateSelectableOption(WeightedDimensionSelectableOption option) {
        if (selectableRepository.findById(option.getId()).isEmpty()) {
            return false;
        } else {
            selectableRepository.save(option);
            return true;
        }
    }

    @Override
    public List<WeightedDimensionSelectableOption> getSelectableOptions(WeightedDimension dimension) {
        return selectableRepository.getByDimensionCategory_Id(dimension.getDimensionCategory().getId());
    }

    @Override
    public WeightedDimensionSelectableOption getSelectableOption(WeightedDimension dimension, String optionName) {
        return selectableRepository.getByDimensionCategory_IdAndValue(dimension.getDimensionCategory().getId(), optionName);
    }

    @Override
    public boolean deleteSelectableOptionById(Long selectableOptionId) {
        if (!selectableRepository.existsById(selectableOptionId)) {
            return false;
        }
        WeightedDimensionSelectableOption option = selectableRepository.getById(selectableOptionId);
        WeightedDimension dimension = getDimension(option.getDimensionCategory());
        WeightedDimensionSelectableOption defaultOption = dimension.getDefaultValue();
        if (option == defaultOption) {
            return false;
        }
        List<ProfileEntity> affectedProfiles = profileService.getAllProfilesWithSelectedWeightedOption(option);
        affectedProfiles.forEach(profile -> {
            profile.getSelectedWeightedValues().replace(dimension, defaultOption);
            profile.setWasChangedByAdmin(true);
            profileService.updateProfile(profile);
        });
        selectableRepository.deleteById(selectableOptionId);
        return !selectableRepository.existsById(selectableOptionId);
    }

    @Override
    public List<WeightedDimensionSelectableOption> getSelectableOptionsOfCategory(Long categoryId) {
        return selectableRepository.getByDimensionCategory_Id(categoryId);
    }

    @Override
    public Long getDimensionCategoryIdByDescription(String categoryDescription) {
        var dimension = repository.getByDimensionCategory_Description(categoryDescription);
        return dimension.getDimensionCategory().getId();
    }

    @Override
    public WeightedDimensionSelectableOption getSelectableOptionById(Long selectableOptionId) {
        return selectableRepository.getById(selectableOptionId);
    }

}
