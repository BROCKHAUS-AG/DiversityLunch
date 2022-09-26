package de.brockhausag.diversitylunchspringboot.integrationstests;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.repository.AccountRepository;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.config.SecurityConfig;
import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.service.MicrosoftGraphService;
import de.brockhausag.diversitylunchspringboot.profile.logic.*;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.*;
import de.brockhausag.diversitylunchspringboot.profile.data.ProfileRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

@Import(SecurityConfig.class)
@SpringBootTest
@ActiveProfiles("Test")
@SqlGroup({
        @Sql(scripts = "classpath:integrationstests/insert_test_data.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:integrationstests/delete_test_data.sql", executionPhase = AFTER_TEST_METHOD)
})
class ProfileControllerIT {

    private final ProfileTestdataFactory profileFactory = new ProfileTestdataFactory();
    private MockMvc mockMvc;

    private ProfileEntity profileEntity1;

    private AccountEntity accountEntity1;
    private AccountEntity accountEntity2;

    @Autowired
    private AccountRepository accountRepository;

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

    @Mock
    private MicrosoftGraphService microsoftGraphService;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(appContext)
                .apply(springSecurity())
                .build();


        when(microsoftGraphService.getGroups()).thenReturn(Optional.of(new ArrayList<>()));
        // profileEntity1 = profileService.createProfile(tmpProfileEntity, accountEntity1.getId()).orElseThrow();

        accountEntity2 = accountService.getOrCreateAccount(testProfile.getEmail());
        testProfile = profileService.createProfile(testProfile, accountEntity2.getId()).orElseThrow();
    }

    @AfterEach
    void afterEach() {
        accountRepository.deleteAll();
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
                .andExpect(jsonPath("$.email").value(profileEntity1.getEmail()))
                .andExpect(jsonPath("$.birthYear").value(profileEntity1.getBirthYear()))
                .andExpect(jsonPath("$.project.descriptor").value(profileEntity1.getProject().getDescriptor()))
                .andExpect(jsonPath("$.diet.descriptor").value(profileEntity1.getDiet().getDescriptor()))
                .andExpect(jsonPath("$.education.descriptor").value(profileEntity1.getEducation().getDescriptor()))
                .andExpect(jsonPath("$.gender.descriptor").value(profileEntity1.getGender().getDescriptor()))
                .andExpect(jsonPath("$.hobby.descriptor").value(profileEntity1.getHobby().getDescriptor()))
                .andExpect(jsonPath("$.hobby.category.descriptor").value(profileEntity1.getHobby().getCategory().getDescriptor()))
                .andExpect(jsonPath("$.motherTongue.descriptor").value(profileEntity1.getMotherTongue().getDescriptor()))
                .andExpect(jsonPath("$.originCountry.descriptor").value(profileEntity1.getOriginCountry().getDescriptor()))
                .andExpect(jsonPath("$.religion.descriptor").value(profileEntity1.getReligion().getDescriptor()))
                .andExpect(jsonPath("$.workExperience.descriptor").value(profileEntity1.getWorkExperience().getDescriptor()));
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
    void testCreateProfile_withWrongId_thenForbidden() throws Exception {

        AccountEntity account = accountService.getOrCreateAccount("irgendwas");
        ProfileDto createProfileDto = profileFactory.buildDto(1);
        String profileJSON = objectMapper.writeValueAsString(createProfileDto);

        mockMvc.perform(
                post("/api/profiles/byAccount/" + (account.getId() + 1))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromId(account.getUniqueName()))
                        .contentType(MediaType.APPLICATION_JSON).content(profileJSON)
        ).andExpect(status().isForbidden());
    }

    @Test
    void testPutProfile_withWrongId_thenForbidden() throws Exception {

        ProfileDto profileDto = profileFactory.buildDto(1);
        String profileJSON = objectMapper.writeValueAsString(profileDto);

        mockMvc
                .perform(
                        put("/api/profiles/" + profileEntity1.getId())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromId(accountEntity2.getUniqueName()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(profileJSON)
                ).andExpect(status().isForbidden());
    }

    ///// DATA
    @Autowired
    private CountryService countryService;
    @Autowired
    private DietService dietService;
    @Autowired
    private EducationService educationService;
    @Autowired
    private GenderService genderService;
    @Autowired
    private HobbyService hobbyService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ReligionService religionService;
    @Autowired
    private WorkExperienceService workExperienceService;
    private ProfileEntity testProfile;

    private void setFreshProfile() {
        final Long id = 0L;
        final String name = "Max Mustermann";
        final String email = "Max@Mustermann.de";
        final int birthyear = 1996;
        final CountryEntity originCountry = countryService.getAllEntities().get(0);
        final DietEntity diet = dietService.getAllEntities().get(0);
        final EducationEntity education = educationService.getAllEntities().get(0);
        final GenderEntity gender = genderService.getAllEntities().get(0);
        final LanguageEntity motherTongue = languageService.getAllEntities().get(0);
        final ProjectEntity project = projectService.getAllEntities().get(0);
        final ReligionEntity religion = religionService.getAllEntities().get(0);
        final WorkExperienceEntity workExperience = workExperienceService.getAllEntities().get(0);
        final HobbyEntity hobby = hobbyService.getAllEntities().get(0);


        testProfile = new ProfileEntity(id,
                name,
                email,
                birthyear,
                originCountry,
                diet,
                education,
                gender,
                motherTongue,
                project,
                religion,
                workExperience,
                hobby);
    }


}
