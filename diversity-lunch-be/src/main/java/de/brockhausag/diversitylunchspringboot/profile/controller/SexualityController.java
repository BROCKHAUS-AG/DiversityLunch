package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.data.SexualityRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.SexualityService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.SexualityMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.SexualityDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.SexualityEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseModelController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/sexuality")
@RestController
public class SexualityController extends GenericBaseModelController<
        SexualityDto, SexualityEntity, SexualityRepository, SexualityService, SexualityMapper> {
    public SexualityController(SexualityMapper mapper, SexualityService service) {
        super(mapper, service);
    }
}