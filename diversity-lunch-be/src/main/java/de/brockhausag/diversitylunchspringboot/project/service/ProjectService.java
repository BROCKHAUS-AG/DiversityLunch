package de.brockhausag.diversitylunchspringboot.project.service;

import de.brockhausag.diversitylunchspringboot.project.model.ProjectEntity;
import de.brockhausag.diversitylunchspringboot.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    public ProjectEntity createProject(ProjectEntity projectEntity) {
        return this.projectRepository.save(projectEntity);
    }
}
