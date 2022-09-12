package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.profile.logic.CountryService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.CountryMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.CountryEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.ErrorHandlingController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/country/")
@RestController
public class CountryController extends ErrorHandlingController {

    private final CountryMapper countryMapper;
    private final CountryService countryService;

    public CountryController(CountryMapper countryMapper, CountryService countryService) {
        this.countryMapper = countryMapper;
        this.countryService = countryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CountryDto>> getAllCountries(){
        List<CountryEntity> countryEntityList = countryService.getAllEntities();
        return new ResponseEntity<>(
                countryMapper.entityToDto(countryEntityList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable Long id){
        Optional<CountryEntity> optionalCountryEntity = countryService.getEntityById(id);
        if (optionalCountryEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                countryMapper.entityToDto(optionalCountryEntity.get()),
                HttpStatus.OK
        );
    }
}
