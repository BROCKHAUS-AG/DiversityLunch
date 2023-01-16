package de.brockhausag.diversitylunchspringboot.profile.serviceTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.BaseModelTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.TestDefaultDimensionEntity;
import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionEntityService;
import de.brockhausag.diversitylunchspringboot.generics.defaultDimension.DefaultDimensionRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenericDefaultDimensionEntityServiceTest {

    private interface TestRepositoryType extends DefaultDimensionRepository<TestDefaultDimensionEntity> {
    }

    private BaseModelTestDataFactory factory;
    @Mock
    private TestRepositoryType testRepository;
    @Mock
    private ProfileService profileService;
    @InjectMocks
    private DefaultDimensionEntityService<TestDefaultDimensionEntity, TestRepositoryType> service;

    @BeforeEach
    void setup() {
        this.factory = new BaseModelTestDataFactory();
    }

    @Test
    void testDeleteEntityById_withExistingId_returnsTrue() {
        //Arrange
        TestDefaultDimensionEntity entity = factory.buildEntity(1);


        when(testRepository.existsById(entity.getId())).thenReturn(true);
        when(testRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(profileService.getAllProfilesWithSelectedDimensionOption(entity)).thenReturn(Collections.emptyList());

        //Act
        boolean actual = service.deleteEntityById(entity.getId());

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
        List<TestDefaultDimensionEntity> expectedList = Collections.emptyList();

        when(testRepository.findAll()).thenReturn(expectedList);
        //Act
        List<TestDefaultDimensionEntity> actualList = service.getAllEntities();

        //Assert
        assertEquals(expectedList, actualList);
    }

    @Test
    void testGetAllEntities_withThreeEntitiesInRepository_returnsListOfThreeEntities() {
        //Arrange
        List<TestDefaultDimensionEntity> expectedList = Arrays.asList(factory.buildEntity(1),
                factory.buildEntity(2), factory.buildEntity(3));

        when(testRepository.findAll()).thenReturn(expectedList);
        //Act
        List<TestDefaultDimensionEntity> actualList = service.getAllEntities();

        //Assert
        assertEquals(expectedList, actualList);
    }

}
