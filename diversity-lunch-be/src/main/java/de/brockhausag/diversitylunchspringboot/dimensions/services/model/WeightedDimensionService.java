package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.WeightedDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.WeightedDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.DimensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WeightedDimensionService implements DimensionService<WeightedDimension, WeightedDimensionSelectableOption> {

    private final BasicDimensionRepository repository;
    private final BasicDimensionSelectableOptionRepository selectableRepository;

    public BasicDimension getDimension(DimensionCategory category) {
        return repository.getByDimensionCategory(category);
    }

    public List<BasicDimension> getAllDimensions() {

        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public BasicDimension getDimension(String categoryDescription) {
        return repository.getByDimensionCategory_Description(categoryDescription);
    }

    @Override
    public BasicDimensionSelectableOption addSelectableOption(BasicDimensionSelectableOption option) {
        return selectableRepository.save(option);
    }

    @Override
    public BasicDimensionSelectableOption updateSelectableOption(BasicDimensionSelectableOption option) {
        return selectableRepository.save(option);
    }

    @Override
    public void deleteSelectableOptionById(Long selectableOptionId) {
        selectableRepository.deleteById(selectableOptionId);
    }

    @Override
    public List<BasicDimensionSelectableOption> getSelectableOptionsOfCategory(Long categoryId) {
        return selectableRepository.getByDimensionCategory_Id(categoryId);
    }

    @Override
    public Long getDimensionCategoryIdByDescription(String categoryDescription) {
        var dimension = repository.getByDimensionCategory_Description(categoryDescription);
        return dimension.getId();
    }

    @Override
    public BasicDimensionSelectableOption getSelectableOptionById(Long selectableOptionId) {
        return selectableRepository.getById(selectableOptionId);
    }

}
