package de.brockhausag.diversitylunchspringboot.integrationstests;

import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.config.SecurityConfig;
import de.brockhausag.diversitylunchspringboot.integrationDataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.service.MicrosoftGraphService;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchMailProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RequiredArgsConstructor
@ActiveProfiles("Test")
@Import(SecurityConfig.class)
@SqlGroup({
        @Sql(scripts = "classpath:integrationstests/insert_test_data.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:integrationstests/delete_test_data.sql", executionPhase = AFTER_TEST_METHOD)
})
public class EMailControllerIT {

    @MockBean
    private DiversityLunchMailProperties diversityLunchMailProperties;
    @Mock
    private MicrosoftGraphService microsoftGraphService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileTestdataFactory profileTestdataFactory;

    private MockMvc mockMvc;

    private MockMvc mockMvcSecurity;

    @Autowired
    private WebApplicationContext appContext;
    private ProfileEntity myProfileEntity;
    private AccountEntity accountEntity;

    @SneakyThrows
    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(appContext).build();

        mockMvcSecurity = MockMvcBuilders.webAppContextSetup(appContext).apply(springSecurity()).build();

        when(this.diversityLunchMailProperties.getSender()).thenReturn("test@example.com");
        when(microsoftGraphService.getGroups()).thenReturn(Optional.of(new ArrayList<>()));
        myProfileEntity = profileTestdataFactory.createNewMaxProfile();
        accountEntity = accountService.getOrCreateAccount(myProfileEntity.getEmail());
        myProfileEntity = profileService.createProfile(myProfileEntity, accountEntity.getId()).orElseThrow();
    }

    @SneakyThrows
    @AfterEach
    void cleanup() {
        // Nach jedem Test soll die Test Mail geloescht werden
        HttpUriRequest request = new HttpDelete("http://localhost:8025/api/v1/messages");
        HttpClientBuilder.create().build().execute(request);
    }

    @SneakyThrows
    @Test
    public void testSendTestMail_expectedToNotThrowException() {
        when(this.diversityLunchMailProperties.getSender()).thenReturn("test@example.com");


        mockMvc.perform(
                post("/api/mailing/sendTestMail")
        ).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void testSendTestMail_expectedCorrectTestContentInMail() {
        //Send email
        when(this.diversityLunchMailProperties.getSender()).thenReturn("test@example.com");

        mockMvc.perform(
                post("/api/mailing/sendTestMail")
        );

        // Prepare request to Mailhog
        HttpUriRequest request = new HttpGet("http://localhost:8025/api/v2/messages");

        //Send request to Mailhog
        CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        //Get values from response
        String responseBody = EntityUtils.toString(httpResponse.getEntity());
        JSONObject jsonResponse = new JSONObject(responseBody);
        String to = jsonResponse.getJSONArray("items").getJSONObject(0).getJSONObject("Content")
                .getJSONObject("Headers").getJSONArray("To").getString(0);
        String from = jsonResponse.getJSONArray("items").getJSONObject(0).getJSONObject("Content")
                .getJSONObject("Headers").getJSONArray("From").getString(0);
        String subject = jsonResponse.getJSONArray("items").getJSONObject(0).getJSONObject("Content")
                .getJSONObject("Headers").getJSONArray("Subject").getString(0);
        String body = jsonResponse.getJSONArray("items").getJSONObject(0).getJSONObject("MIME")
                .getJSONArray("Parts").getJSONObject(0).getJSONObject("MIME")
                .getJSONArray("Parts").getJSONObject(0).getJSONObject("MIME")
                .getJSONArray("Parts").getJSONObject(0).getString("Body");
        int amount = jsonResponse.getInt("total");

        //Assert
        Assertions.assertEquals("test@test.de", to);
        Assertions.assertEquals("test@example.com", from);
        Assertions.assertEquals("Testsubject", subject);
        Assertions.assertNotEquals("", body);
        Assertions.assertNotEquals(null, body);
        Assertions.assertEquals("Datum: " + LocalDate.now(), body.substring(0, 17));
        Assertions.assertEquals(1, amount);
    }

    @SneakyThrows
    @Test
    public void testAuthenticationSendTestMailToLoggedInUser_withValidId_expectedOkStatus() {
        Long id = myProfileEntity.getId();
        String url = "/api/mailing/sendTestMailToUser?id=" + id;

        performRequestWithToken(url, accountEntity).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void testAuthenticationSendTestMailToLoggedInUser_withInvalidId_expectedForbiddenStatus() {
        long id = myProfileEntity.getId() + 1;
        String url = "/api/mailing/sendTestMailToUser?id=" + id;

        performRequestWithToken(url, accountEntity).andExpect(status().isForbidden());
    }

    @SneakyThrows
    @Test
    public void testAuthenticationSendTenTestMailsToUser_withValidId_expectedOkStatus() {
        Long id = myProfileEntity.getId();
        String url = "/api/mailing/sendTenTestMailsToUser?id=" + id;

        performRequestWithToken(url, accountEntity).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void testAuthenticationSendTenTestMailsToLoggedInUser_withInvalidId_expectedForbiddenStatus() {
        long id = myProfileEntity.getId() + 1;
        String url = "/api/mailing/sendTenTestMailsToUser?id=" + id;

        performRequestWithToken(url, accountEntity).andExpect(status().isForbidden());
    }

    private ResultActions performRequestWithToken(String path, AccountEntity accountEntity) throws Exception {
        return mockMvcSecurity.perform(
                post(path)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileTestdataFactory.getTokenStringFromId(accountEntity.getOid()))
        );
    }


}
