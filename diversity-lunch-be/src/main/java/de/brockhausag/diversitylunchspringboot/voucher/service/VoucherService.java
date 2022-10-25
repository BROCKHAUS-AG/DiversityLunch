package de.brockhausag.diversitylunchspringboot.voucher.service;

import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import de.brockhausag.diversitylunchspringboot.voucher.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoucherService {
    private final VoucherRepository voucherRepository;

    public Optional<VoucherEntity> getFirstUnclaimedVoucher(){
        return voucherRepository.getFirstByProfileIsNullAndMeetingIsNull();
    }
    public Iterable<VoucherEntity> getVoucherByProfileId(long profileId){
        return voucherRepository.getAllByProfileId(profileId);
    }
}
