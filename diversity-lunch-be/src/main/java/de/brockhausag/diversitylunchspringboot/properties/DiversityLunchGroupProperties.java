package de.brockhausag.diversitylunchspringboot.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "diversity.groups")
@Data
public class DiversityLunchGroupProperties {
    @JsonProperty("admin-group-name")
    private String adminGroupName;
}
