package de.brockhausag.diversitylunchspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@Profile("!IssuerLocationTest")
public class SymmetricJwtDecoderConfig {
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withSecretKey(new SecretKeySpec("SomeSecretWeDontCheckItAnywayToS".getBytes(), "HmacSHA256"))
                .build();
    }
}
