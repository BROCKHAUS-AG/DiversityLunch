package de.brockhausag.diversitylunchspringboot.profile.controllerTest;

import de.brockhausag.diversitylunchspringboot.profile.data.CountryRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.CountryService;
import de.brockhausag.diversitylunchspringboot.profile.mapperTest.CountryMapper;
import de.brockhausag.diversitylunchspringboot.profile.modelTest.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.modelTest.entities.CountryEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseModelController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/country")
@CrossOrigin(origins = "http://localhost:4000/admin-panel")
@RestController
public class CountryController extends GenericBaseModelController<
        CountryDto, CountryEntity, CountryRepository, CountryService, CountryMapper> {
    public CountryController(CountryMapper mapper, CountryService service) {
        super(mapper, service);
    }
}
