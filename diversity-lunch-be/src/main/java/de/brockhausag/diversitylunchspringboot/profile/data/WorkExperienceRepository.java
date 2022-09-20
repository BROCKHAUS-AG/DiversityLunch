package de.brockhausag.diversitylunchspringboot.profile.data;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.WorkExperienceEntity;
import org.springframework.data.repository.CrudRepository;

public interface WorkExperienceRepository extends CrudRepository<WorkExperienceEntity, Long> {
}
