package de.brockhausag.diversitylunchspringboot.dimensions.services;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.WeightedDimensionTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.WeightedDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.WeightedDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.WeightedDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeightedDimensionServiceTest {
    // TODO: Add tests when service gets more complex
    @Mock
    private WeightedDimensionRepository dimensionRepository;
    @Mock
    private WeightedDimensionSelectableOptionRepository selectableRepository;
    @Mock
    private ProfileService profileService;
    @InjectMocks
    private WeightedDimensionService dimensionService;

    private WeightedDimension weightedDimension;


    @BeforeEach
    void setup() {
        WeightedDimensionTestDataFactory weightedDimensionTestDataFactory = new WeightedDimensionTestDataFactory();
        weightedDimension = weightedDimensionTestDataFactory.buildEntity(1);
    }

    @Test
    void testAddSelectableOption_withNotExistingOption_shouldReturnTrue() {
        // Assemble
        WeightedDimensionSelectableOption inputSelectableOption = weightedDimension.getSelectableValues().iterator().next();

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
        WeightedDimensionSelectableOption inputSelectableOption = weightedDimension.getSelectableValues().iterator().next();

        when(selectableRepository.existsById(inputSelectableOption.getId())).thenReturn(true);

        // Act
        boolean actualOutput = dimensionService.addSelectableOption(inputSelectableOption);

        // Assert
        assertFalse(actualOutput);
    }

    @Test
    void testAddSelectableOption_withDuplicateName_shouldReturnFalse() {
        // Assemble
        WeightedDimensionSelectableOption inputSelectableOption = weightedDimension.getSelectableValues().iterator().next();

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
        WeightedDimensionSelectableOption inputSelectableOption = weightedDimension.getSelectableValues().iterator().next();

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
        WeightedDimensionSelectableOption inputSelectableOption = weightedDimension.getSelectableValues().iterator().next();

        when(selectableRepository.existsById(inputSelectableOption.getId())).thenReturn(false);

        // Act
        boolean actualOutput = dimensionService.updateSelectableOption(inputSelectableOption);

        // Assert
        assertFalse(actualOutput);
    }

    @Test
    void testUpdateSelectableOption_withDuplicateName_shouldReturnFalse() {
        // Assemble
        WeightedDimensionSelectableOption inputSelectableOption = weightedDimension.getSelectableValues().iterator().next();

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
        WeightedDimensionSelectableOption inputSelectableOption = List.copyOf(weightedDimension.getSelectableValues()).get(1);
        ProfileTestdataFactory profileTestdataFactory = new ProfileTestdataFactory();
        ProfileEntity profile = profileTestdataFactory.buildEntity(1);

        when(selectableRepository.findById(inputSelectableOption.getId())).thenReturn(Optional.of(inputSelectableOption));
        doNothing().when(selectableRepository).deleteById(inputSelectableOption.getId());
        when(selectableRepository.existsById(inputSelectableOption.getId())).thenReturn(false);
        when(dimensionRepository.findByDimensionCategory(weightedDimension.getDimensionCategory())).thenReturn(Optional.of(weightedDimension));
        when(profileService.getAllProfilesWithSelectedWeightedOption(inputSelectableOption)).thenReturn(List.of(profile));
        when(profileService.updateProfile(profile)).thenReturn(Optional.of(profile));

        // Act
        boolean actualOutput = dimensionService.deleteSelectableOptionById(inputSelectableOption.getId());

        // Assert
        assertTrue(actualOutput);
        assertTrue(profile.isWasChangedByAdmin());
        assertNotEquals(inputSelectableOption, profile.getSelectedWeightedValues().get(weightedDimension));
        assertEquals(weightedDimension.getDefaultValue(), profile.getSelectedWeightedValues().get(weightedDimension));
    }

    @Test
    void testDeleteSelectableOptionById_withNotExistingOption_shouldReturnTrue() {
        // Assemble
        WeightedDimensionSelectableOption inputSelectableOption = List.copyOf(weightedDimension.getSelectableValues()).get(1);

        when(selectableRepository.findById(inputSelectableOption.getId())).thenReturn(Optional.empty());

        // Act
        boolean actualOutput = dimensionService.deleteSelectableOptionById(inputSelectableOption.getId());

        // Assert
        assertTrue(actualOutput);
    }

    @Test
    void testDeleteSelectableOptionById_withDefaultOption_shouldReturnFalse() {
        // Assemble
        WeightedDimensionSelectableOption inputSelectableOption = weightedDimension.getDefaultValue();

        when(selectableRepository.findById(inputSelectableOption.getId())).thenReturn(Optional.of(inputSelectableOption));
        when(dimensionRepository.findByDimensionCategory(weightedDimension.getDimensionCategory())).thenReturn(Optional.of(weightedDimension));

        // Act
        boolean actualOutput = dimensionService.deleteSelectableOptionById(inputSelectableOption.getId());

        // Assert
        assertFalse(actualOutput);
    }
}
