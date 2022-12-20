package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionModelController;
import de.brockhausag.diversitylunchspringboot.generics.multiDimension.MultiDimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.data.HobbyRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.HobbyService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.HobbyMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hobby")
public class HobbyController extends MultiDimensionModelController<HobbyDto, HobbyEntity, HobbyRepository, HobbyService, HobbyMapper> {

    public HobbyController(HobbyMapper mapper, HobbyService service) {
        super(mapper, service);
    }
}
