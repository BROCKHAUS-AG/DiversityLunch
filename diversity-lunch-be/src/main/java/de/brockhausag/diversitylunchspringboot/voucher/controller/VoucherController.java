package de.brockhausag.diversitylunchspringboot.voucher.controller;

import de.brockhausag.diversitylunchspringboot.voucher.mapper.VoucherMapper;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import de.brockhausag.diversitylunchspringboot.voucher.service.VoucherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voucher")
@Slf4j
@RequiredArgsConstructor
public class VoucherController {
    private final VoucherService voucherService;
    private final VoucherMapper voucherMapper;

    @PreAuthorize("isProfileOwner(#profileId)")
    @PutMapping("/claim/{profileId}/{meetingId}")
    public ResponseEntity<?> claimVoucher(@PathVariable Long profileId, @PathVariable Long meetingId){
        try{
            Optional<VoucherEntity> voucherEntity = voucherService.getUnclaimedVoucherForMeeting(profileId,meetingId);
            if(voucherEntity.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return  ResponseEntity.ok().body(voucherMapper.mapEntityToDto(voucherEntity.get()));
        }catch (VoucherService.IllegalVoucherClaim e){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @PreAuthorize("isProfileOwner(#profileId)")
    @GetMapping("/getVouchers/{profileId}")
    public ResponseEntity<List<VoucherDto>> getVouchers(@PathVariable Long profileId){
        List<VoucherEntity> voucherEntities = voucherService.getVoucherByProfileId(profileId);
        List<VoucherDto> voucherDtos = new ArrayList<VoucherDto>();
        for (VoucherEntity voucher: voucherEntities) {
            voucherDtos.add(voucherMapper.mapEntityToDto(voucher));
        }
        return ResponseEntity.ok().body(voucherDtos);
    }

}
