package de.brockhausag.diversitylunchspringboot.security;

import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.service.MeetingService;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Optional;

@Slf4j
public class DiversityLunchSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    @Getter
    @Setter
    private Object filterObject;

    @Getter
    @Setter
    private Object returnObject;

    private Object target;

    private final AccountService accountService;
    private final MeetingService meetingService;

    public DiversityLunchSecurityExpressionRoot(Authentication authentication, AccountService accountService, MeetingService meetingService) {
        super(authentication);
        this.accountService = accountService;
        this.meetingService = meetingService;
    }

    @Generated
    public Object getThis() {
        return target;
    }

    @Generated
    public void setThis(Object target){ this.target = target; }

    public boolean isProfileOwner(long id) {
        System.out.println("Inside isProfileOwner");
        Optional<Long> profileId = getProfileId();
        System.out.println("ProfileId.isPresent(): " + profileId.isPresent());
        System.out.println("Id from Swagger: " + id);
        System.out.println("profileId: " + id + " " + (profileId.isPresent() ? profileId.get() : "not found"));
        System.out.println("Should be true: " + (profileId.isPresent() && profileId.get() == id));
        return profileId.isPresent() && profileId.get() == id;
    }

    public boolean isProposalOwner(long id) {
        Optional<Long> profileId = getProfileId();
        Optional<MeetingProposalEntity> optionalMeeting = meetingService.getMeetingProposal(id);
        Optional<Long> proposalProfileId = optionalMeeting.map(meeting -> meeting.getProposerProfile().getId());
        return profileId.isPresent() && proposalProfileId.isPresent() && profileId.get().equals(proposalProfileId.get());
    }

    public boolean isAccountOwner(long id) {
        Optional<Long> accountId = getAccountId();
        return accountId.isPresent() && accountId.get() == id;
    }

    public boolean hasAccountRole(AccountRole role) {
        String uniqueName = getUniqueName();
        Optional<AccountEntity> account = accountService.getAccount(uniqueName);
        return account.isPresent() && account.get().getRole().equals(role);
    }

    public boolean hasAccountPermission(AccountPermission permission) {
        String uniqueName = getUniqueName();
        Optional<AccountEntity> account = accountService.getAccount(uniqueName);
        return account.isPresent() && account.get().getRole().getPermissions().contains(permission);
    }

    private OAuth2AuthenticatedPrincipal getOAuth2AuthenticatedPrincipal(){
        Object principal = this.getAuthentication().getPrincipal();

        if (!(principal instanceof OAuth2AuthenticatedPrincipal oAuth2Authentication)) {
            log.debug("Principal is not a BearerTokenAuthentication");
            return null;
        }
        return oAuth2Authentication;
    }

    private String getUniqueName(){
        OAuth2AuthenticatedPrincipal oAuth2Authentication = getOAuth2AuthenticatedPrincipal();
        if(oAuth2Authentication == null){
            return null;
        }
        Object claimValue = oAuth2Authentication.getAttribute("unique_name");
        if(claimValue == null){
            log.debug("No Claim with unique_name");
            return null;
        }
        return claimValue.toString();
    }

    private Optional<Long> getAccountId() {
        String uniqueName = getUniqueName();
        Optional<AccountEntity> optionalAccount = accountService.getAccount(uniqueName);
        return optionalAccount.map(AccountEntity::getId);
    }

    private Optional<Long> getProfileId(){
        String uniqueName = getUniqueName();
        Optional<AccountEntity> optionalAccount = accountService.getAccount(uniqueName);
        return optionalAccount.map(account -> account.getProfile().getId());
    }
}
