package de.brockhausag.diversitylunchspringboot.profile.serviceTest;

import de.brockhausag.diversitylunchspringboot.dataFactories.HobbyTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.profile.logic.HobbyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HobbyServiceTest {
    @InjectMocks
    private HobbyService service;

    private final HobbyTestDataFactory factory = new HobbyTestDataFactory();
    private final long accountId = 42;
    //12 tests
    @Test
    void test_inputValidEntity_expectsEntitySaved(){

    }
}
