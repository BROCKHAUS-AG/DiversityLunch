package de.brockhausag.diversitylunchspringboot.voucher.mapper;

import de.brockhausag.diversitylunchspringboot.voucher.model.AdminVoucherDto;
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
                    voucherEntity.getProfile().getId(),
                    voucherEntity.getMeeting().getId());

    }

    @Override
    public AdminVoucherDto mapEntityToAdminVoucherDto(VoucherEntity voucherEntity)
    {
        if(voucherEntity.getProfile() != null)
        {
            return new AdminVoucherDto(
                    voucherEntity.getVoucher(),
                    voucherEntity.getProfile().getEmail()
            );
        }
        else
        {
            return new AdminVoucherDto(
                    voucherEntity.getVoucher(),
                    "Nicht beansprucht"
            );
        }

    }




}
