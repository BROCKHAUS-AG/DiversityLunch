package de.brockhausag.diversitylunchspringboot.integrationstests;

import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchMailProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RequiredArgsConstructor
public class EMailControllerIT {

    @MockBean
    private DiversityLunchMailProperties diversityLunchMailProperties;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext appContext;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(appContext).build();
    }

    @SneakyThrows
    @Test
    public void testSendTestMail_expectedToNotThrowException() {
        when(this.diversityLunchMailProperties.getSender()).thenReturn("test@test.de");

        mockMvc.perform(
                post("/mailing/sendTestMail")
        ).andExpect(status().isOk());
    }
}
