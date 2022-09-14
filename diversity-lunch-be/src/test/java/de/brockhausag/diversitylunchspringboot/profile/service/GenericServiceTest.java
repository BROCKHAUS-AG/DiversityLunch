package de.brockhausag.diversitylunchspringboot.profile.service;

import de.brockhausag.diversitylunchspringboot.profile.utils.BaseEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.GenericServiceForBaseEntity;
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
public class GenericServiceTest {

    private interface TestEntityType extends BaseEntity {}

    private interface TestRepositoryType extends CrudRepository<TestEntityType, Long>{}

    @Mock
    private TestRepositoryType testRepository;

    @Mock
    private TestEntityType firstTestEntity;

    @Mock
    private TestEntityType secondTestEntity;

    @InjectMocks
    private GenericServiceForBaseEntity<TestRepositoryType, TestEntityType> service;

    @Test
    void testDeleteEntityById_withExistingId_returnsTrue(){
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
    void testGetAllEntities_withNoEntitiesInRepository_returnsEmptyList(){
        //Arrange
        List<TestEntityType> expectedList = Collections.emptyList();

        when(testRepository.findAll()).thenReturn((Iterable<TestEntityType>) expectedList);
        //Act
        List<TestEntityType> actualList = service.getAllEntities();

        //Assert
        assertEquals(expectedList, actualList);
    }

    @Test
    void testGetAllEntities_withTwoEntitiesInRepository_returnsListOfTwoEntities(){
        //Arrange
        List<TestEntityType> expectedList = Arrays.asList(firstTestEntity, secondTestEntity);

        when(testRepository.findAll()).thenReturn((Iterable<TestEntityType>) expectedList);
        //Act
        List<TestEntityType> actualList = service.getAllEntities();

        //Assert
        assertEquals(expectedList, actualList);
    }


}
