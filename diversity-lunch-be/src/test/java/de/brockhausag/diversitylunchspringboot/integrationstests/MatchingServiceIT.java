package de.brockhausag.diversitylunchspringboot.integrationstests;

import de.brockhausag.diversitylunchspringboot.config.MsTeamsTestConfig;
import de.brockhausag.diversitylunchspringboot.integrationDataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.match.service.MatchingService;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.data.ProfileRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@Slf4j
@SpringBootTest
@SqlGroup({
        @Sql(scripts = "classpath:integrationstests/insert_test_data.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:integrationstests/insert_matching_test_data.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:integrationstests/delete_test_data.sql", executionPhase = AFTER_TEST_METHOD)
})
@ActiveProfiles("Test")
@Import(MsTeamsTestConfig.class)
class MatchingServiceIT {
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private MatchingService matchingService;
    @Autowired
    private MeetingProposalRepository meetingProposalRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileTestdataFactory profileFactory;

    @Test
    void testMatchingServiceScore0() {
        log.info("DB: " + meetingProposalRepository.findAll());
        ProfileEntity partner = profileRepository.findById(1L).orElseThrow();
        ProfileEntity proposer = profileRepository.findById(2L).orElseThrow();
        LocalDateTime proposedDateTime = LocalDateTime.of(2022, 3, 18, 12, 30);
        MeetingEntity expectedForCase21First = MeetingEntity.builder()
                .fromDateTime(proposedDateTime)
                .score(4)
                .partner(partner)
                .proposer(proposer)
                .build();
        matchingService.matching(proposedDateTime, proposedDateTime.minusDays(1));
        List<MeetingEntity> result = meetingRepository.findAll();
        log.info("MEETING_ENTITY 21: " + result);
        assertFalse(result.isEmpty());
        assertMeetingIsEqual(expectedForCase21First, result.get(0), 0);
        assertFalse(result.get(0).getQuestion().getQuestionText().isEmpty());
    }

    @Test
    void testMatchingServiceScore9() {
        LocalDateTime proposedDateTime = LocalDateTime.of(2022, 3, 18, 11, 30);
        ProfileEntity partner = profileRepository.findById(1L).orElseThrow();
        ProfileEntity proposer = profileRepository.findById(3L).orElseThrow();
        MeetingEntity expectedForCase9 = MeetingEntity.builder()
                .fromDateTime(proposedDateTime)
                .score(9)
                .partner(partner)
                .proposer(proposer)
                .build();
        matchingService.matching(proposedDateTime, proposedDateTime.minusDays(3));
        List<MeetingEntity> result = meetingRepository.findAll();
        log.info("MEETING_ENTITY 9: " + result);
        assertFalse(result.isEmpty());
        assertMeetingIsEqual(expectedForCase9, result.get(0), 9);
        assertFalse(result.get(0).getQuestion().getQuestionText().isEmpty());
    }

    @Test
    void testMatchingServiceScore21() {
        LocalDateTime proposedDateTime = LocalDateTime.of(2022, 4, 5, 13, 30);
        ProfileEntity partner = profileRepository.findById(1L).orElseThrow();
        ProfileEntity proposer = profileRepository.findById(4L).orElseThrow();
        MeetingEntity expectedForCase21 = MeetingEntity.builder()
                .fromDateTime(proposedDateTime)
                .score(21)
                .partner(partner)
                .proposer(proposer)
                .build();
        matchingService.matching(proposedDateTime, proposedDateTime.minusDays(8));
        List<MeetingEntity> result = meetingRepository.findAll();
        log.info("MEETING_ENTITY 0: " + result);
        assertFalse(result.isEmpty());
        assertMeetingIsEqual(expectedForCase21, result.get(0), 21);
    }

    @Test
    void testMatchingServiceUnmatched() {
        LocalDateTime proposedDateTime = LocalDateTime.of(2022, 4, 5, 13, 30);
        MeetingProposalEntity expectedForCaseUnmatched = MeetingProposalEntity.builder()
                .proposedDateTime(proposedDateTime)
                .proposerProfile(profileFactory.createNewMaxProfile())
                .matched(false)
                .build();
        matchingService.executeMatching(proposedDateTime, 21);
        if (meetingProposalRepository.findById(7L).isPresent()) {
            MeetingProposalEntity result = meetingProposalRepository.findById(7L).get();
            log.info("MEETING_ENTITY UNMATCHED: " + result);
            assertEquals("[UNMATCHED] Get Matched: ", expectedForCaseUnmatched.isMatched(), result.isMatched());
        } else {
            log.info("MEETING_PROPOSAL_ENTITY UNMATCHED NOT FOUND. MATCHING FAULTY.");
        }
    }

    private void assertMeetingIsEqual(MeetingEntity expected, MeetingEntity result, int caseIndex) {
        assertEquals("[" + caseIndex + "] Get Partner Name: ", expected.getPartner().getName(), result.getPartner().getName());
        assertEquals("[" + caseIndex + "] Get Proposer Name: ", expected.getProposer().getName(), result.getProposer().getName());
        assertEquals("[" + caseIndex + "] Get Score: ", expected.getScore(), result.getScore());
        assertEquals("[" + caseIndex + "] Get Date: ", expected.getFromDateTime(), result.getFromDateTime());
    }
}
