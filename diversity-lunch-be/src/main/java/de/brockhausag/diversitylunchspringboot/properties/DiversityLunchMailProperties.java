package de.brockhausag.diversitylunchspringboot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "diversity.mail")
@Data
public class DiversityLunchMailProperties {
    private String sender;
}
