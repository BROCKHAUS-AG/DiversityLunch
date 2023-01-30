package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.DietDto;
import de.brockhausag.diversitylunchspringboot.profile.generics.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.mapper.DietMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/diet")
@RestController
public class DietController extends DimensionModelController<DietDto,
        BasicDimensionSelectableOption,
        BasicDimension,
        BasicDimensionService,
        DietMapper> {
    public DietController(DietMapper mapper, BasicDimensionService service) {

        super(mapper, service, "Ernährung");
    }
}