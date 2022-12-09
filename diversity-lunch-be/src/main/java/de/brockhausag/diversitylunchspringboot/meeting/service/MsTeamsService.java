package de.brockhausag.diversitylunchspringboot.meeting.service;

import com.microsoft.graph.models.Attendee;
import com.microsoft.graph.models.Event;
import com.microsoft.graph.models.ResponseType;
import de.brockhausag.diversitylunchspringboot.match.records.Match;
import de.brockhausag.diversitylunchspringboot.meeting.mapper.MsTeamsMapper;
import de.brockhausag.diversitylunchspringboot.meeting.model.DeclinedMeeting;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class MsTeamsService {
    private final MsTeamsMapper msTeamsMapper;
    private final MicrosoftGraphService microsoftGraphService;
    private final MeetingRepository meetingRepository;

    public String createMsTeamsMeeting(Match matchEntity) {
        MeetingProposalEntity meetingProposalEntityOne = matchEntity.proposalOne();
        MeetingProposalEntity meetingProposalEntityTwo = matchEntity.proposalTwo();

        Event event = msTeamsMapper.convertToMicrosoftEventModel(meetingProposalEntityOne, meetingProposalEntityTwo);
        Event result = microsoftGraphService.createEvent(event);

        return result.id;
    }

    static List<String> getCancelerEmail(Event event) {
        List<String> emailsCanceled = new ArrayList<>();
        if (event != null && event.attendees != null) {
            for (Attendee attendee : event.attendees) {
                if (attendee.status != null
                    && attendee.emailAddress != null
                    && attendee.status.response == ResponseType.DECLINED
                ) {
                        emailsCanceled.add(attendee.emailAddress.address);
                }
            }
        }
        return emailsCanceled;
    }

    public List<DeclinedMeeting> getAllDeclinedMeetings() {
        List<Event> allFutureEvents = microsoftGraphService.getAllFutureEvents();
        List<Event> canceledEvents = MicrosoftGraphService.filterDeclinedEvents(allFutureEvents);
        return buildDeclinedMeetings(canceledEvents);
    }

    public void cancelMsTeamsMeeting(MeetingEntity meeting)
    {
        String message = "Einer der Teilnehmenden hat den Termin abgesagt.";
        microsoftGraphService.cancelEvent(meeting.getMsTeamsMeetingId(), message);
    }

    Optional<ProfileEntity> getCancelerProfileByEmail(MeetingEntity meetingEntity, String emailCanceled) {
        ProfileEntity cancelerProfile = null;
        if(meetingEntity.getProposer().getEmail().equalsIgnoreCase(emailCanceled)) {
            cancelerProfile = meetingEntity.getProposer();
        }
        else if (meetingEntity.getPartner().getEmail().equalsIgnoreCase(emailCanceled)){
            cancelerProfile = meetingEntity.getPartner();
        }
        return Optional.ofNullable(cancelerProfile);
    }

    List<DeclinedMeeting> buildDeclinedMeetings(List<Event> canceledEvents) {
        List<DeclinedMeeting> declinedMeetings = new ArrayList<>();
        for(Event event: canceledEvents) {
            Optional<MeetingEntity> meetingEntity = meetingRepository.findByMsTeamsMeetingId(event.id);
            if(meetingEntity.isPresent()) {
                List<String> emailsCanceled = getCancelerEmail(event);
                List<ProfileEntity> cancelerProfiles = emailsCanceled.stream()
                        .map(mail -> getCancelerProfileByEmail(meetingEntity.get(), mail))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .toList();
                declinedMeetings.add(new DeclinedMeeting(meetingEntity.get(), cancelerProfiles));
            }
        }
        return declinedMeetings;
    }
}
