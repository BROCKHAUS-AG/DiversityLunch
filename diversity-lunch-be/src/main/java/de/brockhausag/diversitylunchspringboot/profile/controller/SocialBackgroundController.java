package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.data.SocialBackgroundRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.SocialBackgroundService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.SocialBackgroundMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SocialBackgroundDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SocialBackgroundEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseModelController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/socialBackground")
@RestController
public class SocialBackgroundController extends GenericBaseModelController<SocialBackgroundDto, SocialBackgroundEntity, SocialBackgroundRepository, SocialBackgroundService, SocialBackgroundMapper> {
    public SocialBackgroundController(SocialBackgroundMapper mapper, SocialBackgroundService service) {
        super(mapper, service);
    }
}
