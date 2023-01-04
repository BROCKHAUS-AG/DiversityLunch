package de.brockhausag.diversitylunchspringboot.profile.oldStructure.controller;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.SocialBackgroundDiscriminationDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper.SocialBackgroundDiscriminationMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/social-background-discrimination")
@RestController
public class SocialBackgroundDiscriminationController extends DimensionModelController<
        SocialBackgroundDiscriminationDto,
        BasicDimensionSelectableOption,
        BasicDimension,
        BasicDimensionService,
        SocialBackgroundDiscriminationMapper> {
    public SocialBackgroundDiscriminationController(SocialBackgroundDiscriminationMapper mapper, BasicDimensionService service) {
        super(mapper, service, "Diskriminierung aufgrund sozialer Herkunft");
    }
}
