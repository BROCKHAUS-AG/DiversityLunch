package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.data.CountryTestDataFactory;
import de.brockhausag.diversitylunchspringboot.profile.logic.CountryService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.CountryMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.CountryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CountryControllerTest {
    @Mock
    private CountryService countryService;
    @Mock
    private CountryMapper countryMapper;

    @InjectMocks
    private CountryController countryController;

    private final CountryTestDataFactory factory = new CountryTestDataFactory();

    @Test
    void testGetCountry_withExistingId_returnsOkWithCountryDto() {
        //Arrange
        CountryEntity existingCountryEntity = this.factory.buildCountryEntity();
        CountryDto expectedCountryDto = this.factory.buildCountryDto();
        ResponseEntity<CountryDto> expectedResponse = new ResponseEntity<>(expectedCountryDto, HttpStatus.OK);

        when(countryService.getEntityById(existingCountryEntity.getId())).thenReturn(Optional.of(existingCountryEntity));
        when(countryMapper.entityToDto(existingCountryEntity)).thenReturn(expectedCountryDto);

        //Act
        ResponseEntity<CountryDto> actualResponse = countryController.getCountry(existingCountryEntity.getId());

        //Assert
        assertEquals(expectedResponse.getStatusCode(),actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
    }
}
