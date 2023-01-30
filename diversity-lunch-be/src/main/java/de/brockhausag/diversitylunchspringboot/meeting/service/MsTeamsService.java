package de.brockhausag.diversitylunchspringboot.meeting.service;

import com.microsoft.graph.models.Event;
import de.brockhausag.diversitylunchspringboot.match.model.Matching;
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

    public String createMsTeamsMeeting(Matching matchEntity) {
        MeetingProposalEntity meetingProposalEntityOne = matchEntity.getFirstProposal();
        MeetingProposalEntity meetingProposalEntityTwo = matchEntity.getSecondProposal();

        Event event = msTeamsMapper.convertToMicrosoftEventModel(meetingProposalEntityOne, meetingProposalEntityTwo);
        Event result = microsoftGraphService.createEvent(event);

        return result.id;
    }


    public List<DeclinedMeeting> getAllDeclinedMeetings() {
        List<Event> allFutureEvents = microsoftGraphService.getAllFutureEvents();
        List<Event> canceledEvents = MicrosoftGraphService.filterDeclinedEvents(allFutureEvents);
        return buildDeclinedMeetings(canceledEvents);
    }

    public void cancelMsTeamsMeeting(MeetingEntity meeting)
    {
        String message = "Eine teilnehmende Person hat den Termin abgesagt.";
        microsoftGraphService.cancelEvent(meeting.getMsTeamsMeetingId(), message);
    }

    List<DeclinedMeeting> buildDeclinedMeetings(List<Event> canceledEvents) {
        List<DeclinedMeeting> declinedMeetings = new ArrayList<>();
        for(Event event: canceledEvents) {
            Optional<MeetingEntity> meetingEntity = meetingRepository.findByMsTeamsMeetingId(event.id);
            if(meetingEntity.isPresent()) {
                List<ProfileEntity> cancelerProfiles = List.of(
                        meetingEntity.get().getPartner(),
                        meetingEntity.get().getProposer());
                declinedMeetings.add(new DeclinedMeeting(meetingEntity.get(), cancelerProfiles));
            }
            else {
                log.warn("Could not find matching MeetingEntity for MsTeamsMeetingId %s".formatted(event.id));
            }
        }
        return declinedMeetings;
    }
}
