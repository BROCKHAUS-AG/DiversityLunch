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
        String body = "Hallo :)";
        try {
            this.diversityLunchEMailService.sendEmail("test@test.de", "Testsubject", body, body);
        } catch (MessagingException e) {
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
    public ResponseEntity<String> sendTestMailToUser(long id){
        System.out.println("Beginning of sendTestMailToUser");
        String body = "Hallo :)";
        try {
            System.out.println("Beginning of try block of sendTestMailToUser");
            diversityLunchEMailService.sendMailToUser(id, body);
        } catch (MessagingException e) {
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
        System.out.println("Beginning of sendTestMailToUser");
        String body = "Hallo :)";
        try {
            System.out.println("Beginning of try block of sendTestMailToUser");
            diversityLunchEMailService.sendMailToUser(id, body);
        } catch (MessagingException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
