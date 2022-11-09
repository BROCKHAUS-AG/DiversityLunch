package de.brockhausag.diversitylunchspringboot.profile.data;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.SexualOrientationEntity;
import org.springframework.data.repository.CrudRepository;

public interface SexualityRepository extends CrudRepository<SexualOrientationEntity,Long> {
}
