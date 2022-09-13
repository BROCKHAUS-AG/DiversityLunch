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

import java.util.*;

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
        ResponseEntity<?> actualResponse = countryController.getCountry(existingCountryEntity.getId());

        //Assert
        assertEquals(expectedResponse.getStatusCode(),actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
    }

    @Test
    void testGetCountry_withNotExistingId_returnsNotFound() {
        //Arrange
        Long notExistingId = 666L;

        when(countryService.getEntityById(notExistingId)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<?> actualResponse = countryController.getCountry(notExistingId);

        //Assert
        assertFalse(actualResponse.hasBody());
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
    }

    @Test
    void testGetAllCountries_withEmptyDb_returnsEmptyList(){
        //Arrange
        List<CountryDto> emptyDtoList = Collections.emptyList();
        List<CountryEntity> emptyEntityList = Collections.emptyList();
        ResponseEntity<List<CountryDto>> expectedResponse = new ResponseEntity<>(
                emptyDtoList,
                HttpStatus.OK
        );

        when(countryService.getAllEntities()).thenReturn(emptyEntityList);
        when(countryMapper.entityToDto(emptyEntityList)).thenReturn(emptyDtoList);
        //Act
        ResponseEntity<List<CountryDto>> actualResponse = countryController.getAllCountries();

        //Assert
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    void testGetAllCountries_withTwoDbEntries_returnsListOfTwoCountryEntities(){
        //Arrange
        CountryEntity firstCountryEntity = this.factory.buildCountryEntity();
        CountryEntity secondCountryEntity = this.factory.buildSecondCountryEntity();
        List<CountryEntity> countryEntityList = Arrays.asList(firstCountryEntity, secondCountryEntity);

        CountryDto firstCountryDto = this.factory.buildCountryDto();
        CountryDto secondCountryDto = this.factory.buildSecondCountryDto();
        List<CountryDto> countryDtoList = Arrays.asList(firstCountryDto, secondCountryDto);

        ResponseEntity<List<CountryDto>> expectedResponse = new ResponseEntity<>(
                countryDtoList,
                HttpStatus.OK
        );

        when(countryService.getAllEntities()).thenReturn(countryEntityList);
        when(countryMapper.entityToDto(countryEntityList)).thenReturn(countryDtoList);
        //Arrange
        ResponseEntity<List<CountryDto>> actualResponse = countryController.getAllCountries();

        //Assert
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());

    }
}
