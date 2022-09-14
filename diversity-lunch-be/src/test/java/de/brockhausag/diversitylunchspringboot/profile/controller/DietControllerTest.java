package de.brockhausag.diversitylunchspringboot.profile.controller;

import de.brockhausag.diversitylunchspringboot.data.DietTestDataFactory;
import de.brockhausag.diversitylunchspringboot.profile.logic.DietService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.DietMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.DietDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.DietEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DietControllerTest {
    @Mock
    private DietService dietService;
    @Mock
    private DietMapper dietMapper;
    @InjectMocks
    private DietController dietController;
    private DietTestDataFactory factory;

    @BeforeEach
    void setup() {
        factory = new DietTestDataFactory();
    }

    @Test
    void testGetDiet_withExistingId_returnsOkWithDietDto() {
        //Arrange
        DietEntity existingDietEntity = this.factory.buildDietEntity();
        DietDto expectedDietDto = this.factory.buildDietDto();
        ResponseEntity<DietDto> expectedResponse = new ResponseEntity<>(expectedDietDto, HttpStatus.OK);

        when(dietService.getEntityById(existingDietEntity.getId())).thenReturn(Optional.of(existingDietEntity));
        when(dietMapper.entityToDto(existingDietEntity)).thenReturn(expectedDietDto);

        //Act
        ResponseEntity<?> actualResponse = dietController.getDiet(existingDietEntity.getId());

        //Assert
        assertEquals(expectedResponse.getStatusCode(),actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
    }

    @Test
    void testGetDiet_withNotExistingId_returnsNotFound() {
        //Arrange
        Long notExistingId = 666L;

        when(dietService.getEntityById(notExistingId)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<?> actualResponse = dietController.getDiet(notExistingId);

        //Assert
        assertFalse(actualResponse.hasBody());
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
    }

    @Test
    void testGetAllCountries_withNoEntitiesInRepository_returnsEmptyList(){
        //Arrange
        List<DietDto> emptyDtoList = Collections.emptyList();
        List<DietEntity> emptyEntityList = Collections.emptyList();
        ResponseEntity<List<DietDto>> expectedResponse = new ResponseEntity<>(
                emptyDtoList,
                HttpStatus.OK
        );

        when(dietService.getAllEntities()).thenReturn(emptyEntityList);
        when(dietMapper.entityToDto(emptyEntityList)).thenReturn(emptyDtoList);
        //Act
        ResponseEntity<List<DietDto>> actualResponse = dietController.getAllCountries();

        //Assert
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    void testGetAllCountries_withTwoEntitiesInRepository_returnsListOfTwoDietEntities(){
        //Arrange
        DietEntity firstDietEntity = this.factory.buildDietEntity();
        DietEntity secondDietEntity = this.factory.buildSecondDietEntity();
        List<DietEntity> dietEntityList = Arrays.asList(firstDietEntity, secondDietEntity);

        DietDto firstDietDto = this.factory.buildDietDto();
        DietDto secondDietDto = this.factory.buildSecondDietDto();
        List<DietDto> dietDtoList = Arrays.asList(firstDietDto, secondDietDto);

        ResponseEntity<List<DietDto>> expectedResponse = new ResponseEntity<>(
                dietDtoList,
                HttpStatus.OK
        );

        when(dietService.getAllEntities()).thenReturn(dietEntityList);
        when(dietMapper.entityToDto(dietEntityList)).thenReturn(dietDtoList);
        //Arrange
        ResponseEntity<List<DietDto>> actualResponse = dietController.getAllCountries();

        //Assert
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());

    }
    
}
