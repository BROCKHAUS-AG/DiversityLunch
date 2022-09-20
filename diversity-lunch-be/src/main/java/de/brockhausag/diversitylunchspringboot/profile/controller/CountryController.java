package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.data.CountryRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.CountryService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.CountryMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.CountryEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseModelController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/country")
@RestController
public class CountryController extends GenericBaseModelController<
        CountryDto, CountryEntity, CountryRepository, CountryService, CountryMapper> {
    public CountryController(CountryMapper mapper, CountryService service) {
        super(mapper, service);
    }
}
