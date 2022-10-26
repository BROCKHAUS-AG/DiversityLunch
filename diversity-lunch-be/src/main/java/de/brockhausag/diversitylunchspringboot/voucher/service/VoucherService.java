package de.brockhausag.diversitylunchspringboot.voucher.service;

import com.tngtech.archunit.lang.ArchRule;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.data.ProfileRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import de.brockhausag.diversitylunchspringboot.voucher.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class VoucherService {
    private final VoucherRepository voucherRepository;
    private final MeetingRepository meetingRepository;
    private final ProfileRepository profileRepository;
    public final String NOT_ALLOWED = "Du bist nicht berechtigt diesen Gutschein zu bekommen";
    public final String ALREADY_CLAIMED = "Der Gutschein wurde bereits angefordert.";
    public final String NOT_FOUND = "Keine Vouchers mehr da";
    public Optional<VoucherEntity> getUnclaimedVoucherForMeeting(long profileId, long meetingId) throws IllegalVoucherClaim {

        MeetingEntity meeting = meetingRepository.getById(meetingId);
        Optional<ProfileEntity> profileEntity = profileRepository.findById(profileId);

        if (meeting == null || profileEntity.isEmpty()) {
            throw new IllegalVoucherClaim(NOT_ALLOWED);
        }
        if(!(meeting.getPartner().getId() == profileId || meeting.getProposer().getId() == profileId)){
            throw new IllegalVoucherClaim(NOT_ALLOWED);
        }
        if (voucherRepository.existsByProfileIdAndMeetingId(profileId, meetingId)) {
            throw new IllegalVoucherClaim(ALREADY_CLAIMED);
        }
        int test = 0;
        Optional<VoucherEntity> voucherEntity = voucherRepository.getFirstByProfileIsNullAndMeetingIsNull();

        if(voucherEntity.isEmpty()){
            throw new IllegalVoucherClaim(NOT_FOUND);
        }

        voucherEntity.get().setProfile(profileEntity.get());
        voucherEntity.get().setMeeting(meeting);
        voucherRepository.save(voucherEntity.get());

        return voucherEntity;
    }

    public List<VoucherEntity> getVoucherByProfileId(long profileId) {
        return voucherRepository.getAllByProfileId(profileId);
    }

    public static class IllegalVoucherClaim extends Exception {

        public IllegalVoucherClaim(String s) {
            super(s);
        }

    }
}
