package de.brockhausag.diversitylunchspringboot.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtBearerTokenAuthenticationConverter;


@TestConfiguration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(new JwtBearerTokenAuthenticationConverter());
        http.authorizeRequests(request -> request.anyRequest().authenticated());
    }


}
