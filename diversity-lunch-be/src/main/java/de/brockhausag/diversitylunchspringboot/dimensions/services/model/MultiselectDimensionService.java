package de.brockhausag.diversitylunchspringboot.dimensions.services.model;

import com.google.common.collect.Lists;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
    public List<MultiselectDimension> getAllDimensions() {
        return Lists.newArrayList(repository.findAll());
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
