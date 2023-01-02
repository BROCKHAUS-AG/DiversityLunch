package de.brockhausag.diversitylunchspringboot.integrationstests;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.repository.AccountRepository;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.config.MsTeamsTestConfig;
import de.brockhausag.diversitylunchspringboot.integrationDataFactories.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.integrationDataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.model.CreateMeetingProposalDto;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.service.MicrosoftGraphService;
import de.brockhausag.diversitylunchspringboot.profile.data.ProfileRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@SqlGroup({
        @Sql(scripts = "classpath:integrationstests/insert_test_data.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:integrationstests/delete_test_data.sql", executionPhase = AFTER_TEST_METHOD)
})
@ActiveProfiles("Test")
@Import(MsTeamsTestConfig.class)
class MeetingControllerIT {

    @Autowired
    private MeetingTestdataFactory meetingFactory;

    @Autowired
    private ProfileTestdataFactory profileFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private MicrosoftGraphService microsoftGraphService;

    @Autowired
    private WebApplicationContext appContext;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MeetingProposalRepository meetingProposalRepository;

    private MockMvc mockMvc;

    private ProfileEntity profileMax;

    private ProfileEntity profileErika;


    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(appContext)
                .apply(springSecurity())
                .build();

        when(microsoftGraphService.getGroups()).thenReturn(Optional.of(new ArrayList<>()));

        //profileMax = profileFactory.createNewMaxProfile();
        //AccountEntity accountEntity = accountService.getOrCreateAccount(profileMax.getEmail());
        //profileMax = profileService.createProfile(profileMax, accountEntity.getId()).orElseThrow();

        //profileErika = profileFactory.createNewErikaProfile();
        //AccountEntity accountEntity1 = accountService.getOrCreateAccount(profileErika.getEmail());
        //profileErika = profileService.createProfile(profileErika, accountEntity1.getId()).orElseThrow();
    }

    @AfterEach
    void after() {
        accountRepository.deleteAll();
        meetingProposalRepository.deleteAll();
        profileRepository.deleteAll();
    }

    @Test
    void testFalse(){
        assertFalse(true);
    }

    @Test
    void testGetMeetingProposalByUser_withWrongId_thenForbidden() throws Exception {
        meetingFactory.create(profileMax);

        mockMvc.perform(
                get("/api/meetings/byUser/" + profileMax.getId())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " +
                                profileFactory.getTokenStringFromId(profileMax.getName()))
        ).andExpect(status().isForbidden());
    }

    @Test
    void testPostMeetingProposal_withWrongId_thenForbidden() throws Exception {
        CreateMeetingProposalDto meetingProposalDtoOfProfileMax = meetingFactory
                .createDto();

        String convertedStringJSON = objectMapper.writeValueAsString(meetingProposalDtoOfProfileMax);

        this.mockMvc.perform(
                post("/api/meetings/byUser/" + profileErika.getId())
                        .header(HttpHeaders.AUTHORIZATION,
                                "Bearer " + profileFactory.getTokenStringFromId(profileErika.getName()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertedStringJSON)
        ).andExpect(status().isForbidden());
    }

    @Test
    void testDeleteMeetingProposal_withWrongId_thenForbidden() throws Exception {
        meetingFactory.create(profileMax);

        mockMvc.perform(
                delete("/api/meetings/" + profileMax.getId())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " +
                                profileFactory.getTokenStringFromId(profileMax.getName()))
        ).andExpect(status().isForbidden());
    }
}
