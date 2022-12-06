package de.brockhausag.diversitylunchspringboot.security;

import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.meeting.service.MeetingService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class DiversityLunchMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
    private final AccountService accountService;
    private final MeetingService meetingService;

    public DiversityLunchMethodSecurityExpressionHandler(AccountService accountService, MeetingService meetingService) {
        this.accountService = accountService;
        this.meetingService = meetingService;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation methodInvocation) {
        DiversityLunchSecurityExpressionRoot root = new DiversityLunchSecurityExpressionRoot(authentication, accountService, meetingService);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(authenticationTrustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}
