package de.brockhausag.diversitylunchspringboot.profile.data;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyCategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface HobbyCategoryRepository extends CrudRepository<HobbyCategoryEntity, Long> {
}
