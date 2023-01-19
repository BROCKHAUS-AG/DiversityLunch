package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import com.google.common.collect.Lists;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
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
public class BasicDimensionService implements DimensionService<
        BasicDimension,
        BasicDimensionSelectableOption> {


    private final BasicDimensionRepository repository;
    private final BasicDimensionSelectableOptionRepository selectableRepository;
    private final ProfileService profileService;

    public BasicDimension getDimension(DimensionCategory category) {
        return repository.getByDimensionCategory(category);
    }

    @Override
    public List<BasicDimension> getAllDimensions() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public BasicDimension getDimension(String categoryDescription) {
        return repository.getByDimensionCategory_Description(categoryDescription);
    }

    @Override
    public boolean addSelectableOption(BasicDimensionSelectableOption option) {
        try {
            selectableRepository.save(option);
            return true;
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            return false;
        }
    }

    @Override
    public boolean updateSelectableOption(BasicDimensionSelectableOption option) {
        if (selectableRepository.findById(option.getId()).isEmpty()) {
            return false;
        } else {
            selectableRepository.save(option);
            return true;
        }
    }

    @Override
    public List<BasicDimensionSelectableOption> getSelectableOptions(BasicDimension dimension) {
        return selectableRepository.getByDimensionCategory_Id(dimension.getDimensionCategory().getId());
    }

    @Override
    public BasicDimensionSelectableOption getSelectableOption(BasicDimension dimension, String optionName) {
        return selectableRepository.getByDimensionCategory_IdAndValue(dimension.getDimensionCategory().getId(), optionName);
    }

    @Override
    public boolean deleteSelectableOptionById(Long selectableOptionId) {
        if (!selectableRepository.existsById(selectableOptionId)) {
            return false;
        }
        BasicDimensionSelectableOption option = selectableRepository.getById(selectableOptionId);
        BasicDimension dimension = getDimension(option.getDimensionCategory());
        BasicDimensionSelectableOption defaultOption = dimension.getDefaultValue();
        if (option == defaultOption) {
            return false;
        }
        List<ProfileEntity> affectedProfiles = profileService.getAllProfilesWithSelectedBasicOption(option);
        affectedProfiles.forEach(profile -> {
            profile.getSelectedBasicValues().replace(dimension, defaultOption);
            profile.setWasChangedByAdmin(true);
            profileService.updateProfile(profile);
        });
        selectableRepository.deleteById(selectableOptionId);
        return !selectableRepository.existsById(selectableOptionId);
    }

    @Override
    public List<BasicDimensionSelectableOption> getSelectableOptionsOfCategory(Long categoryId) {
        return selectableRepository.getByDimensionCategory_Id(categoryId);
    }

    @Override
    public Long getDimensionCategoryIdByDescription(String categoryDescription) {
        var dimension = repository.getByDimensionCategory_Description(categoryDescription);
        return dimension.getDimensionCategory().getId();
    }

    @Override
    public BasicDimensionSelectableOption getSelectableOptionById(Long selectableOptionId) {
        return selectableRepository.getById(selectableOptionId);
    }
}
