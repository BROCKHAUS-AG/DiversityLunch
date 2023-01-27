package de.brockhausag.diversitylunchspringboot.dimensions.entities;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;

public interface SelectableOptions {
    Long getId();

    void setId(Long id);

    String getValue();

    void setValue(String value);

    DimensionCategory getDimensionCategory();

    void setDimensionCategory(DimensionCategory dimensionCategory);
}
