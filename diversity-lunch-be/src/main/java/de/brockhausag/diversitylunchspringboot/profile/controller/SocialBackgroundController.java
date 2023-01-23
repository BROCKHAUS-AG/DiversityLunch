package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SocialBackgroundDto;
import de.brockhausag.diversitylunchspringboot.profile.generics.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.mapper.SocialBackgroundMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/socialBackground")
@RestController
public class SocialBackgroundController extends DimensionModelController<
        SocialBackgroundDto,
        BasicDimensionSelectableOption,
        BasicDimension,
        BasicDimensionService,
        SocialBackgroundMapper> {
    public SocialBackgroundController(SocialBackgroundMapper mapper, BasicDimensionService service) {
        super(mapper, service, "Soziale Herkunft");
    }
}