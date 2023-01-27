package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.GenderDto;
import de.brockhausag.diversitylunchspringboot.profile.generics.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.mapper.GenderMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/gender")
@RestController
public class GenderController extends DimensionModelController<
        GenderDto,
        BasicDimensionSelectableOption,
        BasicDimension,
        BasicDimensionService,
        GenderMapper> {
    public GenderController(GenderMapper mapper, BasicDimensionService service) {

        super(mapper, service, "Geschlechtliche Identität");
    }
}