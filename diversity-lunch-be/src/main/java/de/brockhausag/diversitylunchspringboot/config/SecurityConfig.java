package de.brockhausag.diversitylunchspringboot.config;

import com.azure.spring.aad.webapi.AADResourceServerWebSecurityConfigurerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends AADResourceServerWebSecurityConfigurerAdapter {
    private static final String[] WHITELIST = {
            "/",
            "/static/**",
            "/config/**",
            "/*.png",
            "/favicon.ico",
            "/robots.txt",
            "/asset-manifest.json",
            "/manifest.json",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .authorizeRequests().antMatchers(WHITELIST).permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}


