package de.brockhausag.diversitylunchspringboot.profile.data;

import de.brockhausag.diversitylunchspringboot.profile.modelTest.entities.CountryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<CountryEntity, Long> {
}
