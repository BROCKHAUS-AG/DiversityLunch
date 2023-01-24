package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.data.SocialBackgroundDiscriminationRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.SocialBackgroundDiscriminationService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.SocialBackgroundDiscriminationMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SocialBackgroundDiscriminationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SocialBackgroundDiscriminationEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/social-background-discrimination")
@RestController
public class SocialBackgroundDiscriminationController extends DefaultDimensionModelController<
        SocialBackgroundDiscriminationDto, SocialBackgroundDiscriminationEntity, SocialBackgroundDiscriminationRepository, SocialBackgroundDiscriminationService, SocialBackgroundDiscriminationMapper> {
    public SocialBackgroundDiscriminationController(SocialBackgroundDiscriminationMapper mapper, SocialBackgroundDiscriminationService service) {
        super(mapper, service);
    }
}
