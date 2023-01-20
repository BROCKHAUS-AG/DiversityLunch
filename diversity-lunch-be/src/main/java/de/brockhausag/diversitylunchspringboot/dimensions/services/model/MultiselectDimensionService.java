package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import com.google.common.collect.Lists;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.*;
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
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MultiselectDimensionService implements DimensionService<MultiselectDimension, MultiselectDimensionSelectableOption> {
    private final MultiselectDimensionRepository repository;
    private final MultiselectDimensionSelectableOptionRepository selectableRepository;
    private final ProfileService profileService;

    @Override
    public Optional<MultiselectDimension> getDimension(String categoryDescription) {
        return repository.findByDimensionCategory_Description(categoryDescription);
    }

    @Override
    public Optional<MultiselectDimension> getDimension(DimensionCategory category) {
        return repository.findByDimensionCategory(category);
    }

    @Override
    public List<MultiselectDimension> getAllDimensions() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public boolean addSelectableOption(MultiselectDimensionSelectableOption option) {
        boolean saved = false;
        try {
            if(!selectableRepository.existsById(option.getId())) {
                selectableRepository.save(option);
                saved = true;
            }
        } catch (DataIntegrityViolationException | ConstraintViolationException ignored) {
        }
        return saved;
    }

    @Override
    public boolean deleteSelectableOptionById(Long selectableOptionId) {
        Optional<MultiselectDimensionSelectableOption> selectableOptionOptional = selectableRepository.findById(selectableOptionId);
        if (selectableOptionOptional.isEmpty()) {
            return false;
        }

        MultiselectDimensionSelectableOption option = selectableOptionOptional.get();

        removeOptionInProfile(option);
        return deleteOption(option);
    }

    @Override
    public boolean updateSelectableOption(MultiselectDimensionSelectableOption option) {
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
    public List<MultiselectDimensionSelectableOption> getSelectableOptions(MultiselectDimension dimension) {
        return selectableRepository.getByDimensionCategory(dimension.getDimensionCategory());
    }

    @Override
    public Optional<MultiselectDimensionSelectableOption> getSelectableOption(MultiselectDimension dimension, String optionName) {
        return selectableRepository.findByDimensionCategoryAndValue(dimension.getDimensionCategory(), optionName);
    }

    @Override
    public List<MultiselectDimensionSelectableOption> getSelectableOptionsByCategory(DimensionCategory category) {
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
    public Optional<MultiselectDimensionSelectableOption> getSelectableOptionById(Long selectableOptionId) {
        return selectableRepository.findById(selectableOptionId);
    }

    public List<MultiselectDimensionSelectableOption> getSelectableOptions(List<Long> ids) {
       return Lists.newArrayList(selectableRepository.findAllById(ids));
    }

    private void removeOptionInProfile(MultiselectDimensionSelectableOption selectableOption) {
        MultiselectDimension dimension = getDimension(selectableOption.getDimensionCategory()).get();
        List<ProfileEntity> affectedProfiles = profileService.getAllProfilesWithSelectedMultiselectOption(selectableOption);
        affectedProfiles.forEach(profile -> {
            profile.getSelectedMultiselectValues().get(dimension).getSelectedOptions().remove(selectableOption);
            profile.setWasChangedByAdmin(true);
            profileService.updateProfile(profile);
        });
    }

    private boolean deleteOption(MultiselectDimensionSelectableOption selectableOption) {
        selectableRepository.deleteById(selectableOption.getId());
        return !selectableRepository.existsById(selectableOption.getId());
    }
}
