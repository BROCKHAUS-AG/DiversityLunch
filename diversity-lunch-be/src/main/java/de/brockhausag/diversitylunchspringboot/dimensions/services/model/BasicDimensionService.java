package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import com.google.common.collect.Lists;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.DimensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BasicDimensionService implements DimensionService<
        BasicDimension,
        BasicDimensionSelectableOption> {


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
    public List<BasicDimensionSelectableOption> getSelectableOptions(BasicDimension dimension) {
        return selectableRepository.getByDimensionCategory_Id(dimension.getDimensionCategory().getId());
    }

    @Override
    public BasicDimensionSelectableOption getSelectableOption(BasicDimension dimension, String optionName) {
        return selectableRepository.getByDimensionCategory_IdAndValue(dimension.getDimensionCategory().getId(), optionName);
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
        return dimension.getDimensionCategory().getId();
    }

    @Override
    public BasicDimensionSelectableOption getSelectableOptionById(Long selectableOptionId) {
        return selectableRepository.getById(selectableOptionId);
    }
}
