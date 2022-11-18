package de.brockhausag.diversitylunchspringboot.voucher.controller;

import de.brockhausag.diversitylunchspringboot.dataFactories.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.voucher.exception.ForbiddenVoucherClaim;
import de.brockhausag.diversitylunchspringboot.voucher.mapper.VoucherMapper;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherClaimDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import de.brockhausag.diversitylunchspringboot.voucher.service.VoucherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class VoucherControllerTest {
    @Mock
    private VoucherService voucherService;

    @Mock
    private VoucherMapper voucherMapper;

    @InjectMocks
    private VoucherController voucherController;

    private ProfileEntity meetingProposer;
    private MeetingEntity matchedMeeting;
    private VoucherEntity claimedVoucherProposer;
    private VoucherEntity unclaimedVoucher;
    @BeforeEach
    void initTestData() {
        ProfileTestdataFactory profileTestdataFactory = new ProfileTestdataFactory();
        meetingProposer = profileTestdataFactory.buildEntity(1);
        ProfileEntity meetingPartner = profileTestdataFactory.buildEntity(2);

        MeetingTestdataFactory meetingTestdataFactory = new MeetingTestdataFactory();
        matchedMeeting = meetingTestdataFactory.matchedMeeting(meetingProposer, meetingPartner);

        claimedVoucherProposer = new VoucherEntity(UUID.randomUUID(), meetingProposer, "Claimed-Voucher", matchedMeeting);
        unclaimedVoucher = new VoucherEntity("Unclaimed-Voucher");
    }

    @Test
    void testServiceRespondsVoucherOptionalIfNoNewVoucherIsClaimed_expectHTTPOkAndVoucherCode() {
        when(voucherService.checkForClaimedVoucher(meetingProposer.getId(), matchedMeeting.getId())).thenReturn(true);
        when(voucherService.getVoucherByProfileIdAndMeetingId(meetingProposer.getId(), matchedMeeting.getId())).thenReturn(Optional.of(claimedVoucherProposer));

        ResponseEntity<VoucherClaimDto> resp =  voucherController.claimVoucher(meetingProposer.getId(), matchedMeeting.getId());

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertNotNull(resp.getBody());
        assertEquals(claimedVoucherProposer.getVoucher(), resp.getBody().getVoucherCode());
        assertFalse(resp.getBody().isClaimedNewVoucher());
    }

    @Test
    void testServiceRespondsVoucherOptionalIfANewVoucherIsClaimed_expectHTTPOkAndVoucherCode() throws ForbiddenVoucherClaim {
        when(voucherService.getUnclaimedVoucherForMeeting(meetingProposer.getId(), matchedMeeting.getId())).thenReturn(Optional.of(claimedVoucherProposer));
        when(voucherService.checkForClaimedVoucher(meetingProposer.getId(), matchedMeeting.getId())).thenReturn(false);

        ResponseEntity<VoucherClaimDto> resp =  voucherController.claimVoucher(meetingProposer.getId(), matchedMeeting.getId());

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertNotNull(resp.getBody());
        assertEquals(claimedVoucherProposer.getVoucher(), resp.getBody().getVoucherCode());
        assertTrue(resp.getBody().isClaimedNewVoucher());
    }

    @Test
    void testServiceRespondsWithEmptyVoucherOptional_expectHTTPOkWithVoucherCodeNull() throws ForbiddenVoucherClaim {
        when(voucherService.getUnclaimedVoucherForMeeting(meetingProposer.getId(), matchedMeeting.getId())).thenReturn(Optional.empty());

        ResponseEntity<VoucherClaimDto> resp =  voucherController.claimVoucher(meetingProposer.getId(), matchedMeeting.getId());

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertNotNull(resp.getBody());
        assertNull(resp.getBody().getVoucherCode());
        assertFalse(resp.getBody().isClaimedNewVoucher());
    }

    @Test
    void testServiceThrowsIllegalClaimException_expectHTTPForbidden() throws ForbiddenVoucherClaim {
        when(voucherService.getUnclaimedVoucherForMeeting(meetingProposer.getId(), matchedMeeting.getId())).thenThrow(ForbiddenVoucherClaim.class);

        assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), voucherController.claimVoucher(meetingProposer.getId(), matchedMeeting.getId()));
    }
    @Test
    void testProfileHasNoVouchers_expectEmptyList(){
        when(voucherService.getVoucherByProfileId(meetingProposer.getId())).thenReturn(Collections.emptyList());

        assertEquals(voucherController.getVouchers(meetingProposer.getId()).getBody(),Collections.emptyList());
    }

    @Test
    void testProfileHasVouchers_expectVoucherDtos(){
        List<VoucherEntity> voucherEntities = List.of(claimedVoucherProposer, unclaimedVoucher);
        List<VoucherDto> expected = voucherEntities.stream().map(voucherMapper::entityToDto).toList();
        when(voucherService.getVoucherByProfileId(meetingProposer.getId())).thenReturn(voucherEntities);

        List<VoucherDto> result = voucherController.getVouchers(meetingProposer.getId()).getBody();

        assertNotNull(result);
        assertEquals(expected.size(),result.size());
        assertArrayEquals(expected.toArray(),result.toArray());
    }
}
