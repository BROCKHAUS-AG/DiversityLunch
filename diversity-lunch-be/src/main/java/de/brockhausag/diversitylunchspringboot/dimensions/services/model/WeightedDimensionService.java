package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import com.google.common.collect.Lists;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.*;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.WeightedDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.WeightedDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.DimensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WeightedDimensionService implements DimensionService<WeightedDimension, WeightedDimensionSelectableOption> {

    private final WeightedDimensionRepository repository;
    private final WeightedDimensionSelectableOptionRepository selectableRepository;

    public WeightedDimension getDimension(DimensionCategory category) {
        return repository.getByDimensionCategory(category);
    }

    public List<WeightedDimension> getAllDimensions() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public WeightedDimension getDimension(String categoryDescription) {
        return repository.getByDimensionCategory_Description(categoryDescription);
    }

    @Override
    public WeightedDimensionSelectableOption addSelectableOption(WeightedDimensionSelectableOption option) {
        return selectableRepository.save(option);
    }

    @Override
    public WeightedDimensionSelectableOption updateSelectableOption(WeightedDimensionSelectableOption option) {
        return selectableRepository.save(option);
    }

    @Override
    public void deleteSelectableOptionById(Long selectableOptionId) {
        selectableRepository.deleteById(selectableOptionId);
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
