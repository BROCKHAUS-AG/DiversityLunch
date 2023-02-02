package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.meeting.model.*;
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

    public MeetingProposalEntity unmatchedEntity() {
        return entityBuilder().matched(false).build();
    }

    public MeetingProposalEntity createEntity() {
        return createEntityBuilder().build();
    }


    public MeetingDto.MeetingDtoBuilder dtoBuilder() {
        return MeetingDto.builder()
                .id(id)
                .fromDateTime(localDateTime)
                .partnerName(null);
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

    public MeetingEntity matchedMeeting(ProfileEntity proposerEntity, ProfileEntity partnerEntity) {
        LocalDateTime date = LocalDateTime.of(2011, 11, 11, 11, 11);
        DimensionCategory category = DimensionCategory.builder().id(1L).description("Projekt").profileQuestion("Projekt?").build();
        QuestionEntity question = new QuestionTestDataFactory().buildEntity(category);
        return new MeetingEntity(1L, date, partnerEntity, proposerEntity, 1, question, date, "");
    }

    public MeetingEntity matchedAnotherMeeting(ProfileEntity proposerEntity, ProfileEntity partnerEntity) {
        LocalDateTime date = LocalDateTime.of(2011, 11, 11, 11, 11);
        DimensionCategory category = DimensionCategory.builder().id(1L).description("Berufserfahrung").profileQuestion("Berufserfahrung?").build();
        QuestionEntity question = new QuestionTestDataFactory().buildEntity(category);
        return new MeetingEntity(1L, date, partnerEntity, proposerEntity, 1, question, date, "");
    }

    public List<MeetingProposalEntity> newMeetingProposalList_withMatchingScore29(LocalDateTime time) {
        ProfileEntity secondProposer = new ProfileTestdataFactory().buildEntity(2);
        MeetingProposalEntity meetingProposal = entityBuilder().proposedDateTime(time).build();
        MeetingProposalEntity meetingProposal2 = entityBuilder().id(meetingProposal.getId() + 1).proposedDateTime(time).
                proposerProfile(secondProposer).build();
        MeetingProposalEntity[] meetings = {meetingProposal, meetingProposal2};
        return Stream.of(meetings).collect(Collectors.toList());
    }

    public List<MeetingProposalEntity> newMeetingProposalList_withMatchingScore1(LocalDateTime time) {
        ProfileEntity secondProposer = new ProfileTestdataFactory().buildEntity(1);
        secondProposer.setId(2L);
        MeetingProposalEntity meetingProposal = entityBuilder().proposedDateTime(time).build();
        MeetingProposalEntity meetingProposal2 = entityBuilder().id(meetingProposal.getId() + 1).proposedDateTime(time).
                proposerProfile(secondProposer).build();
        MeetingProposalEntity[] meetings = {meetingProposal, meetingProposal2};
        return Stream.of(meetings).collect(Collectors.toList());
    }
}
