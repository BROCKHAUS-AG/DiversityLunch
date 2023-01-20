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
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BasicDimensionService implements DimensionService<
        BasicDimension,
        BasicDimensionSelectableOption> {


    private final BasicDimensionRepository repository;
    private final BasicDimensionSelectableOptionRepository selectableRepository;
    private final ProfileService profileService;

    public Optional<BasicDimension> getDimension(DimensionCategory category) {
        return repository.findByDimensionCategory(category);
    }

    @Override
    public List<BasicDimension> getAllDimensions() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public Optional<BasicDimension> getDimension(String categoryDescription) {
        return repository.findByDimensionCategory_Description(categoryDescription);
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
        return selectableRepository.getByDimensionCategory(dimension.getDimensionCategory());
    }

    @Override
    public Optional<BasicDimensionSelectableOption> getSelectableOption(BasicDimension dimension, String optionName) {
        return selectableRepository.findByDimensionCategoryAndValue(dimension.getDimensionCategory(), optionName);
    }

    @Override
    public boolean deleteSelectableOptionById(Long selectableOptionId) {
        var selectableOptionOptional = selectableRepository.findById(selectableOptionId);
        if (selectableOptionOptional.isEmpty()) {
            return false;
        }

        boolean result = false;
        BasicDimensionSelectableOption option = selectableOptionOptional.get();
        BasicDimensionSelectableOption defaultOption = getDefaultOption(option);

        if (option != defaultOption) {
            resetProfileOption(option, defaultOption);
            result = deleteOption(option);
        }
        return result;
    }

    @Override
    public List<BasicDimensionSelectableOption> getSelectableOptionsByCategory(DimensionCategory category) {
        return selectableRepository.getByDimensionCategory(category);
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
    public Optional<BasicDimensionSelectableOption> getSelectableOptionById(Long selectableOptionId) {
        return selectableRepository.findById(selectableOptionId);
    }

    /* Returns the default option of the same category as the given option.
     * Requires existing selectable Option.
     * */
    private BasicDimensionSelectableOption getDefaultOption(BasicDimensionSelectableOption selectableOption) {
        BasicDimension dimension = getDimension(selectableOption.getDimensionCategory()).get();
        return dimension.getDefaultValue();
    }

    private void resetProfileOption(BasicDimensionSelectableOption currentOption, BasicDimensionSelectableOption targetOption) {
        BasicDimension dimension = getDimension(currentOption.getDimensionCategory()).get();
        List<ProfileEntity> affectedProfiles = profileService.getAllProfilesWithSelectedBasicOption(currentOption);
        affectedProfiles.forEach(profile -> {
            profile.getSelectedBasicValues().replace(dimension, targetOption);
            profile.setWasChangedByAdmin(true);
            profileService.updateProfile(profile);
        });
    }

    private boolean deleteOption(BasicDimensionSelectableOption selectableOption) {
        selectableRepository.deleteById(selectableOption.getId());
        return !selectableRepository.existsById(selectableOption.getId());
    }
}
