package de.brockhausag.diversitylunchspringboot.security;

import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
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

    private final AccountService accountService;
    private final MeetingService meetingService;
    @Getter
    @Setter
    private Object filterObject;
    @Getter
    @Setter
    private Object returnObject;
    private Object target;

    public DiversityLunchSecurityExpressionRoot(Authentication authentication, AccountService accountService, MeetingService meetingService) {
        super(authentication);
        this.accountService = accountService;
        this.meetingService = meetingService;
    }

    public static Optional<String> extractOID(OAuth2AuthenticatedPrincipal principal) {
        if (principal == null) {
            log.debug("No principal");
            return Optional.empty();
        }
        Object claimValue = principal.getAttribute("oid");
        if (claimValue == null) {
            log.debug("No Claim with oid");
            return Optional.empty();
        }
        return Optional.of(claimValue.toString());
    }

    @Generated
    public Object getThis() {
        return target;
    }

    @Generated
    public void setThis(Object target) {
        this.target = target;
    }

    public boolean isProfileOwner(Long id) {
        Optional<Long> profileId = getProfileId();
        return profileId.isPresent() && profileId.get().equals(id);
    }

    public boolean isProposalOwner(Long id) {
        Optional<Long> profileId = getProfileId();
        Optional<MeetingProposalEntity> optionalMeeting = meetingService.getMeetingProposal(id);
        Optional<Long> proposalProfileId = optionalMeeting.map(meeting -> meeting.getProposerProfile().getId());
        return profileId.isPresent() && proposalProfileId.isPresent() && profileId.get().equals(proposalProfileId.get());
    }

    public boolean isMeetingsParticipantAndOwner(Long meetingId, Long profileId)
    {
        boolean isParticipantAndAccountOwner = false;
        Optional<Long> profileIdOfAccount = getProfileId();
        Optional<MeetingEntity> meeting = meetingService.getMeeting(meetingId);

        if(profileIdOfAccount.isPresent() && meeting.isPresent())
        {
            isParticipantAndAccountOwner = profileIdOfAccount.get().equals(profileId) &&
                    (meeting.get().getPartner().getId().equals(profileId) ||
                    meeting.get().getProposer().getId().equals(profileId));
        }
        return isParticipantAndAccountOwner;
    }

    public boolean isAccountOwner(Long id) {
        Optional<Long> accountId = getAccountId();
        return accountId.isPresent() && accountId.get().equals(id);
    }

    public boolean hasAccountRole(AccountRole role) {
        Optional<String> oid = getOID();
        if (oid.isEmpty())
            return false;
        Optional<AccountEntity> account = accountService.getAccount(oid.get());
        return account.isPresent() && account.get().getRole().equals(role);
    }

    public boolean hasAccountPermission(AccountPermission permission) {
        Optional<String> oid = getOID();
        if (oid.isEmpty())
            return false;
        Optional<AccountEntity> account = accountService.getAccount(oid.get());
        return account.isPresent() && account.get().getRole().getPermissions().contains(permission);
    }

    private OAuth2AuthenticatedPrincipal getOAuth2AuthenticatedPrincipal() {
        Object principal = this.getAuthentication().getPrincipal();

        if (!(principal instanceof OAuth2AuthenticatedPrincipal oAuth2Authentication)) {
            log.debug("Principal is not a BearerTokenAuthentication");
            return null;
        }
        return oAuth2Authentication;
    }

    public Optional<String> getOID() {
        return DiversityLunchSecurityExpressionRoot.extractOID(getOAuth2AuthenticatedPrincipal());
    }

    private Optional<Long> getAccountId() {
        return getAccount().map(AccountEntity::getId);

    }

    private Optional<Long> getProfileId() {
        return getAccount().map(account -> account.getProfile().getId());
    }

    private Optional<AccountEntity> getAccount() {
        Optional<String> oidOptional = getOID();
        if (oidOptional.isEmpty())
            return Optional.empty();

        return accountService.getAccount(oidOptional.get());
    }
}
