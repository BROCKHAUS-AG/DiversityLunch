package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.CountryRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.CountryEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.GenericService;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends GenericService<CountryRepository, CountryEntity> {
    public CountryService(CountryRepository repository) {
        super(repository);
    }
}
