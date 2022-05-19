package de.brockhausag.diversitylunchspringboot.meeting.mapper;

import de.brockhausag.diversitylunchspringboot.meeting.model.CreateMeetingProposalDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;

import java.util.Optional;

public interface MeetingMapper {
    Optional<MeetingProposalEntity> mapCreateDtoToEntity(CreateMeetingProposalDto proposal, long profileId);

    MeetingDto mapEntityToDto(MeetingProposalEntity proposal);

    MeetingDto mapMatchedMeetingToDto(MeetingEntity meeting, String partnerName);
}
