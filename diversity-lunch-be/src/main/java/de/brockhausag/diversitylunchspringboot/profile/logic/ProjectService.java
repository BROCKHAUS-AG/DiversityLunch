package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.ProjectRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProjectEntity;
import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends BaseEntityService<ProjectEntity, ProjectRepository> {
    public ProjectService(ProjectRepository repository) {
        super(repository);
    }
}
