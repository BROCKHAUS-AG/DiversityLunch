package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.data.SexualOrientationRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.SexualOrientationService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.SexualOrientationMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SexualOrientationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SexualOrientationEntity;
import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseModelController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/sexuality")
@RestController
public class SexualOrientationController extends BaseModelController<
        SexualOrientationDto, SexualOrientationEntity, SexualOrientationRepository, SexualOrientationService, SexualOrientationMapper> {
    public SexualOrientationController(SexualOrientationMapper mapper, SexualOrientationService service) {
        super(mapper, service);
    }
}
