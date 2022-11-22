package de.brockhausag.diversitylunchspringboot.profile.serviceTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.HobbyTestDataFactory;
import de.brockhausag.diversitylunchspringboot.profile.data.HobbyRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.HobbyService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HobbyServiceTest {
    
    private HobbyTestDataFactory factory;
    @Mock
    private HobbyRepository repository;
    @InjectMocks
    private HobbyService service;

    @BeforeEach
    void setup(){
        this.factory = new HobbyTestDataFactory();
    }

    @Test
    void testDeleteEntityById_withExistingId_returnsTrue(){
        //Arrange
        Long existingId = 42L;

        when(repository.existsById(existingId)).thenReturn(true);

        //Act
        boolean actual = service.deleteEntityById(existingId);

        //Assert
        assertTrue(actual);
    }

    @Test
    void testDeleteEntityById_withNotExistingId_returnsFalse() {
        //Arrange
        Long notExistingId = 666L;

        when(repository.existsById(notExistingId)).thenReturn(false);

        //Act
        boolean actual = service.deleteEntityById(notExistingId);

        //Assert
        assertFalse(actual);
    }

    @Test
    void testGetAllEntities_withNoEntitiesInRepository_returnsEmptyList(){
        //Arrange
        List<HobbyEntity> expectedList = Collections.emptyList();

        when(repository.findAll()).thenReturn((Iterable<HobbyEntity>) expectedList);
        //Act
        List<HobbyEntity> actualList = service.getAllEntities();

        //Assert
        assertEquals(expectedList, actualList);
        assertEquals(0,actualList.size());
    }

    @Test
    void testGetAllEntities_withThreeEntitiesInRepository_returnsListOfThreeEntities(){
        //Arrange
        List<HobbyEntity> expectedList = Stream.of(1, 2, 3).map(this.factory::buildEntity).toList();

        when(repository.findAll()).thenReturn((Iterable<HobbyEntity>) expectedList);

        //Act
        List<HobbyEntity> actualList = service.getAllEntities();

        //Assert
        assertEquals(expectedList, actualList);
        assertEquals(expectedList.size(),actualList.size());
    }


}
