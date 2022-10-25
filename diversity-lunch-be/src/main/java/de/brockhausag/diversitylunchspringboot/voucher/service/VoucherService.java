package de.brockhausag.diversitylunchspringboot.voucher.service;

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

    public Optional<VoucherEntity> getUnclaimedVoucherForMeeting(int profileId ,int meetingId){
        meetingRepository.getById(1L);
        return voucherRepository.getFirstByProfileIsNullAndMeetingIsNull();
    }
    public Iterable<VoucherEntity> getVoucherByProfileId(long profileId){
        return voucherRepository.getAllByProfile(profileId);
    }

    public class  IllegalDoubleVoucherClaim extends Exception{

        public IllegalDoubleVoucherClaim(String s) {
            super(s);
        }
    }
}
