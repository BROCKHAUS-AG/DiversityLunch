package de.brockhausag.diversitylunchspringboot.meeting.service;

import de.brockhausag.diversitylunchspringboot.data.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.data.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.email.DiversityLunchEMailService;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.Question;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchingServiceTest {

    private final MeetingTestdataFactory meetingTestdataFactory = new MeetingTestdataFactory();

    @Mock
    private MeetingProposalRepository meetingProposalRepository;

    @Mock
    private MeetingRepository meetingRepository;

    @Mock
    private MatchingService matchingServiceMock;

    @Mock
    DiversityLunchEMailService eMailService;

    @Mock
    MsTeamsService msTeamsService;

    @InjectMocks
    private MatchingService matchingService;

    @Test
    void testMatching_moreThen7DaysUntilMeeting_shouldCallExecuteMatchingWithGivenTimeAnd21ScoreToBeat() {
        doNothing().when(matchingServiceMock).executeMatching(any(), anyInt());
        doCallRealMethod().when(matchingServiceMock).matching(any(), any());
        LocalDateTime localDateTime = LocalDateTime.of(2022, 3, 29, 0, 0, 0);
        matchingServiceMock.matching(localDateTime, localDateTime.minusDays(8));
        verify(matchingServiceMock, times(1)).executeMatching(localDateTime, 21);
    }

    @Test
    void testMatching_lessThen7DaysUntilMeeting_shouldCallExecuteMatchingWithGivenTimeAnd9ScoreToBeat() {
        doNothing().when(matchingServiceMock).executeMatching(any(), anyInt());
        doCallRealMethod().when(matchingServiceMock).matching(any(), any());
        LocalDateTime localDateTime = LocalDateTime.of(2022, 3, 29, 0, 0, 0);
        matchingServiceMock.matching(localDateTime, localDateTime.minusDays(6));
        verify(matchingServiceMock, times(1)).executeMatching(localDateTime, 9);
    }

    @Test
    void testMatching_lessThen3DaysUntilMeeting_shouldCallExecuteMatchingWithGivenTimeAnd0ScoreToBeat() {
        doNothing().when(matchingServiceMock).executeMatching(any(), anyInt());
        doCallRealMethod().when(matchingServiceMock).matching(any(), any());
        LocalDateTime localDateTime = LocalDateTime.of(2022, 3, 29, 0, 0, 0);
        matchingServiceMock.matching(localDateTime, localDateTime);
        verify(matchingServiceMock, times(1)).executeMatching(localDateTime, 0);
    }

    @Test
    void testMatching_ProposedTimeIsBeforeToday_shouldCallNoExecuteMatching() {
        doCallRealMethod().when(matchingServiceMock).matching(any(), any());
        LocalDateTime localDateTime = LocalDateTime.of(2022, 3, 29, 0, 0, 0);
        matchingServiceMock.matching(localDateTime, localDateTime.plusDays(1));
        verify(matchingServiceMock, times(0)).executeMatching(any(), anyInt());
    }

    @Test
    void testExecuteMatching_MatchProposal_MeetingShouldBeCreated() {
        LocalDateTime time = LocalDateTime.of(2022, 3, 3, 12, 30, 0);
        List<MeetingProposalEntity> list = meetingTestdataFactory.newMeetingProposalList(time);
        when(meetingProposalRepository.findMeetingProposalEntitiesByProposedDateTimeAndMatchedFalse(any())).thenReturn(list);
        matchingService.executeMatching(time, 0);
        verify(meetingRepository, times(1)).save(any());
        verify(meetingProposalRepository, times(2)).save(any());
    }

    @Test
    void testExecuteMatching_MatchProposalScoreToLow_MeetingShouldNotBeCreated() {
        LocalDateTime time = LocalDateTime.of(2022, 3, 3, 0, 0, 0);
        List<MeetingProposalEntity> list = meetingTestdataFactory.newMeetingProposalList(time);
        when(meetingProposalRepository.findMeetingProposalEntitiesByProposedDateTimeAndMatchedFalse(any())).thenReturn(list);
        matchingService.executeMatching(time, 9);
        verify(meetingRepository, times(0)).save(any());
        verify(meetingProposalRepository, times(0)).save(any());
    }

    @Test
    void testExecuteMatching_ShouldFail() {
        assertThrows(IllegalArgumentException.class, () ->
                matchingService.matching(null, null)
        );
    }

    @Test
    void testSendQuestions() throws MessagingException {

        ProfileTestdataFactory profileTestdataFactory = new ProfileTestdataFactory();

        List<MeetingEntity> meetingEntities = List.of(MeetingEntity.builder()
                .fromDateTime(LocalDateTime.now())
                .partner(profileTestdataFactory.entity())
                .proposer(profileTestdataFactory.entity())
                .createdAt(LocalDateTime.now())
                .question(Question.COUNTRY_OF_ORIGIN2)
                .build());

        when(meetingRepository.findByFromDateTime(any())).thenReturn(meetingEntities);

        matchingService.sendQuestions(LocalDateTime.now());

        verify(eMailService, times(2))
                .sendEmail(any(), any(), any(),any());

    }
}
