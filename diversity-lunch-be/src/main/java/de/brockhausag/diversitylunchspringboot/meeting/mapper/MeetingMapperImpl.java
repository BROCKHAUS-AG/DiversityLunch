package de.brockhausag.diversitylunchspringboot.meeting.mapper;

import de.brockhausag.diversitylunchspringboot.meeting.model.CreateMeetingProposalDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.profile.ProfileNotFoundException;
import de.brockhausag.diversitylunchspringboot.profile.services.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MeetingMapperImpl implements MeetingMapper {

    private final ProfileService profileService;

    @Override
    public Optional<MeetingProposalEntity> mapCreateDtoToEntity(final CreateMeetingProposalDto proposal, Long profileId) {
        Optional<ProfileEntity> profile = profileService.getProfile(profileId);

        if (profile.isEmpty()) {
            throw new ProfileNotFoundException();
        }

        LocalDateTime localDateTime = proposal.getFromDateTime();

        return Optional.of(MeetingProposalEntity.builder()
                .proposerProfile(profile.get())
                .proposedDateTime(localDateTime)
                .createdAt(LocalDateTime.now())
                .build());
    }

    @Override
    public MeetingDto mapEntityToDto(final MeetingProposalEntity proposal) {

        LocalDateTime proposalDateTime = proposal.getProposedDateTime();

        return MeetingDto.builder()
                .id(proposal.getId())
                .fromDateTime(proposalDateTime)
                .partnerName(null)
                .build();
    }

    @Override
    public MeetingDto mapMatchedMeetingToDto(MeetingEntity meeting, String partnerName) {
        return MeetingDto.builder()
                .id(meeting.getId())
                .fromDateTime(meeting.getFromDateTime())
                .partnerName(partnerName)
                .build();
    }
}
