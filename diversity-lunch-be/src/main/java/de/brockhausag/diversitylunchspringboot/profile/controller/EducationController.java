package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.generics.basicDimension.DefaultDimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.data.EducationRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.EducationService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.EducationMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/education")
@RestController
public class EducationController extends DefaultDimensionModelController<
        EducationDto, EducationEntity, EducationRepository, EducationService, EducationMapper> {
    public EducationController(EducationMapper mapper, EducationService service) {
        super(mapper, service);
    }
}
