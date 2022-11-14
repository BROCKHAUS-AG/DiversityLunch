package de.brockhausag.diversitylunchspringboot.voucher.service;

import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.data.ProfileRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import de.brockhausag.diversitylunchspringboot.voucher.repository.VoucherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class VoucherServiceTest {

    @InjectMocks
    private VoucherService voucherService;

    @Mock
    private VoucherRepository voucherRepository;

    @Mock
    private MeetingRepository meetingRepository;

    @Mock
    private ProfileRepository profileRepository;
    @Test
    void getVoucherBasedOnMeetingWithoutClaimedVoucher_expectsVoucherInReturn() {
        VoucherEntity expected = new VoucherEntity("code");
        long proposerId = 1L;
        long partnerId = 2L;
        long meetingId = 1L;

        when(voucherRepository.getFirstByProfileIsNullAndMeetingIsNull()).thenReturn(Optional.of(expected));
        when(voucherRepository.existsByProfileIdAndMeetingId(proposerId, meetingId)).thenReturn(false);

        ProfileEntity proposer = new ProfileEntity();
        proposer.setId(proposerId);
        when(profileRepository.findById(proposerId)).thenReturn(Optional.of(proposer));

        ProfileEntity partner = new ProfileEntity();
        partner.setId(partnerId);
        when(profileRepository.findById(partnerId)).thenReturn(Optional.of(partner));

        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(meetingId);
        meeting.setPartner(partner);
        meeting.setProposer(proposer);
        when(meetingRepository.getById(meetingId)).thenReturn(meeting);

        try {
            Optional<VoucherEntity> voucherEntity = voucherService.getUnclaimedVoucherForMeeting(proposerId, meetingId);
            Assertions.assertNotNull(voucherEntity);
            Assertions.assertEquals( expected.getVoucher(), voucherEntity.get().getVoucher());
        }catch (VoucherService.IllegalVoucherClaim e){
            Assertions.fail();
        }
    }
    @Test
    void meetingExists_butUserWasNotAParticipant_expectsExceptionThrown() {
        long proposerId = 1L;
        long partnerId = 2L;
        long meetingId = 1L;

        ProfileEntity proposer = new ProfileEntity();
        proposer.setId(proposerId);
        when(profileRepository.findById(proposerId)).thenReturn(Optional.of(proposer));

        ProfileEntity partner = new ProfileEntity();
        partner.setId(partnerId);

        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(meetingId);
        meeting.setPartner(partner);
        meeting.setProposer(proposer);
        when(meetingRepository.getById(meetingId)).thenReturn(null);

            Assertions.assertThrows(VoucherService.IllegalVoucherClaim.class,() -> voucherService.getUnclaimedVoucherForMeeting(3L, meetingId));

    }

    @Test
    void meetingDoesNotExists_expectsExceptionThrown() {
        long proposerId = 1L;
        long partnerId = 2L;
        long meetingId = 1L;

        ProfileEntity proposer = new ProfileEntity();
        proposer.setId(proposerId);
        when(profileRepository.findById(proposerId)).thenReturn(Optional.of(proposer));

        ProfileEntity partner = new ProfileEntity();
        partner.setId(partnerId);
        when(profileRepository.findById(partnerId)).thenReturn(Optional.of(partner));

        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(meetingId);
        meeting.setPartner(partner);
        meeting.setProposer(proposer);
        when(meetingRepository.getById(meetingId)).thenReturn(meeting);

        Assertions.assertThrows(VoucherService.IllegalVoucherClaim.class,() -> voucherService.getUnclaimedVoucherForMeeting(proposerId, meetingId + 1));

    }

    @Test
    void UserIsEligible_butThereAreNoMoreClaimableVouchers_expects_EmptyReturn() {
        long proposerId = 1L;
        long partnerId = 2L;
        long meetingId = 1L;

        when(voucherRepository.existsByProfileIdAndMeetingId(proposerId, meetingId)).thenReturn(false);
        when(voucherRepository.getFirstByProfileIsNullAndMeetingIsNull()).thenReturn(Optional.empty());

        ProfileEntity proposer = new ProfileEntity();
        proposer.setId(proposerId);
        when(profileRepository.findById(proposerId)).thenReturn(Optional.of(proposer));

        ProfileEntity partner = new ProfileEntity();
        partner.setId(partnerId);
        when(profileRepository.findById(partnerId)).thenReturn(Optional.of(partner));

        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(meetingId);
        meeting.setPartner(partner);
        meeting.setProposer(proposer);
        when(meetingRepository.getById(meetingId)).thenReturn(meeting);
        try{
            Optional<VoucherEntity> voucherEntity = voucherService.getUnclaimedVoucherForMeeting(proposerId,meetingId);

            Assertions.assertTrue(voucherEntity.isEmpty());
        }catch (VoucherService.IllegalVoucherClaim e){
            Assertions.fail();
        }

    }

    @Test
    void UserAlreadyClaimedVoucherForThisMeeting_expectsIllegalClaimException() {
        long proposerId = 1L;
        long partnerId = 2L;
        long meetingId = 1L;

        when(voucherRepository.existsByProfileIdAndMeetingId(proposerId, meetingId)).thenReturn(true);

        ProfileEntity proposer = new ProfileEntity();
        proposer.setId(proposerId);
        when(profileRepository.findById(proposerId)).thenReturn(Optional.of(proposer));

        ProfileEntity partner = new ProfileEntity();
        partner.setId(partnerId);
        when(profileRepository.findById(partnerId)).thenReturn(Optional.of(partner));

        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(meetingId);
        meeting.setPartner(partner);
        meeting.setProposer(proposer);
        when(meetingRepository.getById(meetingId)).thenReturn(meeting);

        Assertions.assertThrows(VoucherService.IllegalVoucherClaim.class, () -> voucherService.getUnclaimedVoucherForMeeting(proposerId,meetingId));
    }
}
