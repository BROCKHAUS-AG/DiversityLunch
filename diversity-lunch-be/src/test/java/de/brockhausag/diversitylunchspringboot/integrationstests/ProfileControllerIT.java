package de.brockhausag.diversitylunchspringboot.integrationstests;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.config.SecurityConfig;
import de.brockhausag.diversitylunchspringboot.integrationDataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.service.MicrosoftGraphService;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.mapper.ProfileMapper;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@SpringBootTest
@ActiveProfiles("Test")
@SqlGroup({
        @Sql(scripts = "classpath:integrationstests/insert_test_data.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:integrationstests/delete_test_data.sql", executionPhase = AFTER_TEST_METHOD)
})
class ProfileControllerIT {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private ProfileTestdataFactory profileFactory;
    private MockMvc mockMvc;
    private ProfileEntity myProfileEntity;
    private AccountEntity myAccountEntity;
    private AccountEntity otherAccountEntity;
    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private WebApplicationContext appContext;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ProfileService profileService;
    @Mock
    private MicrosoftGraphService microsoftGraphService;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(appContext)
                .apply(springSecurity())
                .build();

        myProfileEntity = profileFactory.createNewMaxProfile();
        ProfileEntity otherProfileEntity = profileFactory.createNewErikaProfile();
        myAccountEntity = accountService.getOrCreateAccount(myProfileEntity.getEmail());
        otherAccountEntity = accountService.getOrCreateAccount(otherProfileEntity.getEmail());

        when(microsoftGraphService.getGroups()).thenReturn(Optional.of(new ArrayList<>()));

        myProfileEntity = profileService.createProfile(myProfileEntity, myAccountEntity.getId()).orElseThrow();
        profileService.createProfile(otherProfileEntity, otherAccountEntity.getId()).orElseThrow();
    }

    @Test
    void testGetProfile_withValidId_thenOKWithExpectedProfile() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/profiles/" + myProfileEntity.getId())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromId(myAccountEntity.getOid()))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(myProfileEntity.getName()))
                .andExpect(jsonPath("$.email").value(myProfileEntity.getEmail()))
                .andExpect(jsonPath("$.birthYear").value(myProfileEntity.getBirthYear()))
                .andExpect(jsonPath("$.project.descriptor").value(myProfileEntity.getProject().getDescriptor()))
                .andExpect(jsonPath("$.diet.descriptor").value(myProfileEntity.getDiet().getDescriptor()))
                .andExpect(jsonPath("$.education.descriptor").value(myProfileEntity.getEducation().getDescriptor()))
                .andExpect(jsonPath("$.gender.descriptor").value(myProfileEntity.getGender().getDescriptor()))
                .andExpect(jsonPath("$.hobby[0].descriptor").value(myProfileEntity.getHobby().get(0).getDescriptor()))
                .andExpect(jsonPath("$.hobby[0].category.descriptor").value(myProfileEntity.getHobby().get(0).getCategory().getDescriptor()))
                .andExpect(jsonPath("$.motherTongue.descriptor").value(myProfileEntity.getMotherTongue().getDescriptor()))
                .andExpect(jsonPath("$.originCountry.descriptor").value(myProfileEntity.getOriginCountry().getDescriptor()))
                .andExpect(jsonPath("$.religion.descriptor").value(myProfileEntity.getReligion().getDescriptor()))
                .andExpect(jsonPath("$.workExperience.descriptor").value(myProfileEntity.getWorkExperience().getDescriptor()));
    }

    @Test
    void testCreateProfile_withInvalidDto_thenBadRequest() throws Exception {

        this.mockMvc
                .perform(
                        post("/api/profiles/byAccount/" + myAccountEntity.getId())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromId(myAccountEntity.getOid()))
                                .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Test\"}")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetProfile_withWrongId_thenForbidden() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/profiles/" + myProfileEntity.getId())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromId(otherAccountEntity.getOid()))
                ).andExpect(status().isForbidden());
    }

    @Test
    void testGetProfile_withInvalidToken_thenUnauthorized() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/profiles/" + myProfileEntity.getId())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromIdWrongKey(myAccountEntity.getOid()))
                ).andExpect(status().isUnauthorized());
    }

    @Test
    void testCreateProfile_withWrongId_thenForbidden() throws Exception {

        AccountEntity account = accountService.getOrCreateAccount("irgendwas");
        ProfileDto profileDto = profileMapper.entityToDto(myProfileEntity);
        String profileJSON = objectMapper.writeValueAsString(profileDto);

        mockMvc.perform(
                post("/api/profiles/byAccount/" + (account.getId() + 1))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromId(account.getOid()))
                        .contentType(MediaType.APPLICATION_JSON).content(profileJSON)
        ).andExpect(status().isForbidden());
    }

    @Test
    void testPutProfile_withWrongId_thenForbidden() throws Exception {

        ProfileDto profileDto = profileMapper.entityToDto(myProfileEntity);
        String profileJSON = objectMapper.writeValueAsString(profileDto);

        mockMvc
                .perform(
                        put("/api/profiles/" + myProfileEntity.getId())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromId(otherAccountEntity.getOid()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(profileJSON)
                ).andExpect(status().isForbidden());
    }
}
