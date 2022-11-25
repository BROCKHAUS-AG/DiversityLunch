package de.brockhausag.diversitylunchspringboot.generics.WeightedDimension;

import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseDto;

public interface WeightedDto {
    Long getId();
    void setId(Long id);
    String getDescriptor();
    void setDescriptor(String descriptor);
    void setWeight(int weight);
    int getWeight();
}
