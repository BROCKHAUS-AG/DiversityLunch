package de.brockhausag.diversitylunchspringboot.integrationstests;

import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchMailProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
        // todo lazevedo 17.08.22 call mailhog v1 api to delete all prior messages
    }

    @SneakyThrows
    @Test
    public void testSendTestMail_expectedToNotThrowException() {
        when(this.diversityLunchMailProperties.getSender()).thenReturn("test@test.de");

        mockMvc.perform(
                post("/mailing/sendTestMail")
        ).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void testSendTestMail_expectedToNotThrowException2() {
        // todo lazevedo 17.08.22 rename and finish test
        // 1. make sure all mails are deleted
        // 2. send mail
        // 3. check if received by mailhog correctly (sender, absender, subject, body, nur eine mail)

        // Given
        HttpUriRequest request = new HttpGet( "http://localhost:8025/api/v1/messages");

        // When
        CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        String responseBody = EntityUtils.toString(httpResponse.getEntity());
        JSONObject jsonResponse = new JSONObject(responseBody);
    }
}
