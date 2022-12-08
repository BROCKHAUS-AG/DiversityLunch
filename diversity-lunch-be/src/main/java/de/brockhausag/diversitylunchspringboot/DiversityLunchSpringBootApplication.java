package de.brockhausag.diversitylunchspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class DiversityLunchSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiversityLunchSpringBootApplication.class, args);
    }

}
