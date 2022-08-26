package de.brockhausag.diversitylunchspringboot.email.service;

import com.nimbusds.jose.util.StandardCharset;
import de.brockhausag.diversitylunchspringboot.meeting.model.Question;
import de.brockhausag.diversitylunchspringboot.profile.model.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.profile.service.ProfileService;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchMailProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiversityLunchEMailService {
    private final JavaMailSender emailSender;
    private final DiversityLunchMailProperties properties;
    private final ProfileService profileService;

    public void sendEmail(String to, String subject, String textHTML, String textPlain) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setFrom(properties.getSender());
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(textPlain, textHTML);

        emailSender.send(message);
    }

    public String createEmailTemplateHTML(String recipient, String partner, Question question) {
        try {
            ClassPathResource resource = new ClassPathResource("email.html");
            String emailText = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()), StandardCharset.UTF_8);
            return String.format(emailText, recipient, partner, question.getCategory().getKind(), question.getKind());
        } catch (Exception e) {
            log.error("Failed to read Resource: email.html", e);
        }
        return "";
    }

    public String createMsTeamsMeetingTemplateHTML(String profileNameOne, String profileNameTwo, String date, String time) {
        try {
            ClassPathResource resource = new ClassPathResource("msTeamsMeeting.html");
            String meetingText = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()), StandardCharset.UTF_8);
            return String.format(meetingText,  profileNameOne, profileNameTwo, date, time);
        } catch (Exception e) {
            log.error("Failed to read Resource: msTeamsMeeting.html", e);
        }
        return "";
    }

    public String createEmailTemplatePlain(String recipient, String partner, Question question) {
        try {
            ClassPathResource resource = new ClassPathResource("email.txt");
            String emailText = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()), StandardCharset.UTF_8);
            return String.format(emailText, recipient, partner, question.getCategory().getKind(), question.getKind());
        } catch (Exception e) {
            log.error("Failed to read Resource: email.txt", e);
        }
        return "";
    }

    public void sendMailToUser(long id, String body) throws MessagingException {
        Optional<ProfileEntity> pe = this.profileService.getProfile(id);
        if (pe.isPresent()) {
            this.sendEmail(pe.get().getEmail(), "Testsubject", body, body);
        }
    }
}
