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
        return new VoucherDto(voucherEntity.getId(),
                voucherEntity.getVoucher(),
                voucherEntity.getMeeting().getId(),
                voucherEntity.getProfile().getId());
    }
}
