package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.*;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.WeightedDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.WeightedDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.DimensionService;
import de.brockhausag.diversitylunchspringboot.profile.services.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WeightedDimensionService implements DimensionService<WeightedDimension, WeightedDimensionSelectableOption> {

    private final WeightedDimensionRepository repository;
    private final WeightedDimensionSelectableOptionRepository selectableRepository;
    private final ProfileService profileService;

    public Optional<WeightedDimension> getDimension(DimensionCategory category) {
        return repository.findByDimensionCategory(category);
    }

    @Override
    public List<WeightedDimension> getAllDimensions() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public Optional<WeightedDimension> getDimension(String categoryDescription) {
        return repository.findByDimensionCategory_Description(categoryDescription);
    }

    @Override
    public boolean addSelectableOption(WeightedDimensionSelectableOption option) {
        boolean saved = false;
        try {
            if (!selectableRepository.existsById(option.getId())) {
                selectableRepository.save(option);
                saved = true;
            }
        } catch (DataIntegrityViolationException | ConstraintViolationException ignored) {
        }
        return saved;
    }

    @Override
    public boolean updateSelectableOption(WeightedDimensionSelectableOption option) {
        boolean updated = false;
        try {
            if (selectableRepository.existsById(option.getId())) {
                selectableRepository.save(option);
                updated = true;
            }
        } catch (DataIntegrityViolationException | ConstraintViolationException ignored) {
        }
        return updated;
    }

    @Override
    public List<WeightedDimensionSelectableOption> getSelectableOptions(WeightedDimension dimension) {
        return selectableRepository.getByDimensionCategory(dimension.getDimensionCategory());
    }

    @Override
    public Optional<WeightedDimensionSelectableOption> getSelectableOption(WeightedDimension dimension, String optionName) {
        return selectableRepository.findByDimensionCategoryAndValue(dimension.getDimensionCategory(), optionName);
    }

    @Override
    public boolean deleteSelectableOptionById(Long selectableOptionId) {
        var selectableOptionOptional = selectableRepository.findById(selectableOptionId);
        if (selectableOptionOptional.isEmpty()) {
            return true;
        }

        boolean result = false;
        WeightedDimensionSelectableOption option = selectableOptionOptional.get();
        WeightedDimensionSelectableOption defaultOption = getDefaultOptionOfSameCategory(option);

        if (option != defaultOption) {
            replaceSelectedOptionInProfiles(option, defaultOption);
            result = deleteOption(option);
        }
        return result;
    }

    @Override
    public List<WeightedDimensionSelectableOption> getSelectableOptionsByCategory(DimensionCategory categoryId) {
        return selectableRepository.getByDimensionCategory(categoryId);
    }

    @Override
    public Optional<DimensionCategory> getDimensionCategoryByDescription(String categoryDescription) {
        Optional<WeightedDimension> dimensionOptional = repository.findByDimensionCategory_Description(categoryDescription);
        return dimensionOptional.map(WeightedDimension::getDimensionCategory);
    }

    @Override
    public Optional<WeightedDimensionSelectableOption> getSelectableOptionById(Long selectableOptionId) {
        return selectableRepository.findById(selectableOptionId);
    }

    /** Returns the default option of the same category as the given option.
      * Requires existing selectable Option.
      */
    private WeightedDimensionSelectableOption getDefaultOptionOfSameCategory(WeightedDimensionSelectableOption selectableOption) {
        // selectableOption should have been checked before and must have a category
        WeightedDimension dimension = getDimension(selectableOption.getDimensionCategory()).get();
        return dimension.getDefaultValue();
    }

    private void replaceSelectedOptionInProfiles(WeightedDimensionSelectableOption currentOption, WeightedDimensionSelectableOption targetOption) {
        // selectableOption should have been checked before and must have a category
        WeightedDimension dimension = getDimension(currentOption.getDimensionCategory()).get();
        List<ProfileEntity> affectedProfiles = profileService.getAllProfilesWithSelectedWeightedOption(currentOption);
        affectedProfiles.forEach(profile -> {
            profile.getSelectedWeightedValues().replace(dimension, targetOption);
            profile.setWasChangedByAdmin(true);
            profileService.updateProfile(profile);
        });
    }

    private boolean deleteOption(WeightedDimensionSelectableOption selectableOption) {
        selectableRepository.deleteById(selectableOption.getId());
        return !selectableRepository.existsById(selectableOption.getId());
    }
}
