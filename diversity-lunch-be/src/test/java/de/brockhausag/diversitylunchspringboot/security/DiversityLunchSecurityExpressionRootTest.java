package de.brockhausag.diversitylunchspringboot.security;

import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.data.AccountTestDataFactory;
import de.brockhausag.diversitylunchspringboot.data.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.data.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.service.MeetingService;
import de.brockhausag.diversitylunchspringboot.profile.model.ProfileEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiversityLunchSecurityExpressionRootTest {

    private static final AccountTestDataFactory accountFactory = new AccountTestDataFactory();

    private static final MeetingTestdataFactory meetingFactory = new MeetingTestdataFactory();

    private static final ProfileTestdataFactory profileFactory = new ProfileTestdataFactory();

    @Mock
    private AccountService accountService;

    @Mock
    private MeetingService meetingService;

    @Mock
    private Authentication authentication;

    @Mock
    private OAuth2AuthenticatedPrincipal oAuth2AuthenticatedPrincipal;

    @InjectMocks
    private DiversityLunchSecurityExpressionRoot diversityLunchSecurityExpressionRoot;

    @Test
    void isProfileOwner_validOwner_returnsTrue() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.of(accountEntity));

        assertTrue(diversityLunchSecurityExpressionRoot.isProfileOwner(accountEntity.getProfile().getId()));
    }

    @Test
    void isProfileOwner_invalidOwner_returnFalse() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.of(accountEntity));

        assertFalse(diversityLunchSecurityExpressionRoot.isProfileOwner(0L));
    }

    @Test
    void isProposalOwner_validOwner_returnTrue() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.of(accountEntity));

        MeetingProposalEntity meetingProposalEntity = meetingFactory.createEntity();
        when(meetingService.getMeetingProposal(meetingProposalEntity.getId())).thenReturn(Optional.of(meetingProposalEntity));

        assertTrue(diversityLunchSecurityExpressionRoot.isProposalOwner(meetingProposalEntity.getId()));
    }

    @Test
    void isProposalOwner_invalidOwner_returnFalse() {
        AccountEntity accountEntity =
                accountFactory.entityBuilder().profile(profileFactory.entityBuilder().id(1).build()).build();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);

        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.of(accountEntity));

        MeetingProposalEntity meetingProposalEntity = meetingFactory.createEntity();
        when(meetingService.getMeetingProposal(meetingProposalEntity.getId())).thenReturn(Optional.of(meetingProposalEntity));

        assertFalse(diversityLunchSecurityExpressionRoot.isProposalOwner(meetingProposalEntity.getId()));
    }

    @Test
    void isProposalOwner_noProfileFound_returnFalse(){
        AccountEntity accountEntity =
                accountFactory.entityBuilder().profile(profileFactory.entityBuilder().id(1).build()).build();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);

        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.empty());

        MeetingProposalEntity meetingProposalEntity = meetingFactory.createEntity();
        when(meetingService.getMeetingProposal(meetingProposalEntity.getId())).thenReturn(Optional.of(meetingProposalEntity));

        assertFalse(diversityLunchSecurityExpressionRoot.isProposalOwner(meetingProposalEntity.getId()));
    }

    @Test
    void isProposalOwner_noMeetingProposalFound_returnFalse(){
        AccountEntity accountEntity =
                accountFactory.entityBuilder().profile(profileFactory.entityBuilder().id(1).build()).build();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);

        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.of(accountEntity));

        MeetingProposalEntity meetingProposalEntity = meetingFactory.createEntity();
        when(meetingService.getMeetingProposal(meetingProposalEntity.getId())).thenReturn(Optional.empty());

        assertFalse(diversityLunchSecurityExpressionRoot.isProposalOwner(meetingProposalEntity.getId()));
    }


    @Test
    void isProfileOwner_withNoExistingAccount_returnFalse() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.empty());

        assertFalse(diversityLunchSecurityExpressionRoot.isProfileOwner(0L));
    }

    @Test
    void isProfileOwner_wrongTokenFormat_returnFalse() {
        when(authentication.getPrincipal()).thenReturn("");
        assertFalse(diversityLunchSecurityExpressionRoot.isProfileOwner(1L));
    }

    @Test
    void isProfileOwner_noClaimProvided_returnFalse() {
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute("unique_name")).thenReturn(null);

        assertFalse(diversityLunchSecurityExpressionRoot.isProfileOwner(1L));
    }

    @Test
    void isAccountOwner_withAccount_returnTrue(){
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.of(accountEntity));

        assertTrue(diversityLunchSecurityExpressionRoot.isAccountOwner(accountEntity.getId()));
    }

    @Test
    void isAccountOwner_withAccount_returnFalse(){
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.of(accountEntity));

        assertFalse(diversityLunchSecurityExpressionRoot.isAccountOwner(accountEntity.getId() +1));
    }

    @Test
    void isAccountOwner_withOutAccount_returnFalse() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.empty());

        assertFalse(diversityLunchSecurityExpressionRoot.isAccountOwner(accountEntity.getId() +1));
    }

    @Test
    void hasAccountRole_withRole_returnTrue() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.of(accountEntity));

        assertTrue(diversityLunchSecurityExpressionRoot.hasAccountRole(AccountRole.STANDARD));
    }

    @Test
    void hasAccountRole_withInvalidRole_returnFalse() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.of(accountEntity));

        assertFalse(diversityLunchSecurityExpressionRoot.hasAccountRole(AccountRole.ADMIN));
    }

    @Test
    void hasAccountPermission_withPermission_returnTrue() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.of(accountEntity));

        assertTrue(diversityLunchSecurityExpressionRoot.hasAccountPermission(AccountPermission.PROFILE_OPTION_READ));
    }

    @Test
    void hasAccountPermission_withInvalidPermission_returnFalse() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.of(accountEntity));

        assertFalse(diversityLunchSecurityExpressionRoot.hasAccountPermission(AccountPermission.PROFILE_OPTION_WRITE));
    }

    @Test
    void hasAccountMultiplePermission_withPermission_returnTrue() {
        AccountEntity accountEntity =
                new AccountEntity(0, mock(ProfileEntity.class), "email", AccountRole.ADMIN);
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getUniqueName());
        when(accountService.getAccount(accountEntity.getUniqueName())).thenReturn(Optional.of(accountEntity));

        assertTrue(diversityLunchSecurityExpressionRoot.hasAccountPermission(AccountPermission.PROFILE_OPTION_READ));
        assertTrue(diversityLunchSecurityExpressionRoot.hasAccountPermission(AccountPermission.PROFILE_OPTION_WRITE));
    }
}
