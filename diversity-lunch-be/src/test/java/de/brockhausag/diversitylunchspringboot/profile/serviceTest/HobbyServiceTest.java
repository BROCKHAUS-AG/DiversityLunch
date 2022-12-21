package de.brockhausag.diversitylunchspringboot.profile.serviceTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.HobbyTestDataFactory;
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
    void setup() {
        this.factory = new HobbyTestDataFactory();
    }

    @Test
    void testDeleteEntityById_withExistingId_returnsTrue() {
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
    void testGetAllEntities_withNoEntitiesInRepository_returnsEmptyList() {
        //Arrange
        List<HobbyEntity> expectedList = Collections.emptyList();

        when(repository.findAll()).thenReturn(expectedList);
        //Act
        List<HobbyEntity> actualList = service.getAllEntities();

        //Assert
        assertEquals(expectedList, actualList);
        assertEquals(0, actualList.size());
    }

    @Test
    void testGetAllEntities_withThreeEntitiesInRepository_returnsListOfThreeEntities() {
        //Arrange
        List<HobbyEntity> expectedList = Stream.of(1, 2, 3).map(this.factory::buildEntity).toList();

        when(repository.findAll()).thenReturn(expectedList);

        //Act
        List<HobbyEntity> actualList = service.getAllEntities();

        //Assert
        assertEquals(expectedList, actualList);
        assertEquals(expectedList.size(), actualList.size());
    }

    @Test
    void testGetEntitySelectionByIds_withThreeExistingIds_returnThreeEntities() {
        // Arrange
        List<HobbyEntity> expectedList = Stream.of(1, 2, 3).map(this.factory::buildEntity).toList();
        List<Long> existingIds = Stream.of(30L, 40L, 42L).toList();

        when(repository.findAllById(existingIds)).thenReturn(expectedList);

        // Act
        List<HobbyEntity> actualList = service.getEntitySelectionByIds(existingIds);

        // Assert
        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList, actualList);
    }

    @Test
    void testGetEntitySelectionByIds_withThreeNotExistingIds_returnEmptyList() {
        // Arrange
        List<Long> notExistingIds = Stream.of(30L, 40L, 42L).toList();
        when(repository.findAllById(notExistingIds)).thenReturn(Collections.emptyList());

        // Act
        List<HobbyEntity> actualList = service.getEntitySelectionByIds(notExistingIds);

        //Assert
        assertEquals(0, actualList.size());
        assertEquals(Collections.emptyList(), actualList);
    }

    @Test
    void testGetEntitySelectionByIds_withEmptyList_returnEmptyList() {
        // Arrange
        when(repository.findAllById(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Act
        List<HobbyEntity> actualList = service.getEntitySelectionByIds(Collections.emptyList());

        //Assert
        assertEquals(0, actualList.size());
        assertEquals(Collections.emptyList(), actualList);
    }
}
