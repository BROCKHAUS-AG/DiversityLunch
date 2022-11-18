package de.brockhausag.diversitylunchspringboot.email;

import de.brockhausag.diversitylunchspringboot.email.service.DiversityLunchEMailService;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchMailProperties;
import de.brockhausag.diversitylunchspringboot.voucher.service.VoucherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiversityLunchEMailServiceTest {

    @Mock
    MimeMessage msg;

    @Mock
    JavaMailSender mailSender;

    @Mock
    DiversityLunchMailProperties props;

    @InjectMocks
    DiversityLunchEMailService diversityLunchEMailService;

    @Mock
    VoucherService voucherService;

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
    void testEmptyLink() throws MessagingException {

        when(voucherService.getAmountOfStoredVouchers()).thenReturn(0);
        String link = diversityLunchEMailService.getProcessedLink("Gutscheincode");
        assert link.equals("");
    }

    @Test
    void testLink() throws MessagingException {

        when(voucherService.getAmountOfStoredVouchers()).thenReturn(1);
        String link = diversityLunchEMailService.getProcessedLink("Gutscheincode");
        assert link.equals("Gutscheincode");
    }
}
