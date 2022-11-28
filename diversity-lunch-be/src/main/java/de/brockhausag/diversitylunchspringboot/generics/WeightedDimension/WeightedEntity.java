package de.brockhausag.diversitylunchspringboot.generics.WeightedDimension;

import de.brockhausag.diversitylunchspringboot.generics.Dimension.DimensionEntity;
import de.brockhausag.diversitylunchspringboot.generics.Dimension.DimensionEntityService;
import de.brockhausag.diversitylunchspringboot.meeting.model.Category;

public interface WeightedEntity extends DimensionEntity {


    void setWeight(int weight);

    int getWeight();


}
