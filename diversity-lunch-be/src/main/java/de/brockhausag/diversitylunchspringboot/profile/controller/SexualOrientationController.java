package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SexualOrientationDto;
import de.brockhausag.diversitylunchspringboot.profile.generics.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.mapper.SexualOrientationMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/sexuality")
@RestController
public class SexualOrientationController extends DimensionModelController<
        SexualOrientationDto,
        BasicDimensionSelectableOption,
        BasicDimension,
        BasicDimensionService,
        SexualOrientationMapper> {
    public SexualOrientationController(SexualOrientationMapper mapper, BasicDimensionService service) {
        super(mapper, service,"Sexuelle Orientierung");
    }
}