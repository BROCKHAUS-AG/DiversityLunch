package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import com.google.common.collect.Lists;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.MultiselectDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.MultiselectDimensionSelectableOptionRepository;
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
public class MultiselectDimensionService implements DimensionService<MultiselectDimension, MultiselectDimensionSelectableOption> {
    private final MultiselectDimensionRepository repository;
    private final MultiselectDimensionSelectableOptionRepository selectableRepository;
    private final ProfileService profileService;

    @Override
    public MultiselectDimension getDimension(String categoryDescription) {
        return repository.getByDimensionCategory_Description(categoryDescription);
    }

    @Override
    public MultiselectDimension getDimension(DimensionCategory category) {
        return repository.getByDimensionCategory(category);
    }

    @Override
    public List<MultiselectDimension> getAllDimensions() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public boolean addSelectableOption(MultiselectDimensionSelectableOption option) {
        try {
            selectableRepository.save(option);
            return true;
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            return false;
        }
    }

    @Override
    public boolean deleteSelectableOptionById(Long selectableOptionId) {
        if (!selectableRepository.existsById(selectableOptionId)) {
            return false;
        }
        MultiselectDimensionSelectableOption option = selectableRepository.getById(selectableOptionId);
        MultiselectDimension dimension = getDimension(option.getDimensionCategory());
        List<ProfileEntity> affectedProfiles = profileService.getAllProfilesWithSelectedMultiselectOption(option);
        affectedProfiles.forEach(profile -> {
            profile.getSelectedMultiselectValues().get(dimension).getSelectedOptions().remove(option);
            profile.setWasChangedByAdmin(true);
            profileService.updateProfile(profile);
        });
        selectableRepository.deleteById(selectableOptionId);
        return !selectableRepository.existsById(selectableOptionId);
    }

    @Override
    public boolean updateSelectableOption(MultiselectDimensionSelectableOption option) {
        try {
            if (selectableRepository.findById(option.getId()).isEmpty()) {
                return false;
            } else {
                selectableRepository.save(option);
                return true;
            }
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            return false;
        }
    }

    @Override
    public List<MultiselectDimensionSelectableOption> getSelectableOptions(MultiselectDimension dimension) {
        return selectableRepository.getByDimensionCategory_Id(dimension.getDimensionCategory().getId());
    }

    @Override
    public MultiselectDimensionSelectableOption getSelectableOption(MultiselectDimension dimension, String optionName) {
        return selectableRepository.getByDimensionCategory_IdAndValue(dimension.getDimensionCategory().getId(), optionName);
    }

    @Override
    public List<MultiselectDimensionSelectableOption> getSelectableOptionsOfCategory(Long categoryId) {
        return selectableRepository.getByDimensionCategory_Id(categoryId);
    }

    @Override
    public Long getDimensionCategoryIdByDescription(String categoryDescription) {

        MultiselectDimension dimension =  repository.getByDimensionCategory_Description(categoryDescription);
        return dimension.getDimensionCategory().getId();
    }

    @Override
    public MultiselectDimensionSelectableOption getSelectableOptionById(Long selectableOptionId) {
        return selectableRepository.getById(selectableOptionId);
    }

    public List<MultiselectDimensionSelectableOption> getSelectableOptions(List<Long> ids) {
       return Lists.newArrayList(selectableRepository.findAllById(ids));
    }
}
