package de.brockhausag.diversitylunchspringboot.email.controller;

import de.brockhausag.diversitylunchspringboot.email.service.DiversityLunchEMailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/mailing/")
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
            this.diversityLunchEMailService.sendEmail("lazevedo@brockhaus-ag.de", "Testsubject", body, body);
        } catch (MessagingException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
