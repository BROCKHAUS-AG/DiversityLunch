package de.brockhausag.diversitylunchspringboot.meeting.service;

import de.brockhausag.diversitylunchspringboot.meeting.mapper.MeetingMapper;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingProposalRepository meetingProposalRepository;
    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;
    private final ProfileService profileService;

    public Optional<MeetingProposalEntity> getMeetingProposal(long id) {
        return this.meetingProposalRepository.findById(id);
    }

    public List<MeetingProposalEntity> getAllMeetingProposals(long profileId) {

        return profileService.getProfile(profileId)
                .map(meetingProposalRepository::findByProposerProfile)
                .orElse(Collections.emptyList());
    }

    public List<MeetingDto> getAllMeetingsInFutureForUser(long profileId) {
        ProfileEntity profile = profileService
                .getProfile(profileId)
                .orElseThrow(() -> new IllegalArgumentException("given profileId does not exist"));

        LocalDateTime today = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        Stream<MeetingDto> matchedMeetingsByPartner = meetingRepository.findByPartnerAndFromDateTimeIsAfter(profile, today)
                .stream()
                .map(m -> meetingMapper.mapMatchedMeetingToDto(m, m.getProposer().getName()));

        Stream<MeetingDto> matchedMeetingsByProposer = meetingRepository.findByProposerAndFromDateTimeIsAfter(profile, today)
                .stream()
                .map(m -> meetingMapper.mapMatchedMeetingToDto(m, m.getPartner().getName()));

        Stream<MeetingDto> meetingProposals =
                meetingProposalRepository.findByProposerProfileAndMatchedFalseAndProposedDateTimeIsAfter(profile, today)
                .stream()
                .map(meetingMapper::mapEntityToDto);

        Stream<MeetingDto> matchedMeetings = Stream.concat(matchedMeetingsByProposer,  matchedMeetingsByPartner);
        return Stream.concat(matchedMeetings, meetingProposals).toList();
    }

    public MeetingProposalEntity createMeetingProposal(MeetingProposalEntity meetingProposalEntity) {
        return meetingProposalRepository.save(meetingProposalEntity);
    }

    public void deleteMeetingProposal(long id) {
        meetingProposalRepository.deleteById(id);
    }

    public boolean meetingExists(long profileId, LocalDateTime newMeetingTime) {
        List<MeetingProposalEntity> currentMeetings = this.getAllMeetingProposals(profileId);
        return currentMeetings
                .stream()
                .anyMatch(meeting -> meeting.getProposedDateTime().isEqual(newMeetingTime));
    }
}
