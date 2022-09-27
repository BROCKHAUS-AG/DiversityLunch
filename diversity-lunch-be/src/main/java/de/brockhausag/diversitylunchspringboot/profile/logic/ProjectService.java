package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.ProjectRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProjectEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends GenericBaseEntityService<ProjectEntity, ProjectRepository> {
    public ProjectService(ProjectRepository repository) {
        super(repository);
    }
}