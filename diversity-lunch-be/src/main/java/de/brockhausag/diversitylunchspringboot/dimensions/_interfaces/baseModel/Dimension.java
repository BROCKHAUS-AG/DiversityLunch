package de.brockhausag.diversitylunchspringboot.dimensions._interfaces.baseModel;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import java.util.Set;

public interface Dimension<
        Selectables extends SelectableOptions
        > {

    Long getId();

    void setId(Long id);

    DimensionCategory getDimensionCategory();

    void setDimensionCategory(DimensionCategory dimensionCategory);

    Set<Selectables> getSelectableValues();

    void setSelectableValues(Set<Selectables> selectableValues);


}
