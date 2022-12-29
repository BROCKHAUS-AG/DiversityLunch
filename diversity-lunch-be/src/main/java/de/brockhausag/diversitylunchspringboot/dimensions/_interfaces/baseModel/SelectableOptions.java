package de.brockhausag.diversitylunchspringboot.dimensions._interfaces.baseModel;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;

public interface SelectableOptions {
    Long getId();

    void setId(Long id);

    String getValue();

    void setValue(String value);

    DimensionCategory getDimensionCategory();

    void setDimensionCategory(DimensionCategory dimensionCategory);


}
