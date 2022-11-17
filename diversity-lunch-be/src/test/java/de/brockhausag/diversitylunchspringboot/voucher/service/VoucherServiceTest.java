package de.brockhausag.diversitylunchspringboot.voucher.service;

import de.brockhausag.diversitylunchspringboot.dataFactories.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
import de.brockhausag.diversitylunchspringboot.profile.data.ProfileRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.voucher.exception.ForbiddenVoucherClaim;
import de.brockhausag.diversitylunchspringboot.voucher.model.VoucherEntity;
import de.brockhausag.diversitylunchspringboot.voucher.repository.VoucherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VoucherServiceTest {

    @InjectMocks
    private VoucherService voucherService;

    @Mock
    private static VoucherRepository voucherRepository;

    @Mock
    private MeetingRepository meetingRepository;

    @Mock
    private ProfileRepository profileRepository;

    private ProfileEntity meetingProposer;
    private ProfileEntity meetingPartner;

    private ProfileEntity externalProfile;
    private MeetingEntity matchedMeeting;

    private VoucherEntity claimedVoucherProposer;
    private VoucherEntity unclaimedVoucher;

    @BeforeEach
    void initTestData() {
        ProfileTestdataFactory profileTestdataFactory = new ProfileTestdataFactory();
        meetingProposer = profileTestdataFactory.buildEntity(1);
        meetingPartner = profileTestdataFactory.buildEntity(2);
        externalProfile = profileTestdataFactory.buildEntity(3);

        MeetingTestdataFactory meetingTestdataFactory = new MeetingTestdataFactory();
        matchedMeeting = meetingTestdataFactory.matchedMeeting(meetingProposer, meetingPartner);

        claimedVoucherProposer = new VoucherEntity(UUID.randomUUID(), meetingProposer, "Claimed-Voucher", matchedMeeting);
        unclaimedVoucher = new VoucherEntity("Unclaimed-Voucher");
    }

    @Test
    void getVoucherBasedOnMeetingWithoutClaimedVoucher_expectsVoucherInReturn() throws ForbiddenVoucherClaim {
        when(profileRepository.findById(meetingPartner.getId())).thenReturn(Optional.of(meetingPartner));
        when(meetingRepository.findById(matchedMeeting.getId())).thenReturn(Optional.of(matchedMeeting));
        when(voucherRepository.getFirstByProfileIsNullAndMeetingIsNull()).thenReturn(Optional.of(unclaimedVoucher));
        when(voucherRepository.existsByProfileIdAndMeetingId(meetingPartner.getId(), matchedMeeting.getId())).thenReturn(false);

        Optional<VoucherEntity> voucherEntity = voucherService.getUnclaimedVoucherForMeeting(meetingPartner.getId(), matchedMeeting.getId());

        Assertions.assertNotNull(voucherEntity.get());
        Assertions.assertEquals(unclaimedVoucher.getVoucher(), voucherEntity.get().getVoucher());
    }

    @Test
    void meetingExists_butUserWasNotAParticipant_expectsExceptionThrown() {
        when(profileRepository.findById(externalProfile.getId())).thenReturn(Optional.of(meetingPartner));
        when(meetingRepository.findById(matchedMeeting.getId())).thenReturn(Optional.of(matchedMeeting));
        when(voucherRepository.getFirstByProfileIsNullAndMeetingIsNull()).thenReturn(Optional.of(unclaimedVoucher));

        Assertions.assertThrows(ForbiddenVoucherClaim.class, () -> voucherService.getUnclaimedVoucherForMeeting(externalProfile.getId(), matchedMeeting.getId()));
    }

    @Test
    void meetingDoesNotExists_expectsExceptionThrown() {
        when(profileRepository.findById(meetingProposer.getId())).thenReturn(Optional.of(meetingProposer));
        when(meetingRepository.findById(2L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ForbiddenVoucherClaim.class, () -> voucherService.getUnclaimedVoucherForMeeting(meetingProposer.getId(), 2L));
    }

    @Test
    void userIsEligible_butThereAreNoMoreClaimableVouchers_expects_EmptyOptional() throws ForbiddenVoucherClaim {
        when(voucherRepository.existsByProfileIdAndMeetingId(meetingProposer.getId(), matchedMeeting.getId())).thenReturn(false);
        when(voucherRepository.getFirstByProfileIsNullAndMeetingIsNull()).thenReturn(Optional.empty());
        when(profileRepository.findById(meetingProposer.getId())).thenReturn(Optional.of(meetingProposer));
        when(meetingRepository.findById(matchedMeeting.getId())).thenReturn(Optional.of(matchedMeeting));

        Assertions.assertEquals(voucherService.getUnclaimedVoucherForMeeting(meetingProposer.getId(), matchedMeeting.getId()), Optional.empty());
    }

    @Test
    void userAlreadyClaimedVoucherForThisMeeting_expectsAlreadyClaimedVoucher() {
        when(voucherRepository.getVoucherEntityByProfileIdAndMeetingId(meetingProposer.getId(), matchedMeeting.getId())).thenReturn(Optional.of(claimedVoucherProposer));

        VoucherEntity voucherActual = voucherService.getVoucherByProfileIdAndMeetingId(meetingProposer.getId(), matchedMeeting.getId()).get();
        Assertions.assertEquals(claimedVoucherProposer, voucherActual);
    }

    @Test
    void userAlreadyClaimedVoucherForThisMeeting_expectsAlreadyClaimedUser() {
        when(voucherRepository.getVoucherEntityByProfileIdAndMeetingId(meetingProposer.getId(), matchedMeeting.getId())).thenReturn(Optional.of(claimedVoucherProposer));

        VoucherEntity voucherActual = voucherService.getVoucherByProfileIdAndMeetingId(meetingProposer.getId(), matchedMeeting.getId()).get();
        Assertions.assertEquals(claimedVoucherProposer, voucherActual);
    }

    @Test
    void userHasNoVoucher_expectsEmptyOptional() {
        when(voucherRepository.getVoucherEntityByProfileIdAndMeetingId(meetingProposer.getId(), matchedMeeting.getId())).thenReturn(Optional.empty());

        Assertions.assertEquals(Optional.empty(),voucherService.getVoucherByProfileIdAndMeetingId(meetingProposer.getId(), matchedMeeting.getId()));
    }
}
