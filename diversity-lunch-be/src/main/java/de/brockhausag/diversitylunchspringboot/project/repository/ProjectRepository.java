package de.brockhausag.diversitylunchspringboot.project.repository;

import de.brockhausag.diversitylunchspringboot.project.model.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {
}
