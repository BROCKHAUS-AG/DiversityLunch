package de.brockhausag.diversitylunchspringboot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "diversity.settings")
@Data
public class DiversityLunchApplicationSettingsProperties {
    private String baseUrl;
}
