package de.brockhausag.diversitylunchspringboot.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "diversity.ms-teams")
@Data
public class DiversityLunchMsTeamsProperties {
    @JsonProperty("client-secret")
    private String clientSecret;

    @JsonProperty("diversity-lunch-user-id")
    private String diversityLunchUserId;

    @JsonProperty("time-zone")
    private String timeZone;
}
