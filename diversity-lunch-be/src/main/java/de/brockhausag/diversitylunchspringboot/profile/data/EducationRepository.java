package de.brockhausag.diversitylunchspringboot.profile.data;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.EducationEntity;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<EducationEntity, Long> {
}
