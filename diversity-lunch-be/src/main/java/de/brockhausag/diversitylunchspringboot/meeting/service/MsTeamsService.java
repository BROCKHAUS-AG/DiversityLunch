package de.brockhausag.diversitylunchspringboot.meeting.service;

import com.microsoft.graph.models.Event;
import de.brockhausag.diversitylunchspringboot.match.utils.Match;
import de.brockhausag.diversitylunchspringboot.meeting.mapper.MsTeamsMapper;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class MsTeamsService {

    private final MsTeamsMapper msTeamsMapper;
    private final MicrosoftGraphService microsoftGraphService;


    public String createMsTeamsMeeting(Match matchEntity) {
        MeetingProposalEntity meetingProposalEntityOne = matchEntity.proposalOne();
        MeetingProposalEntity meetingProposalEntityTwo = matchEntity.proposalTwo();

        Event event = msTeamsMapper.convertToMicrosoftEventModel(meetingProposalEntityOne, meetingProposalEntityTwo);
        Event result = microsoftGraphService.createEvent(event);

        return result.id;
    }
}
