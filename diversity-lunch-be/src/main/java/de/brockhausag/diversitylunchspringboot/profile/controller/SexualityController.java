package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.data.SexualityRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.SexualOrientationService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.SexualOrientationMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SexualOrientationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SexualOrientationEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseModelController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/sexuality")
@RestController
public class SexualityController extends GenericBaseModelController<
        SexualOrientationDto, SexualOrientationEntity, SexualityRepository, SexualOrientationService, SexualOrientationMapper> {
    public SexualityController(SexualOrientationMapper mapper, SexualOrientationService service) {
        super(mapper, service);
    }
}