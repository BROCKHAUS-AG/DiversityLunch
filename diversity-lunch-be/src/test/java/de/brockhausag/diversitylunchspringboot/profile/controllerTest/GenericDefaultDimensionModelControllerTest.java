package de.brockhausag.diversitylunchspringboot.profile.controllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GenericDefaultDimensionModelControllerTest {/*
    private BaseModelTestDataFactory factory;
    @Mock
    private DimensionMapper<TestDefaultDimensionDto, TestBasicDimension> dimensionMapper;
    @Mock
    private TestServiceType service;
    @InjectMocks
    private DefaultDimensionModelController<TestDefaultDimensionDto, TestBasicDimension,
            TestRepositoryType, TestServiceType, DimensionMapper<TestDefaultDimensionDto, TestBasicDimension>> controller;

    @BeforeEach
    void setup() {
        this.factory = new BaseModelTestDataFactory();
    }

    @Test
    void testGetOne_withExistingId_returnsOkWithTestBaseDto() {
        //Arrange
        TestDefaultDimensionDto expectedDto = factory.buildDto(1);
        TestBasicDimension existingEntity = factory.buildEntity(1);
        ResponseEntity<TestDefaultDimensionDto> expectedResponse = new ResponseEntity<>(expectedDto, HttpStatus.OK);

        when(service.getEntityById(existingEntity.getId())).thenReturn(Optional.of(existingEntity));
        when(dimensionMapper.entityToDto(existingEntity)).thenReturn(expectedDto);

        //Act
        ResponseEntity<?> actualResponse = controller.getOne(existingEntity.getId());

        //Assert
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
    }

    @Test
    void testGetOne_withNotExistingId_returnsNotFound() {
        //Arrange
        Long notExistingId = 666L;

        when(service.getEntityById(notExistingId)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<?> actualResponse = controller.getOne(notExistingId);

        //Assert
        assertFalse(actualResponse.hasBody());
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
    }

    @Test
    void testGetAll_withNoEntitiesInRepository_returnsEmptyList() {
        //Arrange
        List<TestDefaultDimensionDto> emptyDtoList = Collections.emptyList();
        List<TestBasicDimension> emptyEntityList = Collections.emptyList();
        ResponseEntity<List<TestDefaultDimensionDto>> expectedResponse = new ResponseEntity<>(
                emptyDtoList,
                HttpStatus.OK
        );

        when(service.getAllEntities()).thenReturn(emptyEntityList);
        when(dimensionMapper.entityToDto(emptyEntityList)).thenReturn(emptyDtoList);
        //Act
        ResponseEntity<List<TestDefaultDimensionDto>> actualResponse = controller.getAll();

        //Assert
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    void testGetAllCountries_withThreeEntitiesInRepository_returnsListOfThreeTestBaseEntities() {
        //Arrange
        List<TestBasicDimension> entityList = Arrays.asList(factory.buildEntity(1),
                factory.buildEntity(2), factory.buildEntity(3));
        List<TestDefaultDimensionDto> dtoList = Arrays.asList(factory.buildDto(1),
                factory.buildDto(2), factory.buildDto(3));

        ResponseEntity<List<TestDefaultDimensionDto>> expectedResponse = new ResponseEntity<>(
                dtoList,
                HttpStatus.OK
        );

        when(service.getAllEntities()).thenReturn(entityList);
        when(dimensionMapper.entityToDto(entityList)).thenReturn(dtoList);
        //Arrange
        ResponseEntity<List<TestDefaultDimensionDto>> actualResponse = controller.getAll();

        //Assert
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    void testPostOne_calledThreeTimesWithSameDto_callCreateEntityThreeTimesWithMappedEntity() {
        TestDefaultDimensionDto dto = factory.buildDto(1);
        TestBasicDimension entity = dimensionMapper.dtoToEntity(dto);
        when(service.createEntity(entity)).thenReturn(entity);
        final int AMOUNT = 3;

        for (int i = 0; i < AMOUNT; ++i) {
            controller.postOne(dto);
        }

        verify(service, times(AMOUNT)).createEntity(entity);
    }

    @Test
    void testPutOne_calledThreeTimesWithSameDto_callCreateOrUpdateEntityThreeTimesWithMappedEntity() {
        TestDefaultDimensionDto dto = factory.buildDto(1);
        TestBasicDimension entity = dimensionMapper.dtoToEntity(dto);
        when(service.updateOrCreateEntity(entity)).thenReturn(entity);
        final int AMOUNT = 3;

        for (int i = 0; i < AMOUNT; ++i) {
            controller.putOne(dto);
        }

        verify(service, times(AMOUNT)).updateOrCreateEntity(entity);
    }

    @Test
    void testDeleteOne_serviceDeleteByIdReturnTrue_returnStatusCodeOk() {
        final Long someIdThatExists = 1337L;
        when(service.deleteEntityById(someIdThatExists)).thenReturn(true);
        var expected = HttpStatus.OK;

        var actual = controller.deleteOne(someIdThatExists).getStatusCode();

        assertEquals(expected, actual);
    }

    @Test
    void testDeleteOne_serviceDeleteByIdReturnFalse_returnStatusCodeNotFound() {
        final Long someNotExistingId = 1337L;
        when(service.deleteEntityById(someNotExistingId)).thenReturn(false);
        var expected = HttpStatus.NOT_FOUND;

        var actual = controller.deleteOne(someNotExistingId).getStatusCode();

        assertEquals(expected, actual);
    }

    private interface TestRepositoryType extends CrudRepository<TestBasicDimension, Long> {
    }

    private static class TestServiceType extends DimensionEntityService<
            EntityType, RepositoryType> {
        public TestServiceType(TestRepositoryType repository) {
            super(repository);
        }
    }

*/
}
