package de.brockhausag.diversitylunchspringboot.meeting.service;

import com.microsoft.graph.models.*;
import de.brockhausag.diversitylunchspringboot.dataFactories.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.model.DeclinedMeeting;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MsTeamsServiceTest {
    @Mock
    MicrosoftGraphService microsoftGraphService;
    @Mock
    MeetingRepository meetingRepository;

    @InjectMocks
    MsTeamsService msTeamsService;

    ProfileTestdataFactory profileFactory;
    MeetingTestdataFactory meetingFactory;

    @BeforeEach
    void setUp() {
        profileFactory = new ProfileTestdataFactory();
        meetingFactory = new MeetingTestdataFactory();
    }

    @Test
    void testGetAllDeclinedMeetingsShouldReturnTheCorrectAmountOfDeclinedMeetings () {
        ProfileEntity p1 = profileFactory.buildEntity(1);
        ProfileEntity p2 = profileFactory.buildEntity(2);
        MeetingEntity meeting = meetingFactory.matchedMeeting(p1, p2);

        List<Event> eventList = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> eventList.add(createMicrosoftGraphEvent(p1, i%2 == 0, p2, i%4 == 0)));

        when(microsoftGraphService.getAllFutureEvents()).thenReturn(eventList);
        when(meetingRepository.findByMsTeamsMeetingId(any())).thenReturn(Optional.of(meeting));

        List<DeclinedMeeting> actual = msTeamsService.getAllDeclinedMeetings();
        assertEquals(5, actual.size());
    }

    @Test
    void testGetCancelerEmail_OneProfileCanceledTheMeeting() {
        ProfileEntity p1 = profileFactory.buildEntity(1);
        ProfileEntity p2 = profileFactory.buildEntity(2);
        Event event1 = createMicrosoftGraphEvent(p1, true, p2, false);

        String cancelerMail1 = MsTeamsService.getCancelerEmail(event1).orElseThrow();
        assertEquals(p1.getEmail(), cancelerMail1);

        Event event2 = createMicrosoftGraphEvent(p1, false, p2, true);

        String cancelerMail2 = MsTeamsService.getCancelerEmail(event2).orElseThrow();
        assertEquals(p2.getEmail(), cancelerMail2);
    }

    @Test
    void testGetCancelerEmail_NoProfileCanceledTheMeeting_ShouldReturnEmptyOptional() {
        ProfileEntity p1 = profileFactory.buildEntity(1);
        ProfileEntity p2 = profileFactory.buildEntity(2);
        Event event = createMicrosoftGraphEvent(p1, false, p2, false);

        Optional<String> actual = MsTeamsService.getCancelerEmail(event);
        assertFalse(actual.isPresent());
    }

    @Test
    void testGetCancelerEmail_BothProfileCanceledTheMeeting_ShouldReturnEmptyOptional() {
        ProfileEntity p1 = profileFactory.buildEntity(1);
        ProfileEntity p2 = profileFactory.buildEntity(2);
        Event event = createMicrosoftGraphEvent(p1, true, p2, true);

        Optional<String> actual = MsTeamsService.getCancelerEmail(event);
        assertEquals(p1.getEmail(), actual.orElseThrow());
    }

    @Test
    void testGetCancelerProfileByEmail_BothMeetingsMatchWithEmail() {
        ProfileEntity p1 = profileFactory.buildEntity(1);
        ProfileEntity p2 = profileFactory.buildEntity(2);
        MeetingEntity meeting = meetingFactory.matchedMeeting(p1, p2);

        ProfileEntity actual1 = MsTeamsService.getCancelerProfileByEmail(meeting, p1.getEmail()).orElseThrow();
        assertEquals(p1, actual1);

        ProfileEntity actual2 = MsTeamsService.getCancelerProfileByEmail(meeting, p2.getEmail()).orElseThrow();
        assertEquals(p2, actual2);
    }

    @Test
    void testGetCancelerProfileByEmail_WithoutMatchingEmail () {
        ProfileEntity p1 = profileFactory.buildEntity(1);
        ProfileEntity p2 = profileFactory.buildEntity(2);
        MeetingEntity meeting = meetingFactory.matchedMeeting(p1, p2);

        Optional<ProfileEntity> actual = MsTeamsService.getCancelerProfileByEmail(meeting, "NotTheReal@Mail");
        assertFalse(actual.isPresent());
    }

    private Event createMicrosoftGraphEvent(ProfileEntity profile1, boolean profile1Declined, ProfileEntity profile2, boolean profile2Declined) {
        Attendee attendee1 = createMicrosoftGraphAttendee(profile1, profile1Declined);
        Attendee attendee2 = createMicrosoftGraphAttendee(profile2, profile2Declined);

        Event event = new Event();
        event.attendees = List.of(attendee1, attendee2);

        return event;
    }

    private Attendee createMicrosoftGraphAttendee (ProfileEntity profile, boolean profileDeclined) {
        Attendee attendee = new Attendee();
        attendee.status = createResponse(profileDeclined);
        attendee.emailAddress = new EmailAddress();
        attendee.emailAddress.name = profile.getName();
        attendee.emailAddress.address = profile.getEmail();
        return attendee;
    }

    private ResponseStatus createResponse(boolean declined) {
        ResponseStatus res = new ResponseStatus();
        res.response = declined ? ResponseType.DECLINED : ResponseType.ACCEPTED;
        return res;
    }
}