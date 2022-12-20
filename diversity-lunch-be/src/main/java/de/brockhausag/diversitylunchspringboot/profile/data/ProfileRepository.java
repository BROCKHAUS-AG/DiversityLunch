package de.brockhausag.diversitylunchspringboot.profile.data;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Long> {
    List<ProfileEntity> findAllByHobby(HobbyEntity entity);
}
