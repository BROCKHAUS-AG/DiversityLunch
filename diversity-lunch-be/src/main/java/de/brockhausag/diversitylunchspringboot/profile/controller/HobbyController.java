package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.MultiselectDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.generics.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.mapper.HobbyMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hobby")
public class HobbyController extends DimensionModelController<
        HobbyDto,
        MultiselectDimensionSelectableOption,
        MultiselectDimension,
        MultiselectDimensionService,
        HobbyMapper> {

    public HobbyController(HobbyMapper mapper, MultiselectDimensionService service) {

        super(mapper, service, "Hobby");

    }
}