package de.brockhausag.diversitylunchspringboot.profile.data;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ReligionEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReligionRepository extends CrudRepository<ReligionEntity, Long> {
}