package de.brockhausag.diversitylunchspringboot.integrationstests;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.repository.AccountRepository;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.config.SecurityConfig;
import de.brockhausag.diversitylunchspringboot.data.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.profile.model.*;
import de.brockhausag.diversitylunchspringboot.profile.repository.ProfileRepository;
import de.brockhausag.diversitylunchspringboot.profile.service.ProfileService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

@Import(SecurityConfig.class)
@SpringBootTest
@ActiveProfiles("Test")
class ProfileControllerIT {

    private final ProfileTestdataFactory profileFactory = new ProfileTestdataFactory();

    private MockMvc mockMvc;

    private ProfileEntity profileEntity1;
    private ProfileEntity profileEntity2;

    private AccountEntity accountEntity1;
    private AccountEntity accountEntity2;

    @Autowired
    private AccountRepository accoutRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private WebApplicationContext appContext;


    @Autowired
    private AccountService accountService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void tearUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(appContext)
                .apply(springSecurity())
                .build();

        ProfileEntity tmpProfileEntity = profileFactory.createEntity();
        accountEntity1 = accountService.getOrCreateAccount(tmpProfileEntity.getEmail());
        profileEntity1 = profileService.createProfile(tmpProfileEntity, accountEntity1.getId()).orElseThrow();


        tmpProfileEntity = profileFactory.createEntityBuilder().email("smith@web.de").build();
        accountEntity2 = accountService.getOrCreateAccount(tmpProfileEntity.getEmail());
        profileEntity2 = profileService.createProfile(tmpProfileEntity, accountEntity2.getId()).orElseThrow();
    }

    @AfterEach
    void afterEach(){
        accoutRepository.deleteAll();
        profileRepository.deleteAll();
    }

    @Test
    void testGetProfile_withValidId_thenOKWithExpectedProfile() throws Exception {

        this.mockMvc
                .perform(
                        get("/api/profiles/" + profileEntity1.getId())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromId(accountEntity1.getUniqueName()))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(profileEntity1.getName()))
                .andExpect(jsonPath("$.birthYear").value(profileEntity1.getBirthYear()))
                .andExpect(jsonPath("$.currentProject").value(profileEntity1.getCurrentProject().toString()))
                .andExpect(jsonPath("$.diet").value(profileEntity1.getDiet().toString()))
                .andExpect(jsonPath("$.education").value(profileEntity1.getEducation().toString()))
                .andExpect(jsonPath("$.email").value(profileEntity1.getEmail()))
                .andExpect(jsonPath("$.gender").value(profileEntity1.getGender().toString()))
                .andExpect(jsonPath("$.hobby").value(profileEntity1.getHobby().toString()))
                .andExpect(jsonPath("$.motherTongue").value(profileEntity1.getMotherTongue().toString()))
                .andExpect(jsonPath("$.originCountry").value(profileEntity1.getOriginCountry().toString()))
                .andExpect(jsonPath("$.religion").value(profileEntity1.getReligion().toString()))
                .andExpect(jsonPath("$.workExperience").value(profileEntity1.getWorkExperience().toString()));
    }

    @Test
    void testCreateProfile_withInvalidDto_thenBadRequest() throws Exception {

        this.mockMvc
                .perform(
                        post("/api/profiles/byAccount/" + accountEntity1.getId())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromId(accountEntity1.getUniqueName()))
                                .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Test\"}")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetProfile_withWrongId_thenForbidden() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/profiles/" + profileEntity1.getId())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromId(accountEntity2.getUniqueName()))
                ).andExpect(status().isForbidden());
    }

    @Test
    void testGetProfile_withInvalidToken_thenUnauthorized() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/profiles/" + profileEntity1.getId())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromIdWrongKey(accountEntity1.getUniqueName()))
                ).andExpect(status().isUnauthorized());
    }

    @Test
    void testCreateProfile_withWrongId_thenForbidden() throws Exception{

        AccountEntity account = accountService.getOrCreateAccount("irgendwas");
        CreateProfileDto createProfileDto = profileFactory.createDto();
        String profileJSON = objectMapper.writeValueAsString(createProfileDto);

        mockMvc.perform(
                post("/api/profiles/byAccount/" + (account.getId() + 1))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromId(account.getUniqueName()))
                        .contentType(MediaType.APPLICATION_JSON).content(profileJSON)
        ).andExpect(status().isForbidden());
    }

    @Test
    void testPutProfile_withWrongId_thenForbidden() throws Exception {

        ProfileDto profileDto = profileFactory.dto();
        String profileJSON = objectMapper.writeValueAsString(profileDto);

        mockMvc
                .perform(
                        put("/api/profiles/" + profileEntity1.getId())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromId(accountEntity2.getUniqueName()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(profileJSON)
                ).andExpect(status().isForbidden());
    }
}
