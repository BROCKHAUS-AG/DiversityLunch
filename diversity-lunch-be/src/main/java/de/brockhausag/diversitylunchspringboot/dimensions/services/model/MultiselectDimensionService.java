package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.MultiselectDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.MultiselectDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.DimensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MultiselectDimensionService implements DimensionService<MultiselectDimension, MultiselectDimensionSelectableOption> {
    private final MultiselectDimensionRepository repository;
    private final MultiselectDimensionSelectableOptionRepository selectableRepository;

    @Override
    public MultiselectDimension getDimension(String categoryDescription) {
        return repository.getByDimensionCategory_Description(categoryDescription);
    }

    @Override
    public MultiselectDimensionSelectableOption addSelectableOption(MultiselectDimensionSelectableOption option) {
        return selectableRepository.save(option);
    }

    @Override
    public void deleteSelectableOptionById(Long selectableOptionId) {
        selectableRepository.deleteById(selectableOptionId);
    }

    @Override
    public MultiselectDimensionSelectableOption updateSelectableOption(MultiselectDimensionSelectableOption option) {
        return selectableRepository.save(option);
    }

    @Override
    public List<MultiselectDimensionSelectableOption> getSelectableOptionsOfCategory(Long categoryId) {
        return repository.findAllById(categoryId);
    }

    @Override
    public Long getDimensionCategoryIdByDescription(String categoryDescription) {
        return null;
    }

    @Override
    public MultiselectDimensionSelectableOption getSelectableOptionById(Long selectableOptionId) {
        return null;
    }
}
