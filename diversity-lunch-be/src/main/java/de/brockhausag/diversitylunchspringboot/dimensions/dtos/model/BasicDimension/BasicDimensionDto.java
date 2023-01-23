package de.brockhausag.diversitylunchspringboot.dimensions.dtos.model.BasicDimension;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasicDimensionDto{

    private Long id;

    private DimensionCategory dimaensionCategory;

    private List<BasicDimensionSelectableOption> selectableOptions;

    private BasicDimensionSelectableOption defaultValue;

}
