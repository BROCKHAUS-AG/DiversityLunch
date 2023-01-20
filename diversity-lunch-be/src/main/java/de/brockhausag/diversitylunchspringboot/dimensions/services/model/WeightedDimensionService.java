package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import com.google.common.collect.Lists;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.*;
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
            return false;
        }

        boolean result = false;
        WeightedDimensionSelectableOption option = selectableOptionOptional.get();
        WeightedDimensionSelectableOption defaultOption = getDefaultOption(option);

        if (option != defaultOption) {
            resetProfileOption(option, defaultOption);
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
        var dimension = repository.findByDimensionCategory_Description(categoryDescription);
        if (dimension.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(dimension.get().getDimensionCategory());
    }

    @Override
    public Optional<WeightedDimensionSelectableOption> getSelectableOptionById(Long selectableOptionId) {
        return selectableRepository.findById(selectableOptionId);
    }

    /* Returns the default option of the same category as the given option.
     * Requires existing selectable Option.
     * */
    private WeightedDimensionSelectableOption getDefaultOption(WeightedDimensionSelectableOption selectableOption) {
        WeightedDimension dimension = getDimension(selectableOption.getDimensionCategory()).get();
        return dimension.getDefaultValue();
    }

    private void resetProfileOption(WeightedDimensionSelectableOption currentOption, WeightedDimensionSelectableOption targetOption) {
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
