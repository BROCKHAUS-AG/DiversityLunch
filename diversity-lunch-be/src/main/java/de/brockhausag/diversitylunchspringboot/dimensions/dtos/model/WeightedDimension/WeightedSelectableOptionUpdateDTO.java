package de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.WeightedDimension;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeightedSelectableOptionUpdateDTO {

    private  Long id;

    private String name;

    private  Boolean ignoreInScoring;

    private int weight;
}
