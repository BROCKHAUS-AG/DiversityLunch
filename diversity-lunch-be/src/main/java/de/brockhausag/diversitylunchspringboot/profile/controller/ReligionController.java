package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ReligionDto;
import de.brockhausag.diversitylunchspringboot.profile.generics.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.mapper.ReligionMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/religion")
@RestController
public class ReligionController extends DimensionModelController<
        ReligionDto,
        BasicDimensionSelectableOption,
        BasicDimension,
        BasicDimensionService,
        ReligionMapper> {
    public ReligionController(ReligionMapper mapper, BasicDimensionService service) {
        super(mapper, service, "Religion");
    }
}