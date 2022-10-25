package de.brockhausag.diversitylunchspringboot.voucher.mapper;

import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;

public class VoucherMapperImpl implements VoucherMapper{

    @Override
    public VoucherDto mapEntityToDto(VoucherEntity voucherEntity) {
        return new VoucherDto(voucherEntity.getUuid(),
                voucherEntity.getVoucherCode(),
                voucherEntity.getMeeting().getId(),
                voucherEntity.getProfile().getId());
    }
}
