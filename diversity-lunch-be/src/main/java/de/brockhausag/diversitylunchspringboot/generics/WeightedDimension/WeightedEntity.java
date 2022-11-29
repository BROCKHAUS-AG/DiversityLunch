package de.brockhausag.diversitylunchspringboot.generics.WeightedDimension;

import de.brockhausag.diversitylunchspringboot.generics.Dimension.DimensionEntity;

public interface WeightedEntity extends DimensionEntity {


    void setWeight(int weight);

    int getWeight();


}
