package de.brockhausag.diversitylunchspringboot.profile.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.CrudRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenericBasicDimensionServiceTest {

    @Test
    void testFalse(){
        assertFalse(true);
    }

    /*
    private BaseModelTestDataFactory factory;
    @Mock
    private TestRepositoryType testRepository;
    @InjectMocks
    private DefaultDimensionEntityService<TestBasicDimension, TestRepositoryType> service;

    @BeforeEach
    void setup() {
        this.factory = new BaseModelTestDataFactory();
    }

    @Test
    void testDeleteEntityById_withExistingId_returnsTrue() {
        //Arrange
        Long existingId = 666L;

        when(testRepository.existsById(existingId)).thenReturn(true);

        //Act
        boolean actual = service.deleteEntityById(existingId);

        //Assert
        assertTrue(actual);
    }

    @Test
    void testDeleteEntityById_withNotExistingId_returnsFalse() {
        //Arrange
        Long notExistingId = 7L;

        when(testRepository.existsById(notExistingId)).thenReturn(false);

        //Act
        boolean actual = service.deleteEntityById(notExistingId);

        //Assert
        assertFalse(actual);
    }

    @Test
    void testGetAllEntities_withNoEntitiesInRepository_returnsEmptyList() {
        //Arrange
        List<TestBasicDimension> expectedList = Collections.emptyList();

        when(testRepository.findAll()).thenReturn(expectedList);
        //Act
        List<TestBasicDimension> actualList = service.getAllEntities();

        //Assert
        assertEquals(expectedList, actualList);
    }

    @Test
    void testGetAllEntities_withThreeEntitiesInRepository_returnsListOfThreeEntities() {
        //Arrange
        List<TestBasicDimension> expectedList = Arrays.asList(factory.buildEntity(1),
                factory.buildEntity(2), factory.buildEntity(3));

        when(testRepository.findAll()).thenReturn(expectedList);
        //Act
        List<TestBasicDimension> actualList = service.getAllEntities();

        //Assert
        assertEquals(expectedList, actualList);
    }

    private interface TestRepositoryType extends CrudRepository<TestBasicDimension, Long> {
    }
*/
}
