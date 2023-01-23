package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.WeightedDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.WorkExperienceDto;
import de.brockhausag.diversitylunchspringboot.profile.generics.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.mapper.WorkExperienceMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/work-experience")
@RestController
public class WorkExperienceController extends DimensionModelController<
        WorkExperienceDto,
        WeightedDimensionSelectableOption,
        WeightedDimension,
        WeightedDimensionService,
        WorkExperienceMapper> {
    public WorkExperienceController(WorkExperienceMapper mapper, WeightedDimensionService service) {
        super(mapper, service, "Berufserfahrung");
    }
}