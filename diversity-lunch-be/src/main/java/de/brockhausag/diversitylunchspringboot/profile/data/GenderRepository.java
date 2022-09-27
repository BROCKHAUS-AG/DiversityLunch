package de.brockhausag.diversitylunchspringboot.profile.data;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.GenderEntity;
import org.springframework.data.repository.CrudRepository;

public interface GenderRepository extends CrudRepository<GenderEntity, Long> {
}
