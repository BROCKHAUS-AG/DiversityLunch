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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MsTeamsMapperTest {
    public static final String TEST_TEMPLATE_NAME = "Test-Template";
    public static final String TIMEZONE = "Europe/Berlin";
    public static final String EXPECTED_LUNCH_SUBJECT = "Diversity Lunch Termin";

    @Mock
    private DiversityLunchEMailService eMailService;
    @Mock
    private DiversityLunchMsTeamsProperties diversityLunchMsTeamsProperties;
    @InjectMocks
    private MsTeamsMapper classToTest;


    @Test
    public void testConvertToMicrosoftEventModel_meetingProposals_event() {
        //Arrange
        final LocalDateTime startDateTime = LocalDateTime.now();

        final List<MeetingProposalEntity> entities =
                new MeetingTestdataFactory().newMeetingProposalList_withMatchingScore29(startDateTime);
        final MeetingProposalEntity meetingProposalEntityOne = entities.get(0);
        final MeetingProposalEntity meetingProposalEntityTwo = entities.get(1);
        final String firstExpectedMailAddress = meetingProposalEntityOne.getProposerProfile().getEmail();
        final String firstExpectedMailName = meetingProposalEntityOne.getProposerProfile().getName();
        final String secondExpectedMailAddress = meetingProposalEntityTwo.getProposerProfile().getEmail();
        final String secondExpectedMailName = meetingProposalEntityTwo.getProposerProfile().getName();

        when(eMailService.createMsTeamsMeetingTemplateHTML(
                anyString(), anyString(), anyString(), anyString())).thenReturn(TEST_TEMPLATE_NAME);
        when(diversityLunchMsTeamsProperties.getTimeZone()).thenReturn(TIMEZONE);

        //Act
        final Event result = classToTest.convertToMicrosoftEventModel(meetingProposalEntityOne, meetingProposalEntityTwo);


        //Assert
        assertEquals(EXPECTED_LUNCH_SUBJECT, result.subject);

        assertNotNull(result.attendees);
        assertEquals(result.attendees.size(), 2);

        Attendee attendeeOne = result.attendees.get(0);
        Attendee attendeeTwo = result.attendees.get(1);

        assert attendeeOne.emailAddress != null;
        assertEquals(firstExpectedMailAddress, attendeeOne.emailAddress.address);
        assertEquals(firstExpectedMailName, attendeeOne.emailAddress.name);
        assertEquals(AttendeeType.REQUIRED, attendeeOne.type);

        assert attendeeTwo.emailAddress != null;
        assertEquals(secondExpectedMailAddress, attendeeTwo.emailAddress.address);
        assertEquals(secondExpectedMailName, attendeeTwo.emailAddress.name);
        assertEquals(AttendeeType.REQUIRED, attendeeTwo.type);

        assertNotEquals(Boolean.TRUE, result.allowNewTimeProposals);
        assertEquals(Boolean.TRUE, result.isOnlineMeeting);
        assertEquals(OnlineMeetingProviderType.TEAMS_FOR_BUSINESS, result.onlineMeetingProvider);

        final String expectedDateString = DiversityLunchTimeAndDateFormatter.formatDate(startDateTime);
        final String expectedTimeString = DiversityLunchTimeAndDateFormatter.convertFromUTCToTimeZoneAndFormatWithPattern(
                startDateTime, "HH:mm", TIMEZONE);

        verify(eMailService).createMsTeamsMeetingTemplateHTML(
                eq(firstExpectedMailName), eq(secondExpectedMailName),
                eq(expectedDateString), eq(expectedTimeString));
    }
}
