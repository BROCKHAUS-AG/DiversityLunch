package de.brockhausag.diversitylunchspringboot.dimensions.services;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.MultiselectDimensionTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.MultiselectDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.MultiselectDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.MultiselectDimensionService;
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
public class MultiselectDimensionServiceTest {
    // TODO: Add tests when service gets more complex
    @Mock
    private MultiselectDimensionRepository dimensionRepository;
    @Mock
    private MultiselectDimensionSelectableOptionRepository selectableRepository;
    @Mock
    private ProfileService profileService;
    @InjectMocks
    private MultiselectDimensionService dimensionService;

    private MultiselectDimension multiselectDimension;


    @BeforeEach
    void setup() {
        MultiselectDimensionTestDataFactory multiselectDimensionTestDataFactory = new MultiselectDimensionTestDataFactory();
        multiselectDimension = multiselectDimensionTestDataFactory.buildEntity(1);
    }

    @Test
    void testAddSelectableOption_withNotExistingOption_shouldReturnTrue() {
        // Assemble
        MultiselectDimensionSelectableOption inputSelectableOption = multiselectDimension.getSelectableValues().iterator().next();

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
        MultiselectDimensionSelectableOption inputSelectableOption = multiselectDimension.getSelectableValues().iterator().next();

        when(selectableRepository.existsById(inputSelectableOption.getId())).thenReturn(true);

        // Act
        boolean actualOutput = dimensionService.addSelectableOption(inputSelectableOption);

        // Assert
        assertFalse(actualOutput);
    }

    @Test
    void testAddSelectableOption_withDuplicateName_shouldReturnFalse() {
        // Assemble
        MultiselectDimensionSelectableOption inputSelectableOption = multiselectDimension.getSelectableValues().iterator().next();

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
        MultiselectDimensionSelectableOption inputSelectableOption = multiselectDimension.getSelectableValues().iterator().next();

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
        MultiselectDimensionSelectableOption inputSelectableOption = multiselectDimension.getSelectableValues().iterator().next();

        when(selectableRepository.existsById(inputSelectableOption.getId())).thenReturn(false);

        // Act
        boolean actualOutput = dimensionService.updateSelectableOption(inputSelectableOption);

        // Assert
        assertFalse(actualOutput);
    }

    @Test
    void testUpdateSelectableOption_withDuplicateName_shouldReturnFalse() {
        // Assemble
        MultiselectDimensionSelectableOption inputSelectableOption = multiselectDimension.getSelectableValues().iterator().next();

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
        MultiselectDimensionSelectableOption inputSelectableOption = List.copyOf(multiselectDimension.getSelectableValues()).get(1);
        ProfileTestdataFactory profileTestdataFactory = new ProfileTestdataFactory();
        ProfileEntity profile = profileTestdataFactory.buildEntity(1);

        when(selectableRepository.findById(inputSelectableOption.getId())).thenReturn(Optional.of(inputSelectableOption));
        doNothing().when(selectableRepository).deleteById(inputSelectableOption.getId());
        when(selectableRepository.existsById(inputSelectableOption.getId())).thenReturn(false);
        when(dimensionRepository.findByDimensionCategory(multiselectDimension.getDimensionCategory())).thenReturn(Optional.of(multiselectDimension));
        when(profileService.getAllProfilesWithSelectedMultiselectOption(inputSelectableOption)).thenReturn(List.of(profile));
        when(profileService.updateProfile(profile)).thenReturn(Optional.of(profile));

        // Act
        boolean actualOutput = dimensionService.deleteSelectableOptionById(inputSelectableOption.getId());

        // Assert
        assertTrue(actualOutput);
        assertTrue(profile.isWasChangedByAdmin());
        assertFalse(profile.getSelectedMultiselectValues().get(multiselectDimension).getSelectedOptions().contains(inputSelectableOption));
    }

    @Test
    void testDeleteSelectableOptionById_withNotExistingOption_shouldReturnTrue() {
        // Assemble
        MultiselectDimensionSelectableOption inputSelectableOption = List.copyOf(multiselectDimension.getSelectableValues()).get(1);

        when(selectableRepository.findById(inputSelectableOption.getId())).thenReturn(Optional.empty());

        // Act
        boolean actualOutput = dimensionService.deleteSelectableOptionById(inputSelectableOption.getId());

        // Assert
        assertTrue(actualOutput);
    }
}
