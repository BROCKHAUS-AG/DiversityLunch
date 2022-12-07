package de.brockhausag.diversitylunchspringboot.security;

import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.dataFactories.AccountTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.MeetingTestdataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;
import de.brockhausag.diversitylunchspringboot.meeting.service.MeetingService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.of(accountEntity));

        assertTrue(diversityLunchSecurityExpressionRoot.isProfileOwner(accountEntity.getProfile().getId()));
    }

    @Test
    void isProfileOwner_invalidOwner_returnFalse() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.of(accountEntity));

        assertFalse(diversityLunchSecurityExpressionRoot.isProfileOwner(0L));
    }

    @Test
    void isProposalOwner_validOwner_returnTrue() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.of(accountEntity));

        MeetingProposalEntity meetingProposalEntity = meetingFactory.createEntity();
        when(meetingService.getMeetingProposal(meetingProposalEntity.getId())).thenReturn(Optional.of(meetingProposalEntity));

        assertTrue(diversityLunchSecurityExpressionRoot.isProposalOwner(meetingProposalEntity.getId()));
    }

    @Test
    void isProposalOwner_invalidOwner_returnFalse() {
        AccountEntity accountEntity =
                accountFactory.entityBuilder().profile(profileFactory.buildEntity(2)).build();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);

        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.of(accountEntity));

        MeetingProposalEntity meetingProposalEntity = meetingFactory.createEntity();
        when(meetingService.getMeetingProposal(meetingProposalEntity.getId())).thenReturn(Optional.of(meetingProposalEntity));

        assertFalse(diversityLunchSecurityExpressionRoot.isProposalOwner(meetingProposalEntity.getId()));
    }

    @Test
    void isProposalOwner_noProfileFound_returnFalse() {
        AccountEntity accountEntity =
                accountFactory.entityBuilder().profile(profileFactory.buildEntity(1)).build();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);

        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.empty());

        MeetingProposalEntity meetingProposalEntity = meetingFactory.createEntity();
        when(meetingService.getMeetingProposal(meetingProposalEntity.getId())).thenReturn(Optional.of(meetingProposalEntity));

        assertFalse(diversityLunchSecurityExpressionRoot.isProposalOwner(meetingProposalEntity.getId()));
    }

    @Test
    void isProposalOwner_noMeetingProposalFound_returnFalse() {
        AccountEntity accountEntity =
                accountFactory.entityBuilder().profile(profileFactory.buildEntity(1)).build();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);

        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.of(accountEntity));

        MeetingProposalEntity meetingProposalEntity = meetingFactory.createEntity();
        when(meetingService.getMeetingProposal(meetingProposalEntity.getId())).thenReturn(Optional.empty());

        assertFalse(diversityLunchSecurityExpressionRoot.isProposalOwner(meetingProposalEntity.getId()));
    }


    @Test
    void isProfileOwner_withNoExistingAccount_returnFalse() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.empty());

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
        when(oAuth2AuthenticatedPrincipal.getAttribute("oid")).thenReturn(2); //null is not valid just use a wrong number

        assertFalse(diversityLunchSecurityExpressionRoot.isProfileOwner(1L));
    }

    @Test
    void isAccountOwner_withAccount_returnTrue() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.of(accountEntity));

        assertTrue(diversityLunchSecurityExpressionRoot.isAccountOwner(accountEntity.getId()));
    }

    @Test
    void isAccountOwner_withAccount_returnFalse() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.of(accountEntity));

        assertFalse(diversityLunchSecurityExpressionRoot.isAccountOwner(accountEntity.getId() + 1));
    }

    @Test
    void isAccountOwner_withOutAccount_returnFalse() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.empty());

        assertFalse(diversityLunchSecurityExpressionRoot.isAccountOwner(accountEntity.getId() + 1));
    }

    @Test
    void hasAccountRole_withRole_returnTrue() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.of(accountEntity));

        assertTrue(diversityLunchSecurityExpressionRoot.hasAccountRole(AccountRole.STANDARD));
    }

    @Test
    void hasAccountRole_withInvalidRole_returnFalse() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.of(accountEntity));

        assertFalse(diversityLunchSecurityExpressionRoot.hasAccountRole(AccountRole.ADMIN));
    }

    @Test
    void hasAccountPermission_withPermission_returnTrue() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.of(accountEntity));

        assertTrue(diversityLunchSecurityExpressionRoot.hasAccountPermission(AccountPermission.PROFILE_OPTION_READ));
    }

    @Test
    void hasAccountPermission_withInvalidPermission_returnFalse() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.of(accountEntity));

        assertFalse(diversityLunchSecurityExpressionRoot.hasAccountPermission(AccountPermission.PROFILE_OPTION_WRITE));
    }

    @Test
    void hasAccountPermission_withMultiplePermissions_returnTrue() {
        AccountEntity accountEntity =
                new AccountEntity(0L, mock(ProfileEntity.class), "email", AccountRole.ADMIN);
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.of(accountEntity));

        assertTrue(diversityLunchSecurityExpressionRoot.hasAccountPermission(AccountPermission.PROFILE_OPTION_READ));
        assertTrue(diversityLunchSecurityExpressionRoot.hasAccountPermission(AccountPermission.PROFILE_OPTION_WRITE));
    }

    @Test
    void hasAccountPermission_withMultiplePermissions_returnFalse() {
        AccountEntity accountEntity = accountFactory.buildAccountWithProfile();
        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(accountService.getAccount(accountEntity.getOid())).thenReturn(Optional.of(accountEntity));

        assertTrue(diversityLunchSecurityExpressionRoot.hasAccountPermission(AccountPermission.PROFILE_OPTION_READ));
        assertFalse(diversityLunchSecurityExpressionRoot.hasAccountPermission(AccountPermission.PROFILE_OPTION_WRITE));
    }

    @Test
    void testIsMeetingsParticipantAndOwner_proposerIsAllowedToAccess_returnsTrue() {
        ProfileEntity profileProposer = profileFactory.buildEntity(1);
        ProfileEntity profilePartner = profileFactory.buildEntity(2);
        MeetingEntity meetingEntity = MeetingEntity.builder()
                .id(1L)
                .proposer(profileProposer)
                .partner(profilePartner)
                .build();
        AccountEntity accountEntity = AccountEntity.builder()
                .id(1L)
                .oid("1234")
                .profile(profileProposer)
                .build();

        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(meetingService.getMeeting(meetingEntity.getId())).thenReturn(Optional.of(meetingEntity));
        when(accountService.getAccount(any())).thenReturn(Optional.of(accountEntity));

        boolean isParticipantAndOwner = diversityLunchSecurityExpressionRoot
                .isMeetingsParticipantAndOwner(meetingEntity.getId(), profileProposer.getId());

        assertTrue(isParticipantAndOwner);
    }

    @Test
    void testIsMeetingsParticipantAndOwner_partnerIsAllowedToAccess_returnsTrue() {
        ProfileEntity profileProposer = profileFactory.buildEntity(1);
        ProfileEntity profilePartner = profileFactory.buildEntity(2);
        MeetingEntity meetingEntity = MeetingEntity.builder()
                .id(1L)
                .proposer(profileProposer)
                .partner(profilePartner)
                .build();
        AccountEntity accountEntity = AccountEntity.builder()
                .id(1L)
                .oid("1234")
                .profile(profilePartner)
                .build();

        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(meetingService.getMeeting(meetingEntity.getId())).thenReturn(Optional.of(meetingEntity));
        when(accountService.getAccount(any())).thenReturn(Optional.of(accountEntity));

        boolean isParticipantAndOwner = diversityLunchSecurityExpressionRoot
                .isMeetingsParticipantAndOwner(meetingEntity.getId(), profilePartner.getId());

        assertTrue(isParticipantAndOwner);
    }

    @Test
    void testIsMeetingsParticipantAndOwner_wrongMeetingId_returnsFalse() {
        ProfileEntity profileProposer = profileFactory.buildEntity(1);
        AccountEntity accountEntity = AccountEntity.builder()
                .id(1L)
                .oid("1234")
                .profile(profileProposer)
                .build();

        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(meetingService.getMeeting(any())).thenReturn(Optional.empty());
        when(accountService.getAccount(any())).thenReturn(Optional.of(accountEntity));

        boolean isParticipantAndOwner = diversityLunchSecurityExpressionRoot
                .isMeetingsParticipantAndOwner(42L, profileProposer.getId());

        assertFalse(isParticipantAndOwner);
    }

    @Test
    void testIsMeetingsParticipantAndOwner_notPartOfTheMeeting_returnsFalse() {
        ProfileEntity profileProposer = profileFactory.buildEntity(1);
        ProfileEntity profilePartner = profileFactory.buildEntity(2);
        ProfileEntity callerProfile = profileFactory.buildEntity(3);
        MeetingEntity meetingEntity = MeetingEntity.builder()
                .id(1L)
                .proposer(profileProposer)
                .partner(profilePartner)
                .build();
        AccountEntity accountEntity = AccountEntity.builder()
                .id(1L)
                .oid("1234")
                .profile(callerProfile)
                .build();

        when(authentication.getPrincipal()).thenReturn(oAuth2AuthenticatedPrincipal);
        when(oAuth2AuthenticatedPrincipal.getAttribute(any())).thenReturn(accountEntity.getOid());
        when(meetingService.getMeeting(meetingEntity.getId())).thenReturn(Optional.of(meetingEntity));
        when(accountService.getAccount(any())).thenReturn(Optional.of(accountEntity));

        boolean isParticipantAndOwner = diversityLunchSecurityExpressionRoot
                .isMeetingsParticipantAndOwner(meetingEntity.getId(), callerProfile.getId());

        assertFalse(isParticipantAndOwner);
    }
}
