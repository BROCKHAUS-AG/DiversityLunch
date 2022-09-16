package de.brockhausag.diversitylunchspringboot.profile.data;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.DietEntity;
import org.springframework.data.repository.CrudRepository;

public interface DietRepository extends CrudRepository<DietEntity, Long> {
}
