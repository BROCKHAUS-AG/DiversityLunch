package de.brockhausag.diversitylunchspringboot.profile.controllerTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.BaseModelTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.TestBaseDto;
import de.brockhausag.diversitylunchspringboot.dataFactories.TestBaseEntity;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseModelController;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseEntityService;
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
public class GenericBaseModelControllerTest {
    private interface TestRepositoryType extends CrudRepository<TestBaseEntity, Long> {}
    private static class TestServiceType extends GenericBaseEntityService<TestBaseEntity, TestRepositoryType> {
        public TestServiceType(TestRepositoryType repository) {
            super(repository);
        }
    }

    private BaseModelTestDataFactory factory;

    @Mock
    private Mapper<TestBaseDto, TestBaseEntity> mapper;
    @Mock
    private TestServiceType service ;

    @InjectMocks
    private GenericBaseModelController<TestBaseDto,TestBaseEntity,
                TestRepositoryType,TestServiceType, Mapper<TestBaseDto, TestBaseEntity> > controller;

    @BeforeEach
    void setup(){
       this.factory = new BaseModelTestDataFactory();
    }

    @Test
    void testGetOne_withExistingId_returnsOkWithTestBaseDto() {
        //Arrange
        TestBaseDto expectedDto = factory.buildDto(1);
        TestBaseEntity existingEntity = factory.buildEntity(1);
        ResponseEntity<TestBaseDto> expectedResponse = new ResponseEntity<>(expectedDto, HttpStatus.OK);

        when(service.getEntityById(existingEntity.getId())).thenReturn(Optional.of(existingEntity));
        when(mapper.entityToDto(existingEntity)).thenReturn(expectedDto);

        //Act
        ResponseEntity<?> actualResponse = controller.getOne(existingEntity.getId());

        //Assert
        assertEquals(expectedResponse.getStatusCode(),actualResponse.getStatusCode());
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
    void testGetAll_withNoEntitiesInRepository_returnsEmptyList(){
        //Arrange
        List<TestBaseDto> emptyDtoList = Collections.emptyList();
        List<TestBaseEntity> emptyEntityList = Collections.emptyList();
        ResponseEntity<List<TestBaseDto>> expectedResponse = new ResponseEntity<>(
                emptyDtoList,
                HttpStatus.OK
        );

        when(service.getAllEntities()).thenReturn(emptyEntityList);
        when(mapper.entityToDto(emptyEntityList)).thenReturn(emptyDtoList);
        //Act
        ResponseEntity<List<TestBaseDto>> actualResponse = controller.getAll();

        //Assert
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    void testGetAllCountries_withThreeEntitiesInRepository_returnsListOfThreeTestBaseEntities(){
        //Arrange
        List<TestBaseEntity> entityList = Arrays.asList(factory.buildEntity(1),
                factory.buildEntity(2), factory.buildEntity(3));
        List<TestBaseDto> dtoList =Arrays.asList(factory.buildDto(1),
                factory.buildDto(2), factory.buildDto(3));

        ResponseEntity<List<TestBaseDto>> expectedResponse = new ResponseEntity<>(
                dtoList,
                HttpStatus.OK
        );

        when(service.getAllEntities()).thenReturn(entityList);
        when(mapper.entityToDto(entityList)).thenReturn(dtoList);
        //Arrange
        ResponseEntity<List<TestBaseDto>> actualResponse = controller.getAll();

        //Assert
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    void testPostOne_calledThreeTimesWithSameDto_callCreateEntityThreeTimesWithMappedEntity(){
        TestBaseDto dto = factory.buildDto(1);
        TestBaseEntity entity = mapper.dtoToEntity(dto);
        when(service.createEntity(entity)).thenReturn(entity);
        final int AMOUNT = 3;

        for (int i = 0; i < AMOUNT; ++i) {
            controller.postOne(dto);
        }

        verify(service, times(AMOUNT)).createEntity(entity);
    }

    @Test
    void testPutOne_calledThreeTimesWithSameDto_callCreateOrUpdateEntityThreeTimesWithMappedEntity(){
        TestBaseDto dto = factory.buildDto(1);
        TestBaseEntity entity = mapper.dtoToEntity(dto);
        when(service.updateOrCreateEntity(entity)).thenReturn(entity);
        final int AMOUNT = 3;

        for (int i = 0; i < AMOUNT; ++i) {
            controller.putOne(dto);
        }

        verify(service, times(AMOUNT)).updateOrCreateEntity(entity);
    }

    @Test
    void testDeleteOne_serviceDeleteByIdReturnTrue_returnStatusCodeOk(){
        final Long someIdThatExists = 1337L;
        when(service.deleteEntityById(someIdThatExists)).thenReturn(true);
        var expected = HttpStatus.OK;

        var actual = controller.deleteOne(someIdThatExists).getStatusCode();

        assertEquals(expected, actual);
    }

    @Test
    void testDeleteOne_serviceDeleteByIdReturnFalse_returnStatusCodeNotFound(){
        final Long someNotExistingId = 1337L;
        when(service.deleteEntityById(someNotExistingId)).thenReturn(false);
        var expected = HttpStatus.NOT_FOUND;

        var actual = controller.deleteOne(someNotExistingId).getStatusCode();

        assertEquals(expected, actual);
    }


}
