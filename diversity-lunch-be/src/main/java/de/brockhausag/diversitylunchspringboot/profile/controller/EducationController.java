package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.data.EducationRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.EducationService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.EducationMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.EducationDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseModelController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/education")
@CrossOrigin(origins = "http://localhost:4000/admin-panel")
@RestController
public class EducationController extends GenericBaseModelController<
        EducationDto, EducationEntity, EducationRepository, EducationService, EducationMapper> {
    public EducationController(EducationMapper mapper, EducationService service) {
        super(mapper, service);
    }
}
