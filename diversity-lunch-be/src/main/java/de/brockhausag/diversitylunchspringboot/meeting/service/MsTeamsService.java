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
import org.springframework.context.annotation.Lazy;
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
        List<DeclinedMeeting> declinedMeetings = new ArrayList<>();

        Optional<List<Event>> allFutureEvents = microsoftGraphService.getAllFutureEvents();
        if(allFutureEvents.isPresent()) {
            List<Event> canceledEvents = MicrosoftGraphService.filterDeclinedEvents(allFutureEvents.get());
            for(Event e: canceledEvents) {
                Optional<MeetingEntity> meetingEntity = meetingRepository.findByMsTeamsMeetingId(e.id);
                if(meetingEntity.isPresent()) {
                    String emailCanceled = "";
                    for(Attendee attendee: e.attendees) {
                        if(attendee.status.response == ResponseType.DECLINED)
                        {
                            emailCanceled = attendee.emailAddress.address;
                            break;
                        }
                    }
                    ProfileEntity cancelerProfile;
                    if(meetingEntity.get().getProposer().getEmail().equalsIgnoreCase(emailCanceled)) {
                        cancelerProfile = meetingEntity.get().getProposer();
                    }
                    else {
                        cancelerProfile = meetingEntity.get().getPartner();
                    }
                    declinedMeetings.add(new DeclinedMeeting(meetingEntity.get(), cancelerProfile));
                }
            }
        }
        return declinedMeetings;
    }
}
