package de.brockhausag.diversitylunchspringboot.voucher.mapper;

import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VoucherMapperImpl implements VoucherMapper{

    @Override
    public VoucherDto mapEntityToDto(VoucherEntity voucherEntity) {
        if(voucherEntity.getProfile() != null && voucherEntity.getMeeting() != null) {
            return new VoucherDto(voucherEntity.getId(),
                    voucherEntity.getVoucher(),
                    voucherEntity.getProfile().getId(),
                    voucherEntity.getMeeting().getId());
        }
        else {
            return new VoucherDto(voucherEntity.getId(),
                    voucherEntity.getVoucher(),
                    -1,
                    -1);
        }
    }
}
