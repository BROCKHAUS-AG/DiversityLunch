package de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.BasicDimension;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicSelectableOptionUpdateDTO {

    private Long id;

    private String name;

    private Boolean ignoreInScoring;

}
