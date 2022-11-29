package de.brockhausag.diversitylunchspringboot.generics.weightedDimension;

import de.brockhausag.diversitylunchspringboot.generics.dimension.DimensionEntity;

public interface WeightedEntity extends DimensionEntity {


    void setWeight(int weight);

    int getWeight();


}
