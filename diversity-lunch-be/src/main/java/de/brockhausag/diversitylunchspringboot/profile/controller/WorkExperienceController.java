package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.generics.WeightedDimension.WeightedEntityService;
import de.brockhausag.diversitylunchspringboot.generics.WeightedDimension.WeightedModelController;
import de.brockhausag.diversitylunchspringboot.profile.data.WorkExperienceRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.WorkExperienceService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.WorkExperienceMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.WorkExperienceDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.WorkExperienceEntity;
import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseModelController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/work-experience")
@RestController
public class WorkExperienceController extends WeightedModelController<
        WorkExperienceDto, WorkExperienceEntity, WorkExperienceRepository, WorkExperienceService, WorkExperienceMapper> {
    public WorkExperienceController(WorkExperienceMapper mapper, WorkExperienceService service) {
        super(mapper, service);
    }
}
