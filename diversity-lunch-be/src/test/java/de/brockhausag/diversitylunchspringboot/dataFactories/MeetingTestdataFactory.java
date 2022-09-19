package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.meeting.model.CreateMeetingProposalDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MeetingTestdataFactory {

    private static final long id = 1L;
    private static final ProfileEntity profileEntity = new ProfileTestdataFactory().buildEntity(1);
    private static final LocalDateTime localDateTime = LocalDateTime.of(2022, 2, 14, 10, 30, 0, 0);
    private static final LocalDateTime createdAt = LocalDateTime.of(2022, 2, 14, 16, 1, 0, 0);
    private static final boolean matched = false;


    public MeetingProposalEntity.MeetingProposalEntityBuilder entityBuilder() {
        return createEntityBuilder().id(id);
    }

    public MeetingProposalEntity.MeetingProposalEntityBuilder createEntityBuilder() {
        return MeetingProposalEntity.builder()
                .proposerProfile(profileEntity)
                .proposedDateTime(localDateTime)
                .createdAt(createdAt)
                .matched(matched);
    }

    public MeetingProposalEntity matchedEntity() {
        return entityBuilder().build();
    }

    public MeetingProposalEntity unmatchedEntity(){
        return entityBuilder().matched(false).build();
    }

    public MeetingProposalEntity createEntity(){
        return createEntityBuilder().build();
    }


    public MeetingDto.MeetingDtoBuilder dtoBuilder() {
        return MeetingDto.builder()
                .id(id)
                .fromDateTime(localDateTime)
                .partnerName(null);
    }

    public MeetingDto matchedDto() {
        return dtoBuilder()
                .partnerName(profileEntity.getName())
                .build();
    }

    public MeetingProposalEntity entity() {
        return this.entityBuilder().build();
    }

    public MeetingDto dto() {
        return this.dtoBuilder().build();

    }

    public MeetingDto unmatchedDto() {
        return dtoBuilder().build();
    }

    public CreateMeetingProposalDto createDto() {
        return CreateMeetingProposalDto.builder()
                .fromDateTime(localDateTime)
                .build();
    }

    public List<MeetingProposalEntity> newMeetingProposalList(LocalDateTime time) {
        MeetingProposalEntity meetingProposal = entityBuilder().proposedDateTime(time).build();
        MeetingProposalEntity meetingProposal2 = entityBuilder().id(meetingProposal.getId() + 1).proposedDateTime(time).
                proposerProfile(new ProfileTestdataFactory().buildEntity(2)).build();
        MeetingProposalEntity[] meetings = {meetingProposal, meetingProposal2};
        return Stream.of(meetings).collect(Collectors.toList());
    }
}
