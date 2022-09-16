package de.brockhausag.diversitylunchspringboot.teams;

import com.microsoft.graph.models.Attendee;
import com.microsoft.graph.models.AttendeeType;
import com.microsoft.graph.models.Event;
import com.microsoft.graph.models.OnlineMeetingProviderType;
import de.brockhausag.diversitylunchspringboot.dataFactories.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.email.service.DiversityLunchEMailService;
import de.brockhausag.diversitylunchspringboot.meeting.mapper.MsTeamsMapper;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchMsTeamsProperties;
import de.brockhausag.diversitylunchspringboot.utils.DiversityLunchTimeAndDateFormatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MsTeamsMapperTest {

    public static final String TEST_TEMPLATE_NAME = "Test-Template";
    public static final String TIMEZONE = "Europe/Berlin";
    public static final String EXPECTED_LUNCH_SUBJECT = "Diversity Lunch Termin";
    public static final String EXPECTED_MAIL_ADDRESS = "joha.patrick@some.tld";
    public static final String EXPECTED_MAIL_NAME = "John Patrick-Test";
    @Mock
    private DiversityLunchEMailService eMailService;

    @Mock
    private DiversityLunchMsTeamsProperties diversityLunchMsTeamsProperties;

    @InjectMocks
    private MsTeamsMapper classToTest;

    @Test
    public void testConvertToMicrosoftEventModel_meetingProposals_event() {
        final LocalDateTime startDateTime = LocalDateTime.now();

        final List<MeetingProposalEntity> entities =
                new MeetingTestdataFactory().newMeetingProposalList(startDateTime);

        final MeetingProposalEntity meetingProposalEntityOne = entities.get(0);
        final MeetingProposalEntity meetingProposalEntityTwo = entities.get(1);

        when(eMailService.createMsTeamsMeetingTemplateHTML(
                anyString(), anyString(), anyString(), anyString())).thenReturn(TEST_TEMPLATE_NAME);

        when(diversityLunchMsTeamsProperties.getTimeZone()).thenReturn(TIMEZONE);

        final Event result = classToTest.convertToMicrosoftEventModel(meetingProposalEntityOne, meetingProposalEntityTwo);

        assertEquals(EXPECTED_LUNCH_SUBJECT, result.subject);

        assertNotNull(result.attendees);
        assertEquals(result.attendees.size(), 2);

        Attendee attendeeOne = result.attendees.get(0);
        Attendee attendeeTwo = result.attendees.get(1);

        assertEquals(EXPECTED_MAIL_ADDRESS, attendeeOne.emailAddress.address);
        assertEquals(EXPECTED_MAIL_NAME, attendeeOne.emailAddress.name);
        assertEquals(AttendeeType.REQUIRED, attendeeOne.type);

        assertEquals(EXPECTED_MAIL_ADDRESS, attendeeTwo.emailAddress.address);
        assertEquals(EXPECTED_MAIL_NAME, attendeeTwo.emailAddress.name);
        assertEquals(AttendeeType.REQUIRED, attendeeTwo.type);

        assertFalse(result.allowNewTimeProposals);
        assertTrue(result.isOnlineMeeting);
        assertEquals(OnlineMeetingProviderType.TEAMS_FOR_BUSINESS, result.onlineMeetingProvider);

        final String expectedDateString = DiversityLunchTimeAndDateFormatter.formatDate(startDateTime);
        final String expectedTimeString = DiversityLunchTimeAndDateFormatter.convertFromUTCToTimeZoneAndFormatWithPattern(
                startDateTime, "HH:mm", TIMEZONE);

        verify(eMailService).createMsTeamsMeetingTemplateHTML(
                eq(EXPECTED_MAIL_NAME), eq(EXPECTED_MAIL_NAME),
                eq(expectedDateString), eq(expectedTimeString));
    }
}
