package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.CountryRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.CountryEntity;
import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends BaseEntityService<CountryEntity, CountryRepository> {
    public CountryService(CountryRepository repository) {
        super(repository);
    }
}
