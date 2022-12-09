package de.brockhausag.diversitylunchspringboot.meeting.service;

import de.brockhausag.diversitylunchspringboot.meeting.mapper.MeetingMapper;
import de.brockhausag.diversitylunchspringboot.meeting.model.DeclinedMeeting;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingDto;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingProposalRepository;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
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

        Stream<MeetingDto> matchedMeetings = Stream.concat(matchedMeetingsByProposer, matchedMeetingsByPartner);
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

    public boolean cancelMeeting(Long meetingId, Long profileId) {
        boolean success = false;
        Optional<MeetingEntity> meeting = getMeeting(meetingId);
        Optional<ProfileEntity> decliner = profileService.getProfile(profileId);
        List<ProfileEntity> declinerList = decliner.map(List::of).orElseGet(Collections::emptyList);

        if (meeting.isPresent() && decliner.isPresent()) {
            // is the profiledID even part of the meeting
            boolean isParticipant = Stream.of(meeting.get().getProposer(), meeting.get().getPartner())
                    .anyMatch(p -> p.getId().equals(profileId));
            if (isParticipant) {
                success = cancelMeeting(new DeclinedMeeting(meeting.get(), declinerList));
            } else {
                log.warn("Tried to cancel Meeting (Id: %d), where the deliner (Id: %d) is not participant of the Meeting"
                        .formatted(meeting.get().getId(), profileId));
            }
        }

        return success;
    }

    public boolean cancelMeeting(DeclinedMeeting declinedMeeting) {
        MeetingEntity meeting = declinedMeeting.meetingEntity();
        LocalDateTime today = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        LocalDateTime proposedDay = meeting.getFromDateTime().truncatedTo(ChronoUnit.DAYS);
        boolean success = false;

        if(proposedDay.isAfter(today) && declinedMeeting.decliner().size() == 1) {
            success = cancelMeetingForOneParticipant(meeting, declinedMeeting.decliner().get(0));
        }
        else if (today.isEqual(proposedDay) || proposedDay.isAfter(today)){
            success = cancelMeetingForAllParticipants(declinedMeeting);
        }

        return success;
    }

    boolean cancelMeetingForAllParticipants(DeclinedMeeting declinedMeeting) {
        List<Optional<MeetingProposalEntity>> meetingProposals = new ArrayList<>();
        meetingProposals.add(getMeetingProposalForProfile(declinedMeeting.meetingEntity(), declinedMeeting.meetingEntity().getProposer()));
        meetingProposals.add(getMeetingProposalForProfile(declinedMeeting.meetingEntity(), declinedMeeting.meetingEntity().getPartner()));

        boolean success = meetingProposals.stream().allMatch(Optional::isPresent);

        if (success) {
            deleteMeetingProposals(meetingProposals.stream().filter(Optional::isPresent).map(Optional::get).toList());
            cancelAndDeleteMeeting(declinedMeeting.meetingEntity());
            log.info("Deleted Meeting-Proposals for Meeting with id " + declinedMeeting.meetingEntity().getId());
        } else {
            log.warn("Tried to delete both MeetingProposals of a matching Meeting, but could not find all Proposals!");
        }

        return success;
    }

    boolean cancelMeetingForOneParticipant(MeetingEntity meeting, ProfileEntity decliner) {
        Optional<MeetingProposalEntity> meetingProposalDecliner = getMeetingProposalForProfile(meeting, decliner);
        Optional<MeetingProposalEntity> meetingProposalPartner = getMeetingProposalForProfile(meeting, getOtherParticipant(meeting, decliner));

        boolean success = meetingProposalDecliner.isPresent() && meetingProposalPartner.isPresent();

        if (success) {
            deleteMeetingProposals(List.of(meetingProposalDecliner.get()));
            cancelAndDeleteMeeting(meeting);
            meetingProposalPartner.get().setMatched(false);
            meetingProposalRepository.save(meetingProposalPartner.get());
            log.info("Deleted Meeting-Proposal for Decliner and enabled Meeting-Proposal for Partner (Meeting-Id: %d)".formatted(meeting.getId()));
        } else {
            log.warn("Tried to delete both MeetingProposals of a matching Meeting, but could not find all Proposals!");
        }

        return success;
    }

    ProfileEntity getOtherParticipant (MeetingEntity meeting, ProfileEntity participant) {
        return Objects.equals(meeting.getPartner().getId(), participant.getId()) ? meeting.getProposer() : meeting.getPartner();
    }

    void deleteMeetingProposals (List<MeetingProposalEntity> meetingProposals) {
        for (MeetingProposalEntity meetingProposal : meetingProposals) {
            meetingProposalRepository.deleteById(meetingProposal.getId());
        }
    }

    Optional<MeetingProposalEntity> getMeetingProposalForProfile (MeetingEntity meeting, ProfileEntity profile) {
        return meetingProposalRepository.findByProposedDateTimeAndProposerProfileAndMatchedTrue(meeting.getFromDateTime(), profile);
    }

    void cancelAndDeleteMeeting(MeetingEntity meeting) {
        msTeamsService.cancelMsTeamsMeeting(meeting);
        meetingRepository.delete(meeting);
        log.info("Deleted MeetingEntity and canceled MsTeamsMeeting for Meeting with id " + meeting.getId());
    }

    public int cancelDeclinedMeetings() {
        List<DeclinedMeeting> declinedMeetings = msTeamsService.getAllDeclinedMeetings();
        int successFullCanceledMeetings = 0;

        for (DeclinedMeeting declinedMeeting : declinedMeetings) {
            boolean couldCancel = cancelMeeting(declinedMeeting);
            if (couldCancel) {
                successFullCanceledMeetings++;
                log.info("Successfully canceled meeting with id %d".formatted(declinedMeeting.meetingEntity().getId()));
            } else {
                log.warn("Failed to cancel meeting with id %d".formatted(declinedMeeting.meetingEntity().getId()));
            }
        }

        log.info("Successfully canceled %d Meetings".formatted(successFullCanceledMeetings));
        return successFullCanceledMeetings;
    }
}
