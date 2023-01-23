package de.brockhausag.diversitylunchspringboot.dimensions.services;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.Dimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;

import java.util.List;
import java.util.Optional;

public interface DimensionService<
        DimensionType extends Dimension<Selectable>,
        Selectable extends SelectableOptions> {
    Optional<DimensionType> getDimension(String categoryDescription);
    Optional<DimensionType> getDimension(DimensionCategory category);
    List<DimensionType> getAllDimensions();
    boolean addSelectableOption(Selectable option);
    boolean deleteSelectableOptionById(Long selectableOptionId);
    boolean updateSelectableOption(Selectable option);
    List<Selectable> getSelectableOptions(DimensionType dimension);
    Optional<Selectable> getSelectableOption(DimensionType dimension, String optionName);
    List<Selectable> getSelectableOptionsByCategory(DimensionCategory categoryId);
    Optional<DimensionCategory> getDimensionCategoryByDescription(String categoryDescription);
    Optional<Selectable> getSelectableOptionById(Long selectableOptionId);
}