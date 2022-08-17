package de.brockhausag.diversitylunchspringboot.integrationstests;

import de.brockhausag.diversitylunchspringboot.email.service.DiversityLunchEMailService;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchMailProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.when;

@SpringBootTest
@RequiredArgsConstructor
public class EMailControllerIT {

    private DiversityLunchEMailService diversityLunchEMailService;

    @Autowired
    private JavaMailSender mailSender;

    @Mock
    private DiversityLunchMailProperties diversityLunchMailProperties;


    @BeforeEach
    void setUp() {
        this.diversityLunchEMailService = new DiversityLunchEMailService(this.mailSender, this.diversityLunchMailProperties);
    }

    @SneakyThrows
    @Test
    public void testSendMail_expectedToNotThrowException() {
        String emailAddress = "test@test.de";
        String subject = "subject";
        String bodyHtml = "bodyHtml";
        String bodyPlain = "bodyPlain";

        when(this.diversityLunchMailProperties.getSender()).thenReturn("test@test.de");

        Assertions.assertDoesNotThrow(() ->
                this.diversityLunchEMailService.sendEmail(emailAddress, subject, bodyHtml, bodyPlain)
        );
    }
}
