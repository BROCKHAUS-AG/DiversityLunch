package de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.MultiSelectDimension;


import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MultiSelectSelectableOptionCreationDTO {

    private List<MultiselectDimensionSelectableOption> selected;

    private DimensionCategory category;
}
