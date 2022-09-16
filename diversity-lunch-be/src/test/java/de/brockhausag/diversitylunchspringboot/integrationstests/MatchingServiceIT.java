package de.brockhausag.diversitylunchspringboot.integrationstests;

import de.brockhausag.diversitylunchspringboot.config.MsTeamsTestConfig;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.meeting.service.MatchingService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.data.ProfileRepository;
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
        @Sql(scripts = "classpath:testData.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:testDataCleanUp.sql", executionPhase = AFTER_TEST_METHOD)
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

    @Test
    void testMatchingServiceScore0() {
        log.info("DB: " + meetingProposalRepository.findAll());
        ProfileEntity partner = profileRepository.findById(1L).orElseThrow();
        ProfileEntity proposer = profileRepository.findById(2L).orElseThrow();
        LocalDateTime proposedDateTime = LocalDateTime.of(2022, 3, 18, 12, 30);
        MeetingEntity expectedForCase21First = MeetingEntity.builder()
                .fromDateTime(proposedDateTime)
                .score(6)
                .partner(partner)
                .proposer(proposer)
                .build();
        matchingService.matching(proposedDateTime, proposedDateTime.minusDays(1));
        List<MeetingEntity> result = meetingRepository.findAll();
        log.info("MEETING_ENTITY 21: " + result);
        assertFalse(result.isEmpty());
        assertionForEquality(expectedForCase21First, result.get(0), 0);
        assertFalse(result.get(0).getQuestion().getKind().isEmpty());
    }

    @Test
    void testMatchingServiceScore9() {
        LocalDateTime proposedDateTime = LocalDateTime.of(2022, 3, 18, 11, 30);
        ProfileEntity partner = profileRepository.findById(3L).orElseThrow();
        ProfileEntity proposer = profileRepository.findById(4L).orElseThrow();
        MeetingEntity expectedForCase9 = MeetingEntity.builder()
                .fromDateTime(proposedDateTime)
                .score(13)
                .partner(partner)
                .proposer(proposer)
                .build();
        matchingService.matching(proposedDateTime, proposedDateTime.minusDays(3));
        List<MeetingEntity> result = meetingRepository.findAll();
        log.info("MEETING_ENTITY 9: " + result);
        assertFalse(result.isEmpty());
        assertionForEquality(expectedForCase9, result.get(0), 9);
        assertFalse(result.get(0).getQuestion().getKind().isEmpty());
    }

    @Test
    void testMatchingServiceScore21() {
        LocalDateTime proposedDateTime = LocalDateTime.of(2022, 4, 5, 13, 30);
        ProfileEntity partner = profileRepository.findById(5L).orElseThrow();
        ProfileEntity proposer = profileRepository.findById(6L).orElseThrow();
        MeetingEntity expectedForCase0 = MeetingEntity.builder()
                .fromDateTime(proposedDateTime)
                .score(30)
                .partner(partner)
                .proposer(proposer)
                .build();
        matchingService.matching(proposedDateTime, proposedDateTime.minusDays(8));
        List<MeetingEntity> result = meetingRepository.findAll();
        log.info("MEETING_ENTITY 0: " + result);
        assertFalse(result.isEmpty());
        assertionForEquality(expectedForCase0, result.get(0), 21);
    }

    @Test
    void testMatchingServiceUnmatched(){
        LocalDateTime proposedDateTime = LocalDateTime.of(2022, 4, 5, 13, 30);
        MeetingProposalEntity expectedForCaseUnmatched = MeetingProposalEntity.builder()
                .proposedDateTime(proposedDateTime)
                .proposerProfile(ProfileEntity.builder().build())
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

    private void assertionForEquality(MeetingEntity expected, MeetingEntity result, int caseIndex) {
        assertEquals("[" + caseIndex + "] Get Partner Name: ", expected.getPartner().getName(), result.getPartner().getName());
        assertEquals("[" + caseIndex + "] Get Proposer Name: ", expected.getProposer().getName(), result.getProposer().getName());
        assertEquals("[" + caseIndex + "] Get Score: ", expected.getScore(), result.getScore());
        assertEquals("[" + caseIndex + "] Get Date: ", expected.getFromDateTime(), result.getFromDateTime());
    }
}
