package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.DefaultDimensionModelController;
import de.brockhausag.diversitylunchspringboot.profile.data.ProjectRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProjectService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.ProjectMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProjectDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProjectEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/project")
@RestController
public class ProjectController extends DefaultDimensionModelController<
        ProjectDto, ProjectEntity, ProjectRepository, ProjectService, ProjectMapper> {
    public ProjectController(ProjectMapper mapper, ProjectService service) {
        super(mapper, service);
    }
}
