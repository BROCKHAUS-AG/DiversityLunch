package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProjectDto;
import de.brockhausag.diversitylunchspringboot.profile.generics.DimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.mapper.ProjectMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/project")
@RestController
public class ProjectController extends DimensionModelController<
        ProjectDto,
        BasicDimensionSelectableOption,
        BasicDimension,
        BasicDimensionService,
        ProjectMapper> {
    public ProjectController(ProjectMapper mapper, BasicDimensionService service) {
        super(mapper, service, "Projekt");
    }
}