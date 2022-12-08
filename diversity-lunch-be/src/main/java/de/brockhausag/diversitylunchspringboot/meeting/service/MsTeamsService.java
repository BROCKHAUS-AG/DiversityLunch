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

    public void cancelMsTeamsMeeting(MeetingEntity meeting)
    {
        String message = "Ein Teilnehmer hat den Termin abgesagt.";
        microsoftGraphService.cancelEvent(meeting.getMsTeamsMeetingId(), message);
    }

    public List<DeclinedMeeting> getAllDeclinedMeetings() {
        List<Event> allFutureEvents = microsoftGraphService.getAllFutureEvents();
        List<Event> canceledEvents = MicrosoftGraphService.filterDeclinedEvents(allFutureEvents);
        return buildDeclinedMeetings(canceledEvents);
    }

    List<DeclinedMeeting> buildDeclinedMeetings(List<Event> canceledEvents) {
        List<DeclinedMeeting> declinedMeetings = new ArrayList<>();
        for(Event event: canceledEvents) {
            Optional<MeetingEntity> meetingEntity = meetingRepository.findByMsTeamsMeetingId(event.id);
            if(meetingEntity.isPresent()) {
                String emailCanceled = getCancelerEmail(event);
                ProfileEntity cancelerProfile = getCancelerProfileByEmail(meetingEntity.get(), emailCanceled);
                declinedMeetings.add(new DeclinedMeeting(meetingEntity.get(), cancelerProfile));
            }
        }
        return declinedMeetings;
    }

    static String getCancelerEmail(Event event) {
        String emailCanceled = "";
        for(Attendee attendee: event.attendees) {
            if(attendee.status.response == ResponseType.DECLINED)
            {
                emailCanceled = attendee.emailAddress.address;
                break;
            }
        }
        return emailCanceled;
    }

    static ProfileEntity getCancelerProfileByEmail(MeetingEntity meetingEntity, String emailCanceled) {
        ProfileEntity cancelerProfile;
        if(meetingEntity.getProposer().getEmail().equalsIgnoreCase(emailCanceled)) {
            cancelerProfile = meetingEntity.getProposer();
        }
        else {
            cancelerProfile = meetingEntity.getPartner();
        }
        return cancelerProfile;
    }
}
