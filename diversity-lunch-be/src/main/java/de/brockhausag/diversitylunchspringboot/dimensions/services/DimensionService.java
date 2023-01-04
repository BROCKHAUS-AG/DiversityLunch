package de.brockhausag.diversitylunchspringboot.dimensions.services;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.Dimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;

import java.util.List;

public interface DimensionService<DimensionType extends Dimension<Selectable>, Selectable extends SelectableOptions> {

    public DimensionType getDimension(String categoryDescription);

    public boolean addSelectableOption(DimensionCategory category, Selectable option);

    public boolean deleteSelectableOptionById(Long selectableOptionId);

    public boolean updateSelectableOption(Selectable option);

    public List<Selectable> getSelectableOptionsOfCategory(Long categoryId);

    public List<DimensionCategory> getAllCategoriesOfDimension();

    public Long getDimensionCategoryIdByDescription(String categoryDescription);

    public Selectable getSelectableOptionById(Long selectableOptionId);

    public DimensionCategory getDimensionCategoryByDescription(String dimensionCategory);
}
