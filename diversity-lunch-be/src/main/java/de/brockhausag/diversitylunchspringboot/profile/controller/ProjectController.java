package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.data.ProjectRepository;

import de.brockhausag.diversitylunchspringboot.profile.logic.ProjectService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.ProjectMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProjectDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProjectEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseModelController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/project")
@CrossOrigin(origins = "http://localhost:4000/admin-panel")
@RestController
public class ProjectController extends GenericBaseModelController<
        ProjectDto, ProjectEntity, ProjectRepository, ProjectService, ProjectMapper> {
    public ProjectController(ProjectMapper mapper, ProjectService service) {
        super(mapper, service);
    }
}