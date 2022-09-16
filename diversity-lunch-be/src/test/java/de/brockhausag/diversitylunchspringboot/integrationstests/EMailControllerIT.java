package de.brockhausag.diversitylunchspringboot.integrationstests;

import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.config.SecurityConfig;
import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchMailProperties;
import de.brockhausag.diversitylunchspringboot.security.DiversityLunchSecurityExpressionRoot;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RequiredArgsConstructor
@ActiveProfiles("Test")
@Import(SecurityConfig.class)
public class EMailControllerIT {

    @MockBean
    private DiversityLunchMailProperties diversityLunchMailProperties;
    @MockBean
    private DiversityLunchSecurityExpressionRoot diversityLunchSecurityExpressionRoot;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ProfileService profileService;

    private final ProfileTestdataFactory profileTestdataFactory = new ProfileTestdataFactory();

    private MockMvc mockMvc;

    private MockMvc mockMvcSecurity;

    @Autowired
    private WebApplicationContext appContext;

    @SneakyThrows
    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(appContext).build();

        mockMvcSecurity = MockMvcBuilders.webAppContextSetup(appContext).apply(springSecurity()).build();
        // Nach jedem Test soll die Test Mail geloescht werden
        HttpUriRequest request = new HttpDelete( "http://localhost:8025/api/v1/messages");
        CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
    }


    @SneakyThrows
    @Test
    public void testSendTestMail_expectedToNotThrowException() {
        when(this.diversityLunchMailProperties.getSender()).thenReturn("diversitylunchtest@brockhaus-ag.de");


        mockMvc.perform(
                post("/mailing/sendTestMail")
        ).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void testSendTestMail_expectedCorrectTestContentInMail() {
        //Send email
        when(this.diversityLunchMailProperties.getSender()).thenReturn("diversitylunchtest@brockhaus-ag.de");

        mockMvc.perform(
                post("/mailing/sendTestMail")
        );

        // Prepare request to Mailhog
        HttpUriRequest request = new HttpGet( "http://localhost:8025/api/v2/messages");

        //Send request to Mailhog
        CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

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
        Assertions.assertEquals("test@test.de",to);
        Assertions.assertEquals("diversitylunchtest@brockhaus-ag.de", from);
        Assertions.assertEquals("Testsubject",subject);
        Assertions.assertEquals("Hallo :)", body);
        Assertions.assertEquals(1, amount);
    }

    @SneakyThrows
    @Test
    public void testAuthenticationSendTestMailToLoggedInUser_withValidId_expectedOkStatus() {
        when(this.diversityLunchMailProperties.getSender()).thenReturn("diversitylunchtest@brockhaus-ag.de");
        ProfileEntity tmpProfileEntity = profileTestdataFactory.createEntity();
        AccountEntity accountEntity = accountService.getOrCreateAccount(tmpProfileEntity.getEmail());
        profileService.createProfile(tmpProfileEntity, accountEntity.getId()).orElseThrow();
        long id = tmpProfileEntity.getId();
        String url = "/mailing/sendTestMailToUser?id=" + id;

        performRequestWithToken(url, accountEntity).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void testAuthenticationSendTestMailToLoggedInUser_withInvalidId_expectedForbiddenStatus() {
        when(this.diversityLunchMailProperties.getSender()).thenReturn("diversitylunchtest@brockhaus-ag.de");
        ProfileEntity tmpProfileEntity = profileTestdataFactory.createEntity();
        AccountEntity accountEntity = accountService.getOrCreateAccount(tmpProfileEntity.getEmail());
        profileService.createProfile(tmpProfileEntity, accountEntity.getId()).orElseThrow();
        long id = tmpProfileEntity.getId() + 1;
        String url = "/mailing/sendTestMailToUser?id=" + id;

        performRequestWithToken(url, accountEntity).andExpect(status().isForbidden());
    }

    private ResultActions performRequestWithToken(String path, AccountEntity accountEntity) throws Exception {
        return mockMvcSecurity.perform(
                post(path)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileTestdataFactory.getTokenStringFromId(accountEntity.getUniqueName()))
        );
    }
}
