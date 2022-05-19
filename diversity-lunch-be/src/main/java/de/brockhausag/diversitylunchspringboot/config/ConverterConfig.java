package de.brockhausag.diversitylunchspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class ConverterConfig {
    @Bean
    public Base64.Decoder urlDecoder() {
        return Base64.getUrlDecoder();
    }
}
