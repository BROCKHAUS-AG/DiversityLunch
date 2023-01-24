package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.data.ReligionRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.ReligionService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.ReligionMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ReligionDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ReligionEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/religion")
@RestController
public class ReligionController extends DefaultDimensionModelController<
        ReligionDto, ReligionEntity, ReligionRepository, ReligionService, ReligionMapper> {
    public ReligionController(ReligionMapper mapper, ReligionService service) {
        super(mapper, service);
    }
}
