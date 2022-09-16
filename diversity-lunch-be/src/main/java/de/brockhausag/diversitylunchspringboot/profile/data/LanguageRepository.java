package de.brockhausag.diversitylunchspringboot.profile.data;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.LanguageEntity;
import org.springframework.data.repository.CrudRepository;

public interface LanguageRepository extends CrudRepository<LanguageEntity, Long> {
}
