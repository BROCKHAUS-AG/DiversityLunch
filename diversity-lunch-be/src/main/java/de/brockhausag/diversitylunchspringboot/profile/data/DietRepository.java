package de.brockhausag.diversitylunchspringboot.profile.data;

import de.brockhausag.diversitylunchspringboot.profile.modelTest.entities.DietEntity;
import org.springframework.data.repository.CrudRepository;

public interface DietRepository extends CrudRepository<DietEntity, Long> {
}
