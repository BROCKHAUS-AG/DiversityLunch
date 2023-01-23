package de.brockhausag.diversitylunchspringboot.dimensions.services;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.BasicDimensionTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.services.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasicDimensionServiceTest {
    // TODO: Add tests when service gets more complex
    @Mock
    private BasicDimensionRepository dimensionRepository;
    @Mock
    private BasicDimensionSelectableOptionRepository selectableRepository;
    @Mock
    private ProfileService profileService;
    @InjectMocks
    private BasicDimensionService dimensionService;

    private BasicDimension basicDimension;


    @BeforeEach
    void setup() {
        BasicDimensionTestDataFactory basicDimensionTestDataFactory = new BasicDimensionTestDataFactory();
        basicDimension = basicDimensionTestDataFactory.buildEntity(1);
    }

    @Test
    void testAddSelectableOption_withNotExistingOption_shouldReturnTrue() {
        // Assemble
        BasicDimensionSelectableOption inputSelectableOption = basicDimension.getSelectableValues().iterator().next();

        when(selectableRepository.existsById(inputSelectableOption.getId())).thenReturn(false);
        when(selectableRepository.save(inputSelectableOption)).thenReturn(inputSelectableOption);

        // Act
        boolean actualOutput = dimensionService.addSelectableOption(inputSelectableOption);

        // Assert
        assertTrue(actualOutput);
        verify(selectableRepository, times(1)).save(inputSelectableOption);
    }

    @Test
    void testAddSelectableOption_withExistingOption_shouldReturnFalse() {
        // Assemble
        BasicDimensionSelectableOption inputSelectableOption = basicDimension.getSelectableValues().iterator().next();

        when(selectableRepository.existsById(inputSelectableOption.getId())).thenReturn(true);

        // Act
        boolean actualOutput = dimensionService.addSelectableOption(inputSelectableOption);

        // Assert
        assertFalse(actualOutput);
    }

    @Test
    void testAddSelectableOption_withDuplicateName_shouldReturnFalse() {
        // Assemble
        BasicDimensionSelectableOption inputSelectableOption = basicDimension.getSelectableValues().iterator().next();

        when(selectableRepository.existsById(inputSelectableOption.getId())).thenReturn(false);
        when(selectableRepository.save(inputSelectableOption)).thenThrow(new DataIntegrityViolationException(""));

        // Act
        boolean actualOutput = dimensionService.addSelectableOption(inputSelectableOption);

        // Assert
        assertFalse(actualOutput);
        verify(selectableRepository, times(1)).save(inputSelectableOption);
    }

    @Test
    void testUpdateSelectableOption_withExistingOption_shouldReturnTrue() {
        // Assemble
        BasicDimensionSelectableOption inputSelectableOption = basicDimension.getSelectableValues().iterator().next();

        when(selectableRepository.existsById(inputSelectableOption.getId())).thenReturn(true);
        when(selectableRepository.save(inputSelectableOption)).thenReturn(inputSelectableOption);

        // Act
        boolean actualOutput = dimensionService.updateSelectableOption(inputSelectableOption);

        // Assert
        assertTrue(actualOutput);
        verify(selectableRepository, times(1)).save(inputSelectableOption);
    }

    @Test
    void testUpdateSelectableOption_withNotExistingOption_shouldReturnFalse() {
        // Assemble
        BasicDimensionSelectableOption inputSelectableOption = basicDimension.getSelectableValues().iterator().next();

        when(selectableRepository.existsById(inputSelectableOption.getId())).thenReturn(false);

        // Act
        boolean actualOutput = dimensionService.updateSelectableOption(inputSelectableOption);

        // Assert
        assertFalse(actualOutput);
    }

    @Test
    void testUpdateSelectableOption_withDuplicateName_shouldReturnFalse() {
        // Assemble
        BasicDimensionSelectableOption inputSelectableOption = basicDimension.getSelectableValues().iterator().next();

        when(selectableRepository.existsById(inputSelectableOption.getId())).thenReturn(true);
        when(selectableRepository.save(inputSelectableOption)).thenThrow(new DataIntegrityViolationException(""));

        // Act
        boolean actualOutput = dimensionService.updateSelectableOption(inputSelectableOption);

        // Assert
        assertFalse(actualOutput);
        verify(selectableRepository, times(1)).save(inputSelectableOption);
    }

    @Test
    void testDeleteSelectableOptionById_withExistingOption_shouldReturnTrue() {
        // Assemble
        BasicDimensionSelectableOption inputSelectableOption = List.copyOf(basicDimension.getSelectableValues()).get(1);
        ProfileTestdataFactory profileTestdataFactory = new ProfileTestdataFactory();
        ProfileEntity profile = profileTestdataFactory.buildEntity(1);

        when(selectableRepository.findById(inputSelectableOption.getId())).thenReturn(Optional.of(inputSelectableOption));
        doNothing().when(selectableRepository).deleteById(inputSelectableOption.getId());
        when(selectableRepository.existsById(inputSelectableOption.getId())).thenReturn(false);
        when(dimensionRepository.findByDimensionCategory(basicDimension.getDimensionCategory())).thenReturn(Optional.of(basicDimension));
        when(profileService.getAllProfilesWithSelectedBasicOption(inputSelectableOption)).thenReturn(List.of(profile));
        when(profileService.updateProfile(profile)).thenReturn(Optional.of(profile));

        // Act
        boolean actualOutput = dimensionService.deleteSelectableOptionById(inputSelectableOption.getId());

        // Assert
        assertTrue(actualOutput);
        assertTrue(profile.isWasChangedByAdmin());
        assertNotEquals(inputSelectableOption, profile.getSelectedBasicValues().get(basicDimension));
        assertEquals(basicDimension.getDefaultValue(), profile.getSelectedBasicValues().get(basicDimension));
    }

    @Test
    void testDeleteSelectableOptionById_withNotExistingOption_shouldReturnTrue() {
        // Assemble
        BasicDimensionSelectableOption inputSelectableOption = List.copyOf(basicDimension.getSelectableValues()).get(1);

        when(selectableRepository.findById(inputSelectableOption.getId())).thenReturn(Optional.empty());

        // Act
        boolean actualOutput = dimensionService.deleteSelectableOptionById(inputSelectableOption.getId());

        // Assert
        assertTrue(actualOutput);
    }

    @Test
    void testDeleteSelectableOptionById_withDefaultOption_shouldReturnFalse() {
        // Assemble
        BasicDimensionSelectableOption inputSelectableOption = basicDimension.getDefaultValue();

        when(selectableRepository.findById(inputSelectableOption.getId())).thenReturn(Optional.of(inputSelectableOption));
        when(dimensionRepository.findByDimensionCategory(basicDimension.getDimensionCategory())).thenReturn(Optional.of(basicDimension));

        // Act
        boolean actualOutput = dimensionService.deleteSelectableOptionById(inputSelectableOption.getId());

        // Assert
        assertFalse(actualOutput);
    }
}
