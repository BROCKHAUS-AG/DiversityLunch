package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.data.EducationRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.EducationService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.EducationMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;
import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseModelController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/education")
@RestController
public class EducationController extends BaseModelController<
        EducationDto, EducationEntity, EducationRepository, EducationService, EducationMapper> {
    public EducationController(EducationMapper mapper, EducationService service) {
        super(mapper, service);
    }
}
