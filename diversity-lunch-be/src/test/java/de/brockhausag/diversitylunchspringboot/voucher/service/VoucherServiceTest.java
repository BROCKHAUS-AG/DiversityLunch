package de.brockhausag.diversitylunchspringboot.voucher.service;

import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.data.ProfileRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.voucher.exception.ForbiddenVoucherClaim;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import de.brockhausag.diversitylunchspringboot.voucher.repository.VoucherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;


import java.util.Optional;
import java.util.UUID;

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

        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(meetingId);
        meeting.setPartner(partner);
        meeting.setProposer(proposer);
        when(meetingRepository.findById(meetingId)).thenReturn(Optional.of(meeting));

        try {
            Optional<VoucherEntity> voucherEntity = voucherService.getUnclaimedVoucherForMeeting(proposerId, meetingId);
            Assertions.assertNotNull(voucherEntity.get());
            Assertions.assertEquals(expected.getVoucher(), voucherEntity.get().getVoucher());
        } catch (ForbiddenVoucherClaim e) {
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


        ProfileEntity partner = new ProfileEntity();
        partner.setId(partnerId);

        when(meetingRepository.findById(meetingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ForbiddenVoucherClaim.class, () -> voucherService.getUnclaimedVoucherForMeeting(3L, meetingId));

    }

    @Test
    void meetingDoesNotExists_expectsExceptionThrown() {
        long proposerId = 1L;
        long meetingId = 1L;

        ProfileEntity proposer = new ProfileEntity();
        proposer.setId(proposerId);
        when(profileRepository.findById(proposerId)).thenReturn(Optional.of(proposer));
        when(meetingRepository.findById(2L)).thenReturn(Optional.empty());
        Assertions.assertThrows(ForbiddenVoucherClaim.class, () -> voucherService.getUnclaimedVoucherForMeeting(proposerId, meetingId + 1));

    }

    @Test
    void userIsEligible_butThereAreNoMoreClaimableVouchers_expects_ExceptionThrown() {
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

        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(meetingId);
        meeting.setPartner(partner);
        meeting.setProposer(proposer);
        when(meetingRepository.findById(meetingId)).thenReturn(Optional.of(meeting));

        Assertions.assertThrows(ForbiddenVoucherClaim.class, () -> voucherService.getUnclaimedVoucherForMeeting(proposerId, meetingId));
    }

    @Test
    void userAlreadyClaimedVoucherForThisMeeting_expectsIllegalClaimException() {
        long proposerId = 1L;
        long partnerId = 2L;
        long meetingId = 1L;

        when(voucherRepository.existsByProfileIdAndMeetingId(proposerId, meetingId)).thenReturn(true);

        ProfileEntity proposer = new ProfileEntity();
        proposer.setId(proposerId);
        when(profileRepository.findById(proposerId)).thenReturn(Optional.of(proposer));

        ProfileEntity partner = new ProfileEntity();
        partner.setId(partnerId);

        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(meetingId);
        meeting.setPartner(partner);
        meeting.setProposer(proposer);
        when(meetingRepository.findById(meetingId)).thenReturn(Optional.of(meeting));

        Assertions.assertThrows(ForbiddenVoucherClaim.class, () -> voucherService.getUnclaimedVoucherForMeeting(proposerId, meetingId));
    }

    @Test
    void userAlreadyClaimedVoucherForThisMeeting_expectsAlreadyClaimedUser() {
        long proposerId = 1L;
        long partnerId = 2L;
        long meetingId = 1L;


        ProfileEntity proposer = new ProfileEntity();
        proposer.setId(proposerId);

        ProfileEntity partner = new ProfileEntity();
        partner.setId(partnerId);

        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(meetingId);
        meeting.setPartner(partner);
        meeting.setProposer(proposer);

        VoucherEntity voucherEntity = new VoucherEntity(UUID.randomUUID(), proposer, "1234", meeting);
        when(voucherRepository.getVoucherEntityByProfileIdAndMeetingId(proposerId, meetingId)).thenReturn(Optional.of(voucherEntity));

        VoucherEntity voucherActual = voucherService.getVoucherByProfileIdAndMeetingId(proposerId, meetingId).get();
        Assertions.assertEquals(voucherEntity, voucherActual);

    }

    @Test
    void userHasNoVoucher_expectsEmptyOptional() {
        long proposerId = 1L;
        long partnerId = 2L;
        long meetingId = 1L;


        ProfileEntity proposer = new ProfileEntity();
        proposer.setId(proposerId);

        ProfileEntity partner = new ProfileEntity();
        partner.setId(partnerId);



        when(voucherRepository.getVoucherEntityByProfileIdAndMeetingId(proposerId, meetingId)).thenReturn(Optional.empty());

        Assertions.assertEquals(Optional.empty(),voucherService.getVoucherByProfileIdAndMeetingId(proposerId, meetingId));

    }
}
