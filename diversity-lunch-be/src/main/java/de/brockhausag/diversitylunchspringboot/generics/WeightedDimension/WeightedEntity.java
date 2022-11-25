package de.brockhausag.diversitylunchspringboot.generics.WeightedDimension;

import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseEntity;

/*
* Why does this interface not extend BaseEntity?
*
* Because any method would accept a weightedEntity as a BaseEntity
* which can lead to a faulty matching algotithm. Having both interfaces
* seperate allows for clear Typisation between them.
* */
public interface WeightedEntity {

    Long getId();
    void setId(Long id);
    String getDescriptor();
    void setDescriptor(String descriptor);
    void setWeight(int weight);
    int getWeight();
}
