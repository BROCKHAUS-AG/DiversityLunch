package de.brockhausag.diversitylunchspringboot.voucher.service;

import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.MeetingRepository;
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


import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class VoucherServiceTest {

    @InjectMocks
    private VoucherService voucherService;

    @Mock
    private VoucherRepository voucherRepository;

    @Mock
    private MeetingRepository meetingRepository;

    //unclaimed is frei und mensch hat meeting -> kriegt einen voucher
    //unclaimed keine vouchers mehr -> Mensch kann keine vouchers bekommen
    //Mensch versucht zwei mal voucher für gleihces Meeting zu erhalten -> böse kein Voucher
    //Mensch versucht gutschein anzufordern ist aber gar kein teilnehmer -> böse kein Voucher

    //alle vouhcer anzeigen lassen -> du hast keine
    // alle vouhcer anzeigen lassen -> du warst fleißig


    @Test
    void testGetVoucherBasedOnMeetingWithoutClaimedVoucher() {
        VoucherEntity expected = new VoucherEntity("code");

        when(voucherRepository.getFirstByProfileIsNullAndMeetingIsNull()).thenReturn(Optional.of(expected));
        when(voucherRepository.existsByProfileAndMeeting(1L, 1L)).thenReturn(false);

        ProfileEntity proposer = new ProfileEntity();
        proposer.setId(1L);

        ProfileEntity partner = new ProfileEntity();
        partner.setId(2L);

        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(1L);
        meeting.setPartner(partner);
        meeting.setProposer(proposer);
        when(meetingRepository.existsById(1L)).thenReturn(meeting);

        try {
            Optional<VoucherEntity> voucherEntity = voucherService.getUnclaimedVoucherForMeeting(1L, 1L);
            Assertions.assertNotNull(voucherEntity);
            Assertions.assertEquals( expected.getVoucherCode(), voucherEntity.get().getVoucherCode());
        }catch (VoucherService.IllegalVoucherClaim e){
            Assertions.fail();
        }
    }
    @Test
    void input_ProfileWithoutMeetingParticipation_expectsExceptionThrown() {
        VoucherEntity expected = new VoucherEntity("code");
        when(voucherRepository.getFirstByProfileIsNullAndMeetingIsNull()).thenReturn(Optional.of(expected));

        ProfileEntity proposer = new ProfileEntity();
        proposer.setId(1L);

        ProfileEntity partner = new ProfileEntity();
        partner.setId(2L);

        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(1);
        meeting.setPartner(partner);
        meeting.setProposer(proposer);
        when(meetingRepository.getById(1L)).thenReturn(meeting);

            Assertions.assertThrows(VoucherService.IllegalVoucherClaim.class,() -> voucherService.getUnclaimedVoucherForMeeting(3L, 1L));

    }
    @Test
    void input_ProfileEligibleForVoucher_expects_EmptyReturn() {
        when(voucherRepository.existsByProfileAndMeeting(1L, 1L)).thenReturn(false);
        when(voucherRepository.getFirstByProfileIsNullAndMeetingIsNull()).thenReturn(Optional.empty());

        ProfileEntity proposer = new ProfileEntity();
        proposer.setId(1L);

        ProfileEntity partner = new ProfileEntity();
        partner.setId(2L);

        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(1L);
        meeting.setPartner(partner);
        meeting.setProposer(proposer);
        when(meetingRepository.getById(1L)).thenReturn(meeting);
        try{
            Optional<VoucherEntity> voucherEntity = voucherService.getUnclaimedVoucherForMeeting(1L,1L);

            Assertions.assertTrue(voucherEntity.isEmpty());
        }catch (VoucherService.IllegalVoucherClaim e){
            Assertions.fail();
        }

    }

    @Test
    void testGetVoucherBasedOnMeetingAlreadyClaimed() {
        when(voucherRepository.existsByProfileAndMeeting(1L, 1L)).thenReturn(true);

        VoucherEntity claimableVoucher = new VoucherEntity("code");
        when(voucherRepository.getFirstByProfileIsNullAndMeetingIsNull()).thenReturn(Optional.of(claimableVoucher));

        ProfileEntity proposer = new ProfileEntity();
        proposer.setId(1L);

        ProfileEntity partner = new ProfileEntity();
        partner.setId(2L);

        MeetingEntity meeting = new MeetingEntity();
        meeting.setId(1);
        meeting.setPartner(partner);
        meeting.setProposer(proposer);
        when(meetingRepository.getById(1L)).thenReturn(meeting);

        Assertions.assertThrows(VoucherService.IllegalVoucherClaim.class, () -> voucherService.getUnclaimedVoucherForMeeting(1,1));


    }

}
