package de.brockhausag.diversitylunchspringboot.profile.repository;

import de.brockhausag.diversitylunchspringboot.profile.model.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Long> {
}
