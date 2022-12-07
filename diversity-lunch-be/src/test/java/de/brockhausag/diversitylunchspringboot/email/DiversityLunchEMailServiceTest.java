package de.brockhausag.diversitylunchspringboot.email;

import de.brockhausag.diversitylunchspringboot.dataFactories.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.email.service.DiversityLunchEMailService;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchApplicationSettingsProperties;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchMailProperties;
import de.brockhausag.diversitylunchspringboot.voucher.service.VoucherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiversityLunchEMailServiceTest {

    private final ProfileTestdataFactory profileTestdataFactory = new ProfileTestdataFactory();
    private final MeetingTestdataFactory meetingTestdataFactory = new MeetingTestdataFactory();
    @Mock
    MimeMessage msg;
    @Mock
    JavaMailSender mailSender;
    @Mock
    DiversityLunchMailProperties props;
    @Mock
    // Is used even if IntelliJ says not. The Test-Suit will fail, if the Mock gets removed.
    DiversityLunchApplicationSettingsProperties settingsProperties;
    @InjectMocks
    DiversityLunchEMailService diversityLunchEMailService;
    @Mock
    VoucherService voucherService;
    private ProfileEntity proposer;
    private ProfileEntity partner;

    private MeetingEntity meeting;

    @Value("${diversity.url.baseUrl}")
    private String BASE_URL;

    @BeforeEach
    void Setup() {
        proposer = profileTestdataFactory.buildEntity(1);
        partner = profileTestdataFactory.buildEntity(2);
        meeting = meetingTestdataFactory.matchedMeeting(proposer, partner);
    }

    @Test
    void testSendEmail() throws MessagingException {
        when(mailSender.createMimeMessage()).thenReturn(msg);
        when(props.getSender()).thenReturn("test@test.de");
        diversityLunchEMailService.sendEmail("test@diversity-lunch.de",
                "Test-Termin",
                "Hier könnte Deine Frage stehen", "Test");

        verify(mailSender, times(1)).send(msg);
    }

    @Test
    void testCreateEMailTemplateHTML_expectsEmailWithClaimLink() {
        when(voucherService.getAmountOfStoredVouchers()).thenReturn(1);

        String claimLinkAnchorTag = String.format("<a href=%s>Hier klicken</a>", BASE_URL + "/voucherClaim/" + meeting.getId());

        String email = diversityLunchEMailService.createEmailTemplateHTML(proposer, partner, meeting);

        assertTrue(email.contains(claimLinkAnchorTag));
    }

    @Test
    void testCreateEMailTemplateHTML_expectsEmailWithoutClaimLink() {
        when(voucherService.getAmountOfStoredVouchers()).thenReturn(0);

        String claimLinkAnchorTag = String.format("<a href=%s>Hier klicken</a>", BASE_URL + "/voucherClaim/" + meeting.getId());

        String email = diversityLunchEMailService.createEmailTemplateHTML(proposer, partner, meeting);

        assertFalse(email.contains(claimLinkAnchorTag));
    }

    @Test
    void testCreateEMailTemplatePlain_expectsEmailWithClaimLink() {
        when(voucherService.getAmountOfStoredVouchers()).thenReturn(1);

        String claimLinkAnchorTag = String.format("Hier kannst du deinen Lieferando Gutschein anfordern: %s", BASE_URL + "/voucherClaim/" + meeting.getId());

        String email = diversityLunchEMailService.createEmailTemplatePlain(proposer, partner, meeting);

        assertTrue(email.contains(claimLinkAnchorTag));
    }

    @Test
    void testCreateEMailTemplatePlain_expectsEmailWithoutClaimLink() {
        when(voucherService.getAmountOfStoredVouchers()).thenReturn(0);

        String claimLinkAnchorTag = String.format("Hier kannst du deinen Lieferando Gutschein anfordern: %s", BASE_URL + "/voucherClaim/" + meeting.getId());

        String email = diversityLunchEMailService.createEmailTemplatePlain(proposer, partner, meeting);

        assertFalse(email.contains(claimLinkAnchorTag));
    }

    @Test
    void testCreateEmailTemplateHTML_expectsProposerNameForRecipient() {
        when(voucherService.getAmountOfStoredVouchers()).thenReturn(0);

        String name = String.format("Hallo <b>%s</b>", proposer.getName());
        String email = diversityLunchEMailService.createEmailTemplateHTML(proposer, partner, meeting);

        assertTrue(email.contains(name));

    }
}
