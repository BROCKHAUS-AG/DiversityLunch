package de.brockhausag.diversitylunchspringboot.dimensions.dtos.model;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MultiselectDimensionDto {

    private Long id;

    private DimensionCategory dimaensionCategory;

    private List<BasicDimensionSelectableOption> selectableOptions;
}
