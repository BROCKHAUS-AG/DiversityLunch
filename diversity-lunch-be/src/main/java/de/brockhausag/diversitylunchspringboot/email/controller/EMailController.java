package de.brockhausag.diversitylunchspringboot.email.controller;

import de.brockhausag.diversitylunchspringboot.email.service.DiversityLunchEMailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/mailing")
@Slf4j
@RequiredArgsConstructor
public class EMailController {
    private final DiversityLunchEMailService diversityLunchEMailService;
    @Operation(summary = "Test Mail wird versendet.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Test Mail wurde versendet."),
    })
    @PostMapping("/sendTestMail")
    public ResponseEntity<String> sendTestMail(){
        String body = "Datum: " + LocalDateTime.now();
        try {
            this.diversityLunchEMailService.sendEmail("test@test.de", "Testsubject", body, body);
            log.info("requested on /api/mailing/sendTestMail successful");
        } catch (MessagingException e) {
            log.error("requested on /api/mailing/sendTestMail failed");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Test Mail wird an eingeloggten User versendet.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Test Mail wurde versendet."),
    })
    @PostMapping("/sendTestMailToUser")
    @PreAuthorize("isProfileOwner(#id)")
    public ResponseEntity<String> sendTestMailToUser(Long id){
        String body = "Datum: " + LocalDateTime.now();
        try {
            diversityLunchEMailService.sendMailToUser(id, body);
            log.info("requested on /api/mailing/sendTestMailToUser successful");
        } catch (MessagingException e) {
            log.error("requested on /api/mailing/sendTestMailToUser failed");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Test Mail wird an eingeloggten User versendet.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Test Mail wurde versendet."),
    })
    @PostMapping("/sendTestMailToUser/{id}")
    @PreAuthorize("isProfileOwner(#id)")
    public ResponseEntity<String> sendTestMailToUserPathVariable(@PathVariable long id){
        String body = "Datum: " + LocalDateTime.now();
        try {
            diversityLunchEMailService.sendMailToUser(id, body);
            log.info("requested on /api/mailing/sendTestMailToUser/{id} successful");
        } catch (MessagingException e) {
            log.error("requested on /api/mailing/sendTestMailToUser/{id} failed");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "10 Test Mails werden an eingeloggten User versendet.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "10 Test Mails wurden versendet."),
    })
    @PostMapping("/sendTenTestMailsToUser")
    @PreAuthorize("isProfileOwner(#id)")
    public ResponseEntity<String> sendTenTestMailsToUser(Long id){
        try {
            for (int i = 1;  i<= 10; i++) {
                String body = "Datum: " + LocalDateTime.now() + "\n" + "Mailnr.: " + i;
                diversityLunchEMailService.sendMailToUser(id, body);
                log.info("requested on /api/mailing/sendTenTestMailsToUser successful");
            }
        } catch (MessagingException e) {
            log.error("requested on /api/mailing/sendTenTestMailsToUser failed");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
