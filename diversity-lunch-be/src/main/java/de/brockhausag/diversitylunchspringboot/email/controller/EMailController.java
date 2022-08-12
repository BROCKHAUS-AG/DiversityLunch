package de.brockhausag.diversitylunchspringboot.email.controller;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.email.service.DiversityLunchEMailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mailing/")
@Slf4j
@RequiredArgsConstructor
public class EMailController {
    private DiversityLunchEMailService diversityLunchEMailService;
    private AccountService accountService;

    @Operation(summary = "Test Mail wird versendet.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Test Mail wurde versendet."),
    })
    @PostMapping("/sendTestMail")
    public ResponseEntity<String> sendTestMail(){
        //diversityLunchEMailService.sendEmail();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
