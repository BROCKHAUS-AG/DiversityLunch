package de.brockhausag.diversitylunchspringboot.generics.weightedDimension;


import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionDto;

public interface WeightedDto extends DimensionDto {
    boolean isDefault();
    void setDefault(boolean isDefault);
}
