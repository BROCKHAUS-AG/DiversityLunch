package de.brockhausag.diversitylunchspringboot.voucher.service;

import com.google.common.collect.Iterables;
import com.tngtech.archunit.lang.ArchRule;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.data.ProfileRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.voucher.exception.IllegalVoucherClaim;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import de.brockhausag.diversitylunchspringboot.voucher.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class VoucherService {
    private final VoucherRepository voucherRepository;
    private final MeetingRepository meetingRepository;
    private final ProfileRepository profileRepository;
    public static final String NOT_ALLOWED = "Du bist nicht berechtigt diesen Gutschein zu bekommen";
    public static final String ALREADY_CLAIMED = "Der Gutschein wurde bereits angefordert.";
    public static final String NOT_FOUND = "Keine Vouchers mehr da";

    public Optional<VoucherEntity> getUnclaimedVoucherForMeeting(long profileId, long meetingId) throws IllegalVoucherClaim {

        Optional<MeetingEntity> meeting = meetingRepository.findById(meetingId);
        Optional<ProfileEntity> profileEntity = profileRepository.findById(profileId);
        Optional<VoucherEntity> voucherEntity = voucherRepository.getFirstByProfileIsNullAndMeetingIsNull();

        if (meeting.isEmpty()|| profileEntity.isEmpty() || !(meeting.get().getPartner().getId() == profileId || meeting.get().getProposer().getId() == profileId)) {
            throw new IllegalVoucherClaim(NOT_ALLOWED); // TODO: Different kinds of exceptions classes should be use instead of differentiating them by some string like NOT_ALLOWED, ALREADE_CLAIMED,  or NOT_FOUND. These exceptions should inherit from IllegalVoucherClaim exception. ~tgohlisch 16.11.2022
        }
        if (voucherRepository.existsByProfileIdAndMeetingId(profileId, meetingId)) {
            throw new IllegalVoucherClaim(ALREADY_CLAIMED);
        }

        if (voucherEntity.isEmpty()) {
            throw new IllegalVoucherClaim(NOT_FOUND);
        }

        voucherEntity.get().setProfile(profileEntity.get());
        voucherEntity.get().setMeeting(meeting.get());
        voucherRepository.save(voucherEntity.get());

        return voucherEntity;
    }

    public List<VoucherEntity> getVoucherByProfileId(long profileId) {
        return voucherRepository.getAllByProfileId(profileId);
    }
    public boolean saveVouchers(Iterable<VoucherEntity> voucherEntities){
        Iterable<VoucherEntity> savedVouchers = voucherRepository.saveAll(voucherEntities);
        return Iterables.size(savedVouchers) == Iterables.size(voucherEntities);
    }

    public int getAmountOfVouchersStored(){
        return voucherRepository.findAll().size();
    }

    public List<VoucherEntity> getAllVouchersStored(){
        return voucherRepository.findAll();
    }

    public static class IllegalVoucherClaim extends Exception {

        public IllegalVoucherClaim(String s) {
            super(s);
        }

    }
}
