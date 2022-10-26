package de.brockhausag.diversitylunchspringboot.voucher.controller;

import de.brockhausag.diversitylunchspringboot.voucher.mapper.VoucherMapper;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import de.brockhausag.diversitylunchspringboot.voucher.service.VoucherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)

public class VoucherControllerTest {
    @Mock
    private VoucherService voucherService;

    @Mock
    private VoucherMapper voucherMapper;

    @InjectMocks
    private VoucherController voucherController;

    @Test
    void testServiceRespondsVoucherOptional_expectHTTPOkAndVoucherDto(){
        long proposerId = 1L;
        long meetingId = 1L;
        VoucherEntity voucherEntity = new VoucherEntity("code");
        VoucherDto expectedDto = voucherMapper.mapEntityToDto(voucherEntity);
        try {
            when(voucherService.getUnclaimedVoucherForMeeting(proposerId, meetingId)).thenReturn(Optional.of(voucherEntity));
        } catch (VoucherService.IllegalVoucherClaim e) {
            throw new RuntimeException(e);
        }

        ResponseEntity<?> resp =  voucherController.claimVoucher(proposerId,meetingId);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals(expectedDto, resp.getBody());
    }

    @Test
    void testServiceRespondsWithEmptyVoucherOptional_expectHTTPNotFound(){
        long proposerId = 1L;
        long meetingId = 1L;
        try {
            when(voucherService.getUnclaimedVoucherForMeeting(proposerId, meetingId)).thenReturn(Optional.empty());
        } catch (VoucherService.IllegalVoucherClaim e) {
            throw new RuntimeException(e);
        }

        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND), voucherController.claimVoucher(proposerId,meetingId));
    }

    @Test
    void testServiceThrowsIllegalClaimException_expectHTTPForbidden() {
        long proposerId = 1L;
        long meetingId = 1L;
        try {
            when(voucherService.getUnclaimedVoucherForMeeting(proposerId, meetingId)).thenThrow(VoucherService.IllegalVoucherClaim.class);
        } catch (VoucherService.IllegalVoucherClaim e) {
            throw new RuntimeException(e);
        }

        assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), voucherController.claimVoucher(proposerId,meetingId));
    }

}
