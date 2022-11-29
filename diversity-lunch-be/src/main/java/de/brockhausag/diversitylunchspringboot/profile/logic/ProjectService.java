package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.generics.basicDimension.DefaultDimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.data.ProjectRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProjectEntity;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends DefaultDimensionEntityService<ProjectEntity, ProjectRepository> {
    public ProjectService(ProjectRepository repository) {
        super(repository);
    }
}
