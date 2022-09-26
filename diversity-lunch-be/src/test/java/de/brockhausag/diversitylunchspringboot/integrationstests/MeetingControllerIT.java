package de.brockhausag.diversitylunchspringboot.integrationstests;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.brockhausag.diversitylunchspringboot.config.SecurityConfig;
import de.brockhausag.diversitylunchspringboot.dataFactories.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.integrationDataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.model.CreateMeetingProposalDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.service.MeetingService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
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

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(SecurityConfig.class)
@ActiveProfiles("Test")
@SqlGroup({
        @Sql(scripts = "classpath:integrationstests/insert_test_data.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:integrationstests/delete_test_data.sql", executionPhase = AFTER_TEST_METHOD)
})
class MeetingControllerIT {
    private final MeetingTestdataFactory meetingFactory = new MeetingTestdataFactory();

    @Autowired
    private ProfileTestdataFactory profileFactory;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext appContext;

    private ProfileEntity profileMax;
    private ProfileEntity profileErika;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(appContext)
                .apply(springSecurity())
                .build();

        profileMax = profileFactory.createNewMaxProfile();
        profileErika = profileFactory.createNewErikaProfile();
    }

    @Test
    void testGetMeetingProposalByUser_withWrongId_thenForbidden() throws Exception {
        MeetingProposalEntity meetingProposalEntityOfProfileMax = meetingFactory
                .createEntityBuilder()
                .proposerProfile(profileMax)
                .build();

        meetingService.createMeetingProposal(meetingProposalEntityOfProfileMax);

        this.mockMvc.perform(
                get("/api/meetings/byUser/" + profileMax.getId())
                        .header(HttpHeaders.AUTHORIZATION,
                                "Bearer " + profileFactory.getTokenStringFromId("Max"))
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
                                "Bearer " + profileFactory.getTokenStringFromId("Erika"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertedStringJSON)
        ).andExpect(status().isForbidden());
    }

    @Test
    void testDeleteMeetingProposal_withWrongId_thenForbidden() throws Exception {
        MeetingProposalEntity createMeetingOfProfileMax = meetingFactory
                .createEntityBuilder()
                .proposerProfile(profileMax)
                .build();

        MeetingProposalEntity existingMeetingOfProfileMax = meetingService.createMeetingProposal(createMeetingOfProfileMax);



        mockMvc.perform(
                delete("/api/meetings/" + existingMeetingOfProfileMax.getId())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileFactory.getTokenStringFromId("Max"))
        ).andExpect(status().isForbidden());
    }
}
