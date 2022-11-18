package de.brockhausag.diversitylunchspringboot.voucher.controller;

import de.brockhausag.diversitylunchspringboot.voucher.exception.ForbiddenVoucherClaim;
import de.brockhausag.diversitylunchspringboot.voucher.mapper.VoucherMapper;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherClaimDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.AdminVoucherDto;
import de.brockhausag.diversitylunchspringboot.voucher.exception.IllegalVoucherClaim;
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
import org.springframework.web.reactive.result.view.RedirectView;

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
    public ResponseEntity<Integer> getAmountOfVouchersStored(@RequestParam(value = "claimed", required = false) Boolean isVoucherClaimed) {
        int amountOfVouchers;
        if (isVoucherClaimed == null) {
            amountOfVouchers = voucherService.getAmountOfStoredVouchers();
        } else if (isVoucherClaimed) {
            amountOfVouchers = voucherService.getAmountOfClaimedVouchers();
        } else {
            amountOfVouchers = voucherService.getAmountOfUnclaimedVouchers();
        }

        return ResponseEntity.ok(amountOfVouchers);
    }

    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).ADMIN_ROLE_ASSIGN)")
    @GetMapping("/admin/all")
    public ResponseEntity<List<AdminVoucherDto>> getVouchersWithEmail() {
        List<VoucherEntity> voucherEntities = voucherService.getAllVouchersStored();
        List<AdminVoucherDto> voucherDtos = new ArrayList<>();
        for (VoucherEntity voucher : voucherEntities) {
            voucherDtos.add(voucherMapper.mapEntityToAdminVoucherDto(voucher));
        }
        return ResponseEntity.ok().body(voucherDtos);
    }

    @PreAuthorize("isProfileOwner(#profileId)")
    @PutMapping("/claim/{profileId}/{meetingId}")
    public ResponseEntity<VoucherClaimDto> claimVoucher(@PathVariable Long profileId, @PathVariable Long meetingId) {
        try {
            Optional<VoucherEntity> voucherEntity;
            VoucherClaimDto voucherClaimDto = new VoucherClaimDto();
            if (voucherService.checkForClaimedVoucher(profileId, meetingId)) {
                voucherEntity = voucherService.getVoucherByProfileIdAndMeetingId(profileId, meetingId);
                voucherClaimDto.setClaimedNewVoucher(false);
            } else {
                voucherEntity = voucherService.getUnclaimedVoucherForMeeting(profileId, meetingId);
                voucherClaimDto.setClaimedNewVoucher(true);
            }

            if (voucherEntity.isEmpty()) {
                voucherClaimDto.setClaimedNewVoucher(false);
            } else {
                voucherClaimDto.setVoucherCode(voucherEntity.get().getVoucher());
            }

            return ResponseEntity.ok().body(voucherClaimDto);
        } catch (ForbiddenVoucherClaim e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("isProfileOwner(#profileId)")
    @GetMapping("/all/{profileId}")
    public ResponseEntity<List<VoucherDto>> getVouchers(@PathVariable Long profileId) {
        List<VoucherEntity> voucherEntities = voucherService.getVoucherByProfileId(profileId);
        List<VoucherDto> voucherDtos = new ArrayList<>();
        for (VoucherEntity voucher : voucherEntities) {
            voucherDtos.add(voucherMapper.entityToDto(voucher));
        }
        return ResponseEntity.ok().body(voucherDtos);
    }

    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).ADMIN_ROLE_ASSIGN)")
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> postVouchers(@RequestParam("file") MultipartFile file) {
        try {
            List<VoucherEntity> voucherEntities = VoucherCSVHelper.csvToVoucherEntities(file.getInputStream());
            voucherService.saveVouchers(voucherEntities);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
