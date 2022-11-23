package de.brockhausag.diversitylunchspringboot.voucher.mapper;

import de.brockhausag.diversitylunchspringboot.meeting.service.MeetingService;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import de.brockhausag.diversitylunchspringboot.meeting.service.MeetingService;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.utils.mapper.Mapper;
import de.brockhausag.diversitylunchspringboot.voucher.model.AdminVoucherDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VoucherMapper implements Mapper<VoucherDto, VoucherEntity> {

    private final ProfileService profileService;
    private final MeetingService meetingService;
    @Override
    public VoucherDto entityToDto(VoucherEntity entity) {
        return new VoucherDto(
                entity.getId(),
                entity.getVoucher(),
                entity.getProfile().getId(),
                entity.getMeeting().getId()
        );
    }

    @Override
    public VoucherEntity dtoToEntity(VoucherDto dto) {
        return new VoucherEntity(
                dto.getUuid(),
                profileService.getProfile(dto.getProfileId()).orElseThrow(),
                dto.getVoucherCode(),
                meetingService.getMeeting(dto.getMeetingId()).orElseThrow()
        );
    }

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
