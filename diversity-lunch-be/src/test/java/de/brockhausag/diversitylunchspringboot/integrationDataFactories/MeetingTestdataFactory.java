package de.brockhausag.diversitylunchspringboot.integrationDataFactories;

import de.brockhausag.diversitylunchspringboot.meeting.model.CreateMeetingProposalDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MeetingTestdataFactory {
    private final MeetingProposalRepository meetingProposalRepository;
    private final LocalDateTime localDateTime = LocalDateTime.of(2022, 2, 14, 10, 30, 0, 0);
    private final LocalDateTime createdAt = LocalDateTime.of(2022, 2, 14, 16, 1, 0, 0);


    public void create(ProfileEntity profileEntity) {
        MeetingProposalEntity meetingProposalEntity = MeetingProposalEntity.builder()
                .proposerProfile(profileEntity)
                .proposedDateTime(localDateTime)
                .createdAt(createdAt)
                .matched(false)
                .build();
        meetingProposalRepository.save(meetingProposalEntity);
    }

    public CreateMeetingProposalDto createDto() {
        return CreateMeetingProposalDto.builder()
                .fromDateTime(localDateTime)
                .build();
    }
}
