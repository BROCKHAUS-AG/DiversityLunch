package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.generics.basicDimension.DefaultDimensionEntityService;
import de.brockhausag.diversitylunchspringboot.profile.data.CountryRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.CountryEntity;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends DefaultDimensionEntityService<CountryEntity, CountryRepository> {
    public CountryService(CountryRepository repository) {
        super(repository);
    }
}
