package de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.WeightedDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WeightedSelectableOptionCreationDTO {

    private WeightedDimensionSelectableOption selected;

    private DimensionCategory category;
}
