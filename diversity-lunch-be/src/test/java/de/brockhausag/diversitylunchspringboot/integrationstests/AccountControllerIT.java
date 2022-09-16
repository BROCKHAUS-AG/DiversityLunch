package de.brockhausag.diversitylunchspringboot.integrationstests;

import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(profiles = {"Test", "IssuerLocationTest"})
@Import(de.brockhausag.diversitylunchspringboot.config.SecurityConfig.class)
class AccountControllerIT {

    private final ProfileTestdataFactory profileTestdataFactory = new ProfileTestdataFactory();

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext appContext;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(appContext).apply(springSecurity()).build();
    }


    @Test
    void testGetAccount_WithUnauthorizedToken_ThenUnauthorized() throws Exception {
        mockMvc.perform(
                get("/api/account")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileTestdataFactory.getTokenStringFromId("Test"))
        ).andExpect(status().isUnauthorized());
    }
}
