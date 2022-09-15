package de.brockhausag.diversitylunchspringboot.project.controller;

import de.brockhausag.diversitylunchspringboot.project.mapper.ProjectMapper;
import de.brockhausag.diversitylunchspringboot.project.model.ProjectDto;
import de.brockhausag.diversitylunchspringboot.project.model.ProjectEntity;
import de.brockhausag.diversitylunchspringboot.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
@Slf4j
@RequiredArgsConstructor

public class ProjectController {

    private final ProjectService projectService;

    private final ProjectMapper projectMapper;

    @PostMapping(produces = {"application/json"})
    public ProjectDto createProject(@RequestBody ProjectDto projectDto)
    {
        ProjectEntity projectEntity = this.projectMapper.mapDtoToEntity(projectDto);
        // todo lazevedo 09.08.22 Check whether projectEntity gets update by calling save-method in repository
        this.projectService.createProject(projectEntity);

        return this.projectMapper.mapEntityToDto(projectEntity);
    }

}

