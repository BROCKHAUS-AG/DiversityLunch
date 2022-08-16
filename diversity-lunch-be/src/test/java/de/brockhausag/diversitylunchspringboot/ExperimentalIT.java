package de.brockhausag.diversitylunchspringboot;

import de.brockhausag.diversitylunchspringboot.data.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.email.service.DiversityLunchEMailService;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchMailProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import(DiversityLunchMailProperties.class)
//@ContextConfiguration(classes = {DiversityLunchMailProperties.class})
@RequiredArgsConstructor
public class ExperimentalIT {


    private final ProfileTestdataFactory profileTestdataFactory = new ProfileTestdataFactory();

    @Autowired
    private DiversityLunchEMailService diversityLunchEMailService;

    //    @BeforeEach
//    public void setUp() {
//        this.diversityLunchEMailService = new DiversityLunchEMailService(
//                this.javaMailSender,
//                this.diversityLunchMailProperties);
//    }

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @SneakyThrows
    @Test
    public void shouldSendMailToMailhog() {
        String emailAddress = "test@test.de";
        String subject = "subject";
        String bodyHtml = "subject";
        String bodyPlain = "subject";

        this.diversityLunchEMailService.sendEmail(emailAddress, subject, bodyHtml, bodyPlain);
//        this.mockMvc.perform(
//                get("/mailing/sendTestMail")
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + profileTestdataFactory.getTokenStringFromId("Test"))
//        ).andExpect(status().isUnauthorized());
    }

}
