package de.brockhausag.diversitylunchspringboot.meeting.service;

import de.brockhausag.diversitylunchspringboot.meeting.mapper.MeetingMapper;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
    private final MsTeamsService msTeamsService;

    public Optional<MeetingProposalEntity> getMeetingProposal(Long id) {
        return this.meetingProposalRepository.findById(id);
    }

    public List<MeetingProposalEntity> getAllMeetingProposals(Long profileId) {

        return profileService.getProfile(profileId)
                .map(meetingProposalRepository::findByProposerProfile)
                .orElse(Collections.emptyList());
    }

    public List<MeetingDto> getAllMeetingsInFutureForUser(Long profileId) {
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

    public void deleteMeetingProposal(Long id) {
        meetingProposalRepository.deleteById(id);
    }

    public boolean meetingExists(Long profileId, LocalDateTime newMeetingTime) {
        List<MeetingProposalEntity> currentMeetings = this.getAllMeetingProposals(profileId);
        return currentMeetings
                .stream()
                .anyMatch(meeting -> meeting.getProposedDateTime().isEqual(newMeetingTime));
    }

    public boolean checkIfMeetingProposalIsMatched(Long id) {
        MeetingProposalEntity meetingProposal = meetingProposalRepository.getById(id);
        return meetingProposal.isMatched();
    }

    public Optional<MeetingEntity> getMeeting(Long id) {
        return meetingRepository.findById(id);
    }

    public void cancelMeeting(Long meetingId, Long userId)
    {
        MeetingEntity meeting = meetingRepository.findById(meetingId).orElseThrow();
        ProfileEntity profileCaller;
        ProfileEntity profileOther;
        if(meeting.getProposer().getId().equals(userId))
        {
            profileCaller = meeting.getProposer();
            profileOther = meeting.getPartner();
        }
        else
        {
            profileCaller = meeting.getPartner();
            profileOther = meeting.getProposer();
        }

        // 1. Get the proposals of both users
        MeetingProposalEntity meetingProposalCaller = meetingProposalRepository
                .findByProposedDateTimeAndProposerProfileAAndMatchedTrue(meeting.getFromDateTime(), profileCaller)
                .orElseThrow();

        MeetingProposalEntity meetingProposalOther = meetingProposalRepository
                .findByProposedDateTimeAndProposerProfileAAndMatchedTrue(meeting.getFromDateTime(), profileOther)
                .orElseThrow();

        // 2. delete proposal for caller
        meetingProposalRepository.deleteById(meetingProposalCaller.getId());

        // 3. reactivate proposal for the other person (So that he can get matched again)
        LocalDateTime today = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        LocalDateTime proposedDay = meeting.getFromDateTime().truncatedTo(ChronoUnit.DAYS);
        if(today.equals(proposedDay)) {
            meetingProposalRepository.deleteById(meetingProposalOther.getId());
        }
        else {
            meetingProposalOther.setMatched(false);
            meetingProposalRepository.save(meetingProposalOther);
        }

        // 3. Cancel MS meeting and delete meeting
        msTeamsService.cancelMsTeamsMeeting(meeting);
        meetingRepository.delete(meeting);
    }
}
