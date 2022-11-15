package de.brockhausag.diversitylunchspringboot.voucher.controller;

import de.brockhausag.diversitylunchspringboot.voucher.mapper.VoucherMapper;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import de.brockhausag.diversitylunchspringboot.voucher.service.VoucherService;
import de.brockhausag.diversitylunchspringboot.voucher.utils.VoucherCSVHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voucher")
@Slf4j
@RequiredArgsConstructor
public class VoucherController {
    private final VoucherService voucherService;
    private final VoucherMapper voucherMapper;


    @GetMapping("/amount")
    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).ADMIN_ROLE_ASSIGN)")
    public ResponseEntity<Integer> getAmountOfVouchersStored() {
        return ResponseEntity.ok( voucherService.getAmountOfVouchersStored());
    }
    @PreAuthorize("isProfileOwner(#profileId)")
    @PutMapping("/claim/{profileId}/{meetingId}")
    public ResponseEntity<?> claimVoucher(@PathVariable Long profileId, @PathVariable Long meetingId) {
        try {
            Optional<VoucherEntity> voucherEntity;
            if(voucherService.checkForClaimedVoucher(profileId,meetingId)){
                voucherEntity = voucherService.getVoucherByProfileIdAndMeetingId(profileId,meetingId);
            }else{
                voucherEntity = voucherService.getUnclaimedVoucherForMeeting(profileId, meetingId);
            }

            if (voucherEntity.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            String html = voucherEntity.get().getVoucher();
            return ResponseEntity.ok().body(html);
        } catch (VoucherService.IllegalVoucherClaim e) {
            String errorHtml = "<html> <body> <h5>UPSI! Leider ist etwas schief gelaufen. <br> Vielen dank für deine Teilnahme am Diversity Lunch.</h5> </body> <html>";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorHtml);
        }
    }

    @PreAuthorize("isProfileOwner(#profileId)")
    @GetMapping("/all/{profileId}")
    public ResponseEntity<List<VoucherDto>> getVouchers(@PathVariable Long profileId) {
        List<VoucherEntity> voucherEntities = voucherService.getVoucherByProfileId(profileId);
        List<VoucherDto> voucherDtos = new ArrayList<>();
        for (VoucherEntity voucher : voucherEntities) {
            voucherDtos.add(voucherMapper.mapEntityToDto(voucher));
        }
        return ResponseEntity.ok().body(voucherDtos);
    }

    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).ADMIN_ROLE_ASSIGN)")
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> postVouchers(@RequestParam("file") MultipartFile file) {
        try {
            List<VoucherEntity> voucherEntities = VoucherCSVHelper.csvToVoucherEntities(file.getInputStream());
            boolean success = voucherService.saveVouchers(voucherEntities);
            if (success) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
