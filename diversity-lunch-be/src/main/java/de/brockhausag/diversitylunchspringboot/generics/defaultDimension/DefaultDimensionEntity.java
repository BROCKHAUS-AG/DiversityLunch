package de.brockhausag.diversitylunchspringboot.generics.defaultDimension;

import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionEntity;

public interface DefaultDimensionEntity extends DimensionEntity {
    boolean isDefault();

    void setDefault(boolean isDefault);
}
