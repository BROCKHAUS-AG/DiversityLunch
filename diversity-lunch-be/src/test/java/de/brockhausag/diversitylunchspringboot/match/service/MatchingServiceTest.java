package de.brockhausag.diversitylunchspringboot.match.service;

import de.brockhausag.diversitylunchspringboot.dataFactories.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.QuestionTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.email.service.DiversityLunchEMailService;
import de.brockhausag.diversitylunchspringboot.match.model.Matching;
import de.brockhausag.diversitylunchspringboot.match.records.ScoreAndCategory;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.meeting.service.MsTeamsService;
import de.brockhausag.diversitylunchspringboot.meeting.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchingServiceTest {

    private final MeetingTestdataFactory meetingTestdataFactory = new MeetingTestdataFactory();
    private final QuestionTestDataFactory questionTestDataFactory = new QuestionTestDataFactory();
    @Mock
    DiversityLunchEMailService mockedEMailService;
    @Mock
    MsTeamsService msTeamsService;
    @Mock
    private MeetingProposalRepository meetingProposalRepository;
    @Mock
    private MeetingRepository meetingRepository;
    @Mock
    private MatchingService matchingServiceMock;
    @Mock
    private QuestionService questionService;
    @InjectMocks
    private MatchingService matchingService;
    @Mock
    private Matching matching;

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
        // Arrange
        LocalDateTime time = LocalDateTime.of(2022, 3, 3, 12, 30, 0);
        List<MeetingProposalEntity> list = meetingTestdataFactory.newMeetingProposalList_withMatchingScore29(time);
        when(meetingProposalRepository.findMeetingProposalEntitiesByProposedDateTimeAndMatchedFalse(any())).thenReturn(list);
        when(questionService.getQuestionsForCategory(any())).thenReturn(List.of(questionTestDataFactory.buildEntity("Q1"), questionTestDataFactory.buildEntity("Q2")));
        when(matchingService.createMatching(any(MeetingProposalEntity.class), any(MeetingProposalEntity.class))).thenReturn(matching);
        when(matching.getStats()).thenReturn(new ScoreAndCategory(19, DimensionCategory.builder().build()));

        // Act
        matchingService.executeMatching(time, 0);

        // Assert
        verify(meetingRepository, times(1)).save(any());
        verify(meetingProposalRepository, times(2)).save(any());
    }

    @Test
    void testExecuteMatching_MatchProposalScoreToLow_MeetingShouldNotBeCreated() {
        // Arrange
        LocalDateTime time = LocalDateTime.of(2022, 3, 3, 0, 0, 0);
        List<MeetingProposalEntity> list = meetingTestdataFactory.newMeetingProposalList_withMatchingScore1(time);
        when(meetingProposalRepository.findMeetingProposalEntitiesByProposedDateTimeAndMatchedFalse(any())).thenReturn(list);
        when(matchingService.createMatching(any(MeetingProposalEntity.class), any(MeetingProposalEntity.class))).thenReturn(matching);
        when(matching.getStats()).thenReturn(new ScoreAndCategory(8, DimensionCategory.builder().build()));

        // Act
        matchingService.executeMatching(time, 9);

        // Assert
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
                .partner(profileTestdataFactory.buildEntity(1))
                .proposer(profileTestdataFactory.buildEntity(2))
                .createdAt(LocalDateTime.now())
                .question(questionTestDataFactory.buildEntity("Wo kommst du her?", "Herkunft"))
                .build());

        when(meetingRepository.findByFromDateTime(any())).thenReturn(meetingEntities);

        matchingService.sendQuestions(LocalDateTime.now());

        verify(mockedEMailService, times(2))
                .sendEmail(any(), any(), any(), any());

    }
}
