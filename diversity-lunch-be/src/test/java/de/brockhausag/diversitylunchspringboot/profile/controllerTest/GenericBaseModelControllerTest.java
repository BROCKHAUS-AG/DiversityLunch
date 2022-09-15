package de.brockhausag.diversitylunchspringboot.profile.controllerTest;

import de.brockhausag.diversitylunchspringboot.data.BaseModelTestDataFactory;
import de.brockhausag.diversitylunchspringboot.data.TestBaseDto;
import de.brockhausag.diversitylunchspringboot.data.TestBaseEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.Mapper;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenericBaseModelControllerTest {
    private interface TestRepositoryType extends CrudRepository<TestBaseEntity, Long> {}
    private static class TestServiceType extends GenericBaseEntityService<TestBaseEntity, TestRepositoryType> {
        public TestServiceType(TestRepositoryType repository) {
            super(repository);
        }
    }

    private TestBaseEntity firstTestEntity;
    private TestBaseEntity secondTestEntity;
    private TestBaseEntity thirdTestEntity;
    private TestBaseDto firstTestDto;
    private TestBaseDto secondTestDto;
    private TestBaseDto thirdTestDto;

    @Mock
    private Mapper<TestBaseDto, TestBaseEntity> mapper;
    @Mock
    private TestServiceType service ;

    @InjectMocks
    private GenericBaseModelController<TestBaseDto,TestBaseEntity,
                TestRepositoryType,TestServiceType, Mapper<TestBaseDto, TestBaseEntity> > controller;
    
    @BeforeEach
    void setup(){
        BaseModelTestDataFactory factory = new BaseModelTestDataFactory();
        firstTestEntity = factory.buildFirstEntity();
        secondTestEntity = factory.buildSecondEntity();
        thirdTestEntity = factory.buildThirdEntity();
        firstTestDto = factory.buildFirstDto();
        secondTestDto = factory.buildSecondDto();
        thirdTestDto = factory.buildThirdDto();
    }

    @Test
    void testGetOne_withExistingId_returnsOkWithTestBaseDto() {
        //Arrange
        TestBaseDto expectedDto = firstTestDto;
        TestBaseEntity existingEntity = firstTestEntity;
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
        List<TestBaseEntity> entityList = Arrays.asList(firstTestEntity, secondTestEntity, thirdTestEntity);
        List<TestBaseDto> dtoList =Arrays.asList(firstTestDto, secondTestDto,thirdTestDto);

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


}
