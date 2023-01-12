package de.brockhausag.diversitylunchspringboot.dimensions.services;

import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class BasicDimensionServiceTest {

    @InjectMocks
    private BasicDimensionService basicDimensionService;

    @Mock
    private BasicDimensionRepository repository;
    @Mock
    private BasicDimensionSelectableOptionRepository selectableRepository;
    @Test
    void workToDo(){
        assertTrue(false);
    }
}
