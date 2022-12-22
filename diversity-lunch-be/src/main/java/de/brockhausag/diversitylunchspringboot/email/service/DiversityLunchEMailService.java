package de.brockhausag.diversitylunchspringboot.email.service;

import com.nimbusds.jose.util.StandardCharset;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.profile.logic.ProfileService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchApplicationSettingsProperties;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchMailProperties;
import de.brockhausag.diversitylunchspringboot.voucher.service.VoucherService;
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
    private final VoucherService voucherService;
    private final DiversityLunchApplicationSettingsProperties settingsProperties;

    public void sendEmail(String to, String subject, String textHTML, String textPlain) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setFrom(properties.getSender());
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(textPlain, textHTML);
        log.info("Finished mail construction, trying to send mail now");
        emailSender.send(message);
        log.info("Sent mail");
    }

    public String createEmailTemplateHTML(ProfileEntity recipient, ProfileEntity otherParticipant, MeetingEntity meeting) {

        int amountOfStoredVouchers = voucherService.getAmountOfStoredVouchers();
        if (amountOfStoredVouchers == 0) {
            ClassPathResource resource = new ClassPathResource("MailTemplates/emailWithoutLink.html");
            return createEmailTemplateNoLink(resource, recipient, otherParticipant, meeting);
        }
        ClassPathResource resource = new ClassPathResource("MailTemplates/emailWithLink.html");
        return createEmailTemplateWithLink(resource, recipient, otherParticipant, meeting);
    }

    public String createEmailTemplatePlain(ProfileEntity recipient, ProfileEntity otherParticipant, MeetingEntity meeting) {
        int amountOfStoredVouchers = voucherService.getAmountOfStoredVouchers();
        if (amountOfStoredVouchers == 0) {
            ClassPathResource resource = new ClassPathResource("MailTemplates/emailWithoutLink.txt");
            return createEmailTemplateNoLink(resource, recipient, otherParticipant, meeting);
        }
        ClassPathResource resource = new ClassPathResource("MailTemplates/emailWithLink.txt");
        return createEmailTemplateWithLink(resource, recipient, otherParticipant, meeting);
    }

    private String createEmailTemplateWithLink(ClassPathResource resource, ProfileEntity recipient, ProfileEntity otherParticipant, MeetingEntity meeting) {
        try {
            String emailText =
                    new String(FileCopyUtils.copyToByteArray(resource.getInputStream()), StandardCharset.UTF_8);
            String claimLink = settingsProperties.getBaseUrl() + "/voucherClaim/" + meeting.getId();
            return String.format(emailText, recipient.getName(), otherParticipant.getName(),
                    meeting.getQuestion().getCategory().getProfileQuestion(), meeting.getQuestion().getQuestionText(), claimLink);
        } catch (Exception e) {
            log.error(String.format("Failed to read Resource: %s", resource.getPath()), e);
        }
        return "";
    }

    private String createEmailTemplateNoLink(ClassPathResource resource, ProfileEntity recipient, ProfileEntity otherParticipant, MeetingEntity meeting) {
        try {
            String emailText =
                    new String(FileCopyUtils.copyToByteArray(resource.getInputStream()), StandardCharset.UTF_8);
            return String.format(emailText, recipient.getName(), otherParticipant.getName(),
                    meeting.getQuestion().getCategory().getProfileQuestion(), meeting.getQuestion().getQuestionText());
        } catch (Exception e) {
            log.error(String.format("Failed to read Resource: %s", resource.getPath()), e);
        }
        return "";
    }


    public String createMsTeamsMeetingTemplateHTML(String profileNameOne, String profileNameTwo, String date, String time) {
        ClassPathResource resource = new ClassPathResource("MailTemplates/msTeamsMeeting.html");

        try {
            String meetingText = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()), StandardCharset.UTF_8);
            return String.format(meetingText, profileNameOne, profileNameTwo, date, time);
        } catch (Exception e) {
            log.error(String.format("Failed to read Resource: %s", resource.getPath()), e);
        }
        return "";
    }


    public void sendMailToUser(Long id, String body) throws MessagingException {
        Optional<ProfileEntity> pe = this.profileService.getProfile(id);
        if (pe.isPresent()) {
            this.sendEmail(pe.get().getEmail(), "Testsubject", body, body);
            log.info("Successfully sent mail to " + pe.get().getEmail());
        } else {
            log.info("Profile with ID:" + id + " not found");
        }
    }
}
