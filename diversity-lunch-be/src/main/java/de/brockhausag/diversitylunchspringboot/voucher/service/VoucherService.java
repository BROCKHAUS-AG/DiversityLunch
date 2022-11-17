package de.brockhausag.diversitylunchspringboot.voucher.service;

import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.data.ProfileRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.voucher.exception.ForbiddenVoucherClaim;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import de.brockhausag.diversitylunchspringboot.voucher.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoucherService {
    private final VoucherRepository voucherRepository;
    private final MeetingRepository meetingRepository;
    private final ProfileRepository profileRepository;

    public Optional<VoucherEntity> getUnclaimedVoucherForMeeting(long profileId, long meetingId) throws ForbiddenVoucherClaim {
        Optional<ProfileEntity> profileEntity = profileRepository.findById(profileId);
        Optional<MeetingEntity> meeting = meetingRepository.findById(meetingId);
        Optional<VoucherEntity> voucherEntity = voucherRepository.getFirstByProfileIsNullAndMeetingIsNull();

        if (meeting.isEmpty()|| profileEntity.isEmpty() || !(meeting.get().getPartner().getId() == profileId || meeting.get().getProposer().getId() == profileId)) {
            throw new ForbiddenVoucherClaim();
        }

        if (voucherRepository.existsByProfileIdAndMeetingId(profileId, meetingId)) {
            return voucherRepository.getVoucherEntityByProfileIdAndMeetingId(profileId, meetingId);
        }

        if (voucherEntity.isPresent()) {
            voucherEntity.get().setProfile(profileEntity.get());
            voucherEntity.get().setMeeting(meeting.get());
            voucherRepository.save(voucherEntity.get());
        }

        return voucherEntity;
    }

    public boolean checkForClaimedVoucher(long profileId, long meetingId) {
        return voucherRepository.existsByProfileIdAndMeetingId(profileId, meetingId);
    }

    public Optional<VoucherEntity> getVoucherByProfileIdAndMeetingId(long profileId, long meetingId) {
        return voucherRepository.getVoucherEntityByProfileIdAndMeetingId(profileId, meetingId);
    }

    public List<VoucherEntity> getVoucherByProfileId(long profileId) {
        return voucherRepository.getAllByProfileId(profileId);
    }

    public void saveVouchers(Iterable<VoucherEntity> voucherEntities) {
        voucherRepository.saveAll(voucherEntities);
    }

    public int getAmountOfStoredVouchers() {
        return voucherRepository.findAll().size();
    }

    public int getAmountOfUnclaimedVouchers() {
        return voucherRepository.countAllByProfileIsNullAndMeetingIsNull();
    }
    public int getAmountOfClaimedVouchers() {
        return voucherRepository.countAllByProfileIsNotNullAndMeetingIsNotNull();
    }
}
