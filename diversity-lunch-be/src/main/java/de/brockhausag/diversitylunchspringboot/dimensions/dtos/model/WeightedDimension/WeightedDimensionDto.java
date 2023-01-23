package de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.WeightedDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeightedDimensionDto {

    private Long id;

    private DimensionCategory dimaensionCategory;

    private List<WeightedDimensionSelectableOption> selectableOptions;

    private WeightedDimensionSelectableOption defaultValue;
}
