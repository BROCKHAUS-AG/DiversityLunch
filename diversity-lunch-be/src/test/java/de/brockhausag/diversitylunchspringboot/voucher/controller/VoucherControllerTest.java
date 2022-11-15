package de.brockhausag.diversitylunchspringboot.voucher.controller;

import de.brockhausag.diversitylunchspringboot.account.mapper.AccountMapperImpl;
import de.brockhausag.diversitylunchspringboot.dataFactories.AccountTestDataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.voucher.mapper.VoucherMapper;
import de.brockhausag.diversitylunchspringboot.voucher.mapper.VoucherMapperImpl;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherDto;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import de.brockhausag.diversitylunchspringboot.voucher.service.VoucherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

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
    void testServiceRespondsVoucherOptional_expectHTTPOkAndVoucherCode(){
        long proposerId = 1L;
        long meetingId = 1L;
        UUID uuid = UUID.randomUUID();

        ProfileEntity profile = new ProfileEntity();
        profile.setId(proposerId);
        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(meetingId);

        VoucherEntity voucherEntity = new VoucherEntity(uuid,profile,"code",meeting);
        VoucherDto expectedDto = new VoucherDto(uuid,"code",1L,1L);



        try {
            when(voucherService.getUnclaimedVoucherForMeeting(proposerId, meetingId)).thenReturn(Optional.of(voucherEntity));
        } catch (VoucherService.IllegalVoucherClaim e) {
           Assertions.fail();
        }

        ResponseEntity<?> resp =  voucherController.claimVoucher(proposerId,meetingId);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals(expectedDto.getVoucherCode(), resp.getBody());
    }

    @Test
    void testServiceRespondsWithEmptyVoucherOptional_expectHTTPNotFound(){
        long proposerId = 1L;
        long meetingId = 1L;
        try {
            when(voucherService.getUnclaimedVoucherForMeeting(proposerId, meetingId)).thenReturn(Optional.empty());
        } catch (VoucherService.IllegalVoucherClaim e) {
            Assertions.fail();
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
            Assertions.fail();
        }

        assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), voucherController.claimVoucher(proposerId,meetingId));
    }
    @Test
    void testPorfileHasNoVouchers_expectEmptyList(){
        long profile_id = 1L;
        when(voucherService.getVoucherByProfileId(profile_id)).thenReturn(Collections.emptyList());
        assertEquals(voucherController.getVouchers(profile_id).getBody(),Collections.emptyList());
    }

    @Test
    void testProfileHasVouchers_expectVoucherDtos(){

        long profile_id = 1L;
        long meeting_id = 1L;
        UUID uuid = UUID.randomUUID();

        ProfileEntity profile = new ProfileEntity();
        profile.setId(profile_id);
        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(meeting_id);

        VoucherEntity voucher1 = new VoucherEntity(uuid,profile,"code1",meeting);
        VoucherEntity voucher2 = new VoucherEntity(uuid,profile,"code2",meeting);
        VoucherEntity[] voucherArray = {voucher1,voucher2};
        List<VoucherEntity> voucherEntities = new ArrayList<VoucherEntity>(Arrays.asList(voucherArray));

        VoucherDto voucherDto1 = new VoucherDto(uuid,"code1",profile_id,meeting_id);
        VoucherDto voucherDto2 = new VoucherDto(uuid,"code2",profile_id,meeting_id);
        List<VoucherDto> expected = List.of(new VoucherDto[]{voucherDto1, voucherDto2});

        when(voucherMapper.mapEntityToDto(voucher1)).thenReturn(voucherDto1);
        when(voucherMapper.mapEntityToDto(voucher2)).thenReturn(voucherDto2);

        when(voucherService.getVoucherByProfileId(profile_id)).thenReturn(voucherEntities);

        List<VoucherDto> result = voucherController.getVouchers(profile_id).getBody();

        assertEquals(expected.size(),result.size());
        assertArrayEquals(expected.toArray(),result.toArray());
    }

}
