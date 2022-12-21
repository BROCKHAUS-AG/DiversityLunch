package de.brockhausag.diversitylunchspringboot.generics.weightedDimension;

import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionEntity;

public interface WeightedEntity extends DimensionEntity {

    int getWeight();

    void setWeight(int weight);

    boolean isDefault();

    void setDefault(boolean isDefault);
}
