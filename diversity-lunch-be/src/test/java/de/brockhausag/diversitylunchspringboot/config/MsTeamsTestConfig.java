package de.brockhausag.diversitylunchspringboot.config;

import com.microsoft.graph.models.Event;
import de.brockhausag.diversitylunchspringboot.meeting.service.MicrosoftGraphService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("Test")
@Configuration
public class MsTeamsTestConfig {

    @Bean
    @Primary
    public MicrosoftGraphService microsoftGraphService() {
        final MicrosoftGraphService service  = Mockito.mock(MicrosoftGraphService.class);
        final Event customEvent = new Event();
        customEvent.id = "Test12345";
        Mockito.when(service.createEvent(Mockito.any())).thenReturn(customEvent);
        return service;
    }

}
