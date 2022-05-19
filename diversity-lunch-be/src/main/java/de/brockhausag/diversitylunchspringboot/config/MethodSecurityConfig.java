package de.brockhausag.diversitylunchspringboot.config;

import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.meeting.service.MeetingService;
import de.brockhausag.diversitylunchspringboot.security.DiversityLunchMethodSecurityExpressionHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@AllArgsConstructor
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    private final AccountService accountService;
    private final MeetingService meetingService;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new DiversityLunchMethodSecurityExpressionHandler(accountService, meetingService);
    }
}
