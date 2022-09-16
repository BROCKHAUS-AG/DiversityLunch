package de.brockhausag.diversitylunchspringboot.meeting.mapper;

import com.microsoft.graph.models.*;
import de.brockhausag.diversitylunchspringboot.email.service.DiversityLunchEMailService;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchMsTeamsProperties;
import de.brockhausag.diversitylunchspringboot.utils.DiversityLunchTimeAndDateFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MsTeamsMapper {

    private final DiversityLunchEMailService eMailService;
    private final DiversityLunchMsTeamsProperties diversityLunchMsTeamsProperties;

    public Event convertToMicrosoftEventModel(MeetingProposalEntity meetingProposalEntityOne, MeetingProposalEntity meetingProposalEntityTwo) {
        Event event = new Event();

        event.subject = "Diversity Lunch Termin";

        event.start = getDateTimeTimeZone(meetingProposalEntityOne.getProposedDateTime());
        event.end = getDateTimeTimeZone(meetingProposalEntityOne.getProposedDateTime().plusMinutes(30));

        ProfileEntity profileEntityOne = meetingProposalEntityOne.getProposerProfile();
        ProfileEntity profileEntityTwo = meetingProposalEntityTwo.getProposerProfile();

        event.attendees = getAttendeeList(profileEntityOne, profileEntityTwo);

        event.body = getItemBody(profileEntityOne, profileEntityTwo, meetingProposalEntityOne.getProposedDateTime());

        event.allowNewTimeProposals = false;
        event.isOnlineMeeting = true;
        event.onlineMeetingProvider = OnlineMeetingProviderType.TEAMS_FOR_BUSINESS;
        return event;
    }

    private DateTimeTimeZone getDateTimeTimeZone(LocalDateTime localDateTime) {
        DateTimeTimeZone dateTimeTimeZone = new DateTimeTimeZone();
        dateTimeTimeZone.dateTime = DiversityLunchTimeAndDateFormatter.convertFromUTCToTimeZoneAndFormat(localDateTime, diversityLunchMsTeamsProperties.getTimeZone());
        dateTimeTimeZone.timeZone = diversityLunchMsTeamsProperties.getTimeZone();
        return dateTimeTimeZone;
    }



    private ItemBody getItemBody(ProfileEntity profileEntityOne, ProfileEntity profileEntityTwo, LocalDateTime start) {
        ItemBody itemBody = new ItemBody();

        String profileNameOne = profileEntityOne.getName();
        String profileNameTwo = profileEntityTwo.getName();

        String date = DiversityLunchTimeAndDateFormatter.formatDate(start);
        String time = DiversityLunchTimeAndDateFormatter.convertFromUTCToTimeZoneAndFormatWithPattern(start, "HH:mm", diversityLunchMsTeamsProperties.getTimeZone());

        itemBody.content = eMailService.createMsTeamsMeetingTemplateHTML(profileNameOne, profileNameTwo, date, time);

        itemBody.contentType = BodyType.HTML;

        return itemBody;
    }

    private List<Attendee> getAttendeeList(ProfileEntity profileEntityOne, ProfileEntity profileEntityTwo) {
        Attendee attendeeOne = new Attendee();
        Attendee attendeeTwo = new Attendee();

        attendeeOne.emailAddress = getEmailAddress(profileEntityOne);
        attendeeTwo.emailAddress = getEmailAddress(profileEntityTwo);

        attendeeOne.type = AttendeeType.REQUIRED;
        attendeeTwo.type = AttendeeType.REQUIRED;

        return List.of(attendeeOne, attendeeTwo);
    }

    private EmailAddress getEmailAddress(ProfileEntity profileEntity) {
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.address = profileEntity.getEmail();
        emailAddress.name = profileEntity.getName();

        return emailAddress;
    }
}
