package de.brockhausag.diversitylunchspringboot.voucher.mapper;

import de.brockhausag.diversitylunchspringboot.voucher.model.AdminVoucherDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;

public interface VoucherMapper {
    VoucherDto mapEntityToDto(VoucherEntity voucherEntity);
    AdminVoucherDto mapEntityToAdminVoucherDto(VoucherEntity voucherEntity);
}
