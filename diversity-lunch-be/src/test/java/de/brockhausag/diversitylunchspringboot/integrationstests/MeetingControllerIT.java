package de.brockhausag.diversitylunchspringboot.integrationstests;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.repository.AccountRepository;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.config.SecurityConfig;
import de.brockhausag.diversitylunchspringboot.dataFactories.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.model.CreateMeetingProposalDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.service.MeetingService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.data.ProfileRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
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

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(SecurityConfig.class)
@ActiveProfiles("Test")
class MeetingControllerIT {

    private final ProfileTestdataFactory profileFactory = new ProfileTestdataFactory();

    private final MeetingTestdataFactory meetingFactory = new MeetingTestdataFactory();

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MeetingProposalRepository meetingProposalRepository;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext appContext;

    private static final String accAName = "A";
    private static final String accBName = "B";
    private ProfileEntity profileA;
    private ProfileEntity profileB;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(appContext)
                .apply(springSecurity())
                .build();

        AccountEntity accA = accountService.getOrCreateAccount(accAName);
        AccountEntity accB = accountService.getOrCreateAccount(accBName);

        ProfileEntity tmpProfile = profileFactory.createEntity();
        profileA = profileService
                .createProfile(tmpProfile, accA.getId())
                .orElseThrow();

        tmpProfile = profileFactory.createEntity();
        profileB = profileService
                .createProfile(tmpProfile, accB.getId())
                .orElseThrow();
    }

    @AfterEach
    void shutdown() {
        meetingProposalRepository.deleteAll();
        accountRepository.deleteAll();
        profileRepository.deleteAll();
    }

    @Test
    void testGetMeetingProposalByUser_withWrongId_thenForbidden() throws Exception {
        MeetingProposalEntity meetingProposalEntityOfProfileA = meetingFactory
                .createEntityBuilder()
                .proposerProfile(profileA)
                .build();

        meetingService.createMeetingProposal(meetingProposalEntityOfProfileA);

        this.mockMvc.perform(
                get("/api/meetings/byUser/" + profileA.getId())
                        .header(HttpHeaders.AUTHORIZATION,
                                "Bearer " + profileFactory.getTokenStringFromId(accBName))
        ).andExpect(status().isForbidden());
    }

    @Test
    void testPostMeetingProposal_withWrongId_thenForbidden() throws Exception {
        CreateMeetingProposalDto meetingProposalDtoOfProfileA = meetingFactory
                .createDto();

        String convertedStringJSON = objectMapper.writeValueAsString(meetingProposalDtoOfProfileA);

        this.mockMvc.perform(
                post("/api/meetings/byUser/" + profileB.getId())
                        .header(HttpHeaders.AUTHORIZATION,
                                "Bearer " + profileFactory.getTokenStringFromId(accAName))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertedStringJSON)
        ).andExpect(status().isForbidden());
    }

    @Test
    void testDeleteMeetingProposal_withWrongId_thenForbidden() throws Exception {
        MeetingProposalEntity createMeetingOfProfileA = meetingFactory
                .createEntityBuilder()
                .proposerProfile(profileA)
                .build();

        MeetingProposalEntity existingMeetingOfProfileA = meetingService.createMeetingProposal(createMeetingOfProfileA);



        mockMvc.perform(
                delete("/api/meetings/" + existingMeetingOfProfileA.getId())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromId(accBName))
        ).andExpect(status().isForbidden());
    }


}
