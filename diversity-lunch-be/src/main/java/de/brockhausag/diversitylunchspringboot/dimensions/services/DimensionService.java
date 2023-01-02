package de.brockhausag.diversitylunchspringboot.dimensions.services;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.Dimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.SelectableOptions;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;

import java.util.List;

public interface DimensionService<DimensionType extends Dimension<Selectable>, Selectable extends SelectableOptions> {
    public DimensionType getDimension(String categoryDescription);
    public DimensionType getDimension(DimensionCategory category);
    public List<DimensionType> getAllDimensions();
    public boolean addSelectableOption(DimensionCategory category, Selectable option);
    public boolean deleteSelectableOption(Selectable option);
    public boolean updateSelectableOption(Selectable option);
    public List<Selectable> getSelectableOptions(DimensionCategory category);
}
