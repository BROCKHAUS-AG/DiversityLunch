package de.brockhausag.diversitylunchspringboot.profile.data;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import org.springframework.data.repository.CrudRepository;

public interface HobbyRepository extends CrudRepository<HobbyEntity, Long> {
}
