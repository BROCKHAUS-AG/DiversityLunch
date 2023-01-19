package de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.BasicDimension;


import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicSelectableOptionCreationDTO {

    private BasicDimensionSelectableOption selected;

    private DimensionCategory category;
}
