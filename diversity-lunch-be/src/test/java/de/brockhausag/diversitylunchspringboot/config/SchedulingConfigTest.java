package de.brockhausag.diversitylunchspringboot.config;

import de.brockhausag.diversitylunchspringboot.dataFactories.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.match.service.MatchingService;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchedulingConfigTest {

    @Mock
    MatchingService matchingService;

    @Mock
    MeetingProposalRepository meetingProposalRepository;

    @InjectMocks
    SchedulingConfig schedulingConfig;

    final MeetingTestdataFactory meetingTestdataFactory = new MeetingTestdataFactory();

    @Test
    void testScheduleMatching() {
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = date.atTime(0, 0);
        List<MeetingProposalEntity> meetings = List.of(
                meetingTestdataFactory.entityBuilder()
                        .proposedDateTime(dateTime)
                        .build()
        );
        when(meetingProposalRepository.findAll()).thenReturn(meetings);
        schedulingConfig.scheduleMatching();
        verify(matchingService).matching(dateTime, dateTime);
    }

    @Test
    void testScheduleMailBeforeMeetingSending() {
        schedulingConfig.scheduleMailBeforeMeetingSending();
        verify(matchingService, times(1)).sendQuestions(any());
    }

}
