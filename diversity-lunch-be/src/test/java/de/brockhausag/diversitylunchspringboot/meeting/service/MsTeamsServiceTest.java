package de.brockhausag.diversitylunchspringboot.meeting.service;

import com.microsoft.graph.models.Attendee;
import com.microsoft.graph.models.Event;
import com.microsoft.graph.models.ResponseStatus;
import com.microsoft.graph.models.ResponseType;
import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.mapper.MsTeamsMapper;
import de.brockhausag.diversitylunchspringboot.meeting.model.DeclinedMeeting;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MsTeamsServiceTest {
    @Mock
    MicrosoftGraphService microsoftGraphService;
    @Mock
    MsTeamsMapper msTeamsMapper;
    @Mock
    MeetingRepository meetingRepository;

    @InjectMocks
    MsTeamsService msTeamsService;

    ProfileTestdataFactory testdataFactory;

    @BeforeEach
    void setUp() {
        testdataFactory = new ProfileTestdataFactory();
    }

    @Test
    public void testGetAllDeclinedMeetings_withThreeDeclinedEvents() {
        ProfileEntity profile1 = testdataFactory.buildEntity(1);
        ProfileEntity profile2 = testdataFactory.buildEntity(2);

        Attendee attendee1 = new Attendee();
        attendee1.status = new ResponseStatus();
        attendee1.status.response = ResponseType.ACCEPTED;
        Attendee attendee2 = new Attendee();
        attendee2.status = new ResponseStatus();
        attendee2.status.response = ResponseType.DECLINED;
        Event event1 = new Event();
        event1.attendees = List.of(attendee1, attendee2);
        Event event2 = new Event();
        event2.attendees = List.of(attendee1, attendee1);

        String msTeamsId = "42";
        MeetingEntity meeting = MeetingEntity.builder()
                .msTeamsMeetingId(msTeamsId)
                .build();

        List<Event> futureEvents = List.of(event1, event1, event2, event1);
        when(microsoftGraphService.getAllFutureEvents()).thenReturn(Optional.of(futureEvents));
        when(meetingRepository.findByMsTeamsMeetingId(any())).thenReturn(meeting);

        List<DeclinedMeeting> declinedMeetings = msTeamsService.getAllDeclinedMeetings();
        int actual = declinedMeetings.size();
        assertEquals(3, actual);
    }
}