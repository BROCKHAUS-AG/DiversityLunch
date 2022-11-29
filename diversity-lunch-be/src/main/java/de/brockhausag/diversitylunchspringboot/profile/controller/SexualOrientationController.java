package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.generics.basicDimension.DefaultDimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.data.SexualOrientationRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.SexualOrientationService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.SexualOrientationMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SexualOrientationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SexualOrientationEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/sexuality")
@RestController
public class SexualOrientationController extends DefaultDimensionModelController<
        SexualOrientationDto, SexualOrientationEntity, SexualOrientationRepository, SexualOrientationService, SexualOrientationMapper> {
    public SexualOrientationController(SexualOrientationMapper mapper, SexualOrientationService service) {
        super(mapper, service);
    }
}
