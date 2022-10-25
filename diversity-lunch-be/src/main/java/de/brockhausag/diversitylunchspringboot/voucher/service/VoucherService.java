package de.brockhausag.diversitylunchspringboot.voucher.service;

import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import de.brockhausag.diversitylunchspringboot.voucher.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoucherService {
    private final VoucherRepository voucherRepository;
    private final MeetingRepository meetingRepository;

    public Optional<VoucherEntity> getUnclaimedVoucherForMeeting(long profileId, long meetingId) throws IllegalVoucherClaim {


        if (meetingRepository.existsById(meetingId)) {
            MeetingEntity meetingEntity = meetingRepository.getById(meetingId);
            if(meetingEntity.getPartner().getId() != profileId && meetingEntity.getProposer().getId() != profileId){
                throw new IllegalVoucherClaim("Du ist nicht berechtigt diesen Gutschein zu bekommen");
            }
        }


        if (voucherRepository.existsByProfileAndMeeting(profileId, meetingId)) {
            throw new IllegalVoucherClaim("Der Gutschein wurde bereits angefordert.");
        }

        return voucherRepository.getFirstByProfileIsNullAndMeetingIsNull();
    }

    public Iterable<VoucherEntity> getVoucherByProfileId(long profileId) {
        return voucherRepository.getAllByProfile(profileId);
    }

    public class IllegalVoucherClaim extends Exception {

        public IllegalVoucherClaim(String s) {
            super(s);
        }

    }
}
