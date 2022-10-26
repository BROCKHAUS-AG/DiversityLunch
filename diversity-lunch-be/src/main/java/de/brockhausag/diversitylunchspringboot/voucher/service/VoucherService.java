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
        MeetingEntity meeting = meetingRepository.getById(meetingId);
        if (meeting == null) {
            throw new IllegalVoucherClaim("Du bist nicht berechtigt diesen Gutschein zu bekommen");
        }
        if(!(meeting.getPartner().getId() == profileId || meeting.getProposer().getId() == profileId)){
            throw new IllegalVoucherClaim("Du bist nicht berechtigt diesen Gutschein zu bekommen");
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
