package de.brockhausag.diversitylunchspringboot.account.service;

import com.microsoft.graph.models.Group;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.repository.AccountRepository;
import de.brockhausag.diversitylunchspringboot.dataFactories.AccountTestDataFactory;
import de.brockhausag.diversitylunchspringboot.dataFactories.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.service.MicrosoftGraphService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchGroupProperties;
import de.brockhausag.diversitylunchspringboot.security.AccountRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private MicrosoftGraphService microsoftGraphService;

    @Mock
    private DiversityLunchGroupProperties diversityLunchGroupProperties;

    @InjectMocks
    private AccountService accountService;

    private AccountTestDataFactory accountTestDataFactory;
    private ProfileTestdataFactory profileFactory;

    private List<Group> groupForStandardAccount;

    private List<Group> groupForAzureAdminAccount;

    @BeforeEach
    void setup() {
        this.profileFactory = new ProfileTestdataFactory();
        this.accountTestDataFactory = new AccountTestDataFactory();

        groupForStandardAccount = new ArrayList<>();
        var standardGroup = new Group();
        // should be a standard account
        standardGroup.displayName = "NOAdminGroup";
        groupForStandardAccount.add(standardGroup);

        groupForAzureAdminAccount = new ArrayList<>();
        var azureAdminGroup = new Group();
        // should be an azure admin account
        azureAdminGroup.displayName = "AdminGroup";
        groupForAzureAdminAccount.add(azureAdminGroup);
    }

    @Test
    void testGetOrCreateAccountCreatesNewAccountIfNoAccountExists() {
        AccountEntity expected = accountTestDataFactory.buildAccountWithoutProfile();

        when(diversityLunchGroupProperties.getAdminGroupName()).thenReturn("AdminGroup");
        when(accountRepository.save(accountTestDataFactory.buildNewAccount())).thenReturn(expected);
        when(accountRepository.getAccountEntityByUniqueName("Account")).thenReturn(Optional.empty());
        when(microsoftGraphService.getGroups()).thenReturn(Optional.of(groupForStandardAccount));

        AccountEntity accountEntity = this.accountService.getOrCreateAccount("Account");

        Assertions.assertNotNull(accountEntity);
        Assertions.assertEquals(expected.getId(), accountEntity.getId());
        Assertions.assertEquals(expected.getUniqueName(), accountEntity.getUniqueName());
        Assertions.assertNull(accountEntity.getProfile());
        Assertions.assertEquals(expected.getRole(), accountEntity.getRole());
    }

    @Test
    void testGetOrCreateAccount_withExistentAccount_thenReturnAccountWithStandardRole() {
        AccountEntity expected = accountTestDataFactory.buildAccountWithoutProfile();
        when(diversityLunchGroupProperties.getAdminGroupName()).thenReturn("AdminGroup");
        when(accountRepository.getAccountEntityByUniqueName("Account")).thenReturn(Optional.of(expected));
        when(microsoftGraphService.getGroups()).thenReturn(Optional.of(groupForStandardAccount));

        AccountEntity accountEntity = this.accountService.getOrCreateAccount("Account");

        Assertions.assertNotNull(accountEntity);
        Assertions.assertEquals(expected.getId(), accountEntity.getId());
        Assertions.assertEquals(expected.getUniqueName(), accountEntity.getUniqueName());
        Assertions.assertEquals(expected.getRole(), accountEntity.getRole());
    }

    @Test
    void testGetOrCreateAccount_withExistentAccount_thenReturnAccountWithAzureAdminRole() {
        AccountEntity expected = accountTestDataFactory.buildAccountWithoutProfile();
        when(diversityLunchGroupProperties.getAdminGroupName()).thenReturn("AdminGroup");
        when(accountRepository.getAccountEntityByUniqueName("Account")).thenReturn(Optional.of(expected));
        when(microsoftGraphService.getGroups()).thenReturn(Optional.of(groupForAzureAdminAccount));

        AccountEntity accountEntity = this.accountService.getOrCreateAccount("Account");

        Assertions.assertNotNull(accountEntity);
        Assertions.assertEquals(expected.getId(), accountEntity.getId());
        Assertions.assertEquals(expected.getUniqueName(), accountEntity.getUniqueName());
        Assertions.assertEquals(expected.getRole(), accountEntity.getRole());
    }

    @Test
    void testUpdateAccount_withExistentAccount_thenReturnUpdated() {
        AccountEntity account = accountTestDataFactory.buildAccountWithoutProfile();
        ProfileEntity existentProfile = profileFactory.buildEntity(1);
        AccountEntity accountWithProfile = accountTestDataFactory.buildAccountWithProfile();

        when(diversityLunchGroupProperties.getAdminGroupName()).thenReturn("AdminGroup");
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(accountWithProfile)).thenReturn(accountWithProfile);
        when(microsoftGraphService.getGroups()).thenReturn(Optional.of(groupForStandardAccount));

        Optional<AccountEntity> updatedAccount = accountService.updateAccount(existentProfile, 1L);

        assertTrue(updatedAccount.isPresent());
        Assertions.assertNotSame(account, updatedAccount.get());
        Assertions.assertEquals(accountWithProfile, updatedAccount.get());
    }

    @Test
    void testUpdateAccount_withNonExistentAccount_thenReturnEmpty() {
        ProfileEntity existentProfile = profileFactory.buildEntity(1);

        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<AccountEntity> empty = accountService.updateAccount(existentProfile, 1L);

        assertTrue(empty.isEmpty());
    }

    @Test
    void testAssignAdminRole_withNonExistingId_thenReturnEmpty() {

        long nonExistingId = 1;
        when(accountRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        try {
            Optional<AccountEntity> empty = accountService.assignAdminRole(nonExistingId);
            assertTrue(empty.isEmpty());
        } catch (AccountService.IllegalRoleModificationException e) {
            fail();
        }
    }

    @Test
    void testAssignAdminRole_withWrongRole_throwsException() {

        long existingIdWithWrongRole = 2L;
        Optional<AccountEntity> input = Optional.of(accountTestDataFactory.buildAccountWithAzureAdminRole());
        when(accountRepository.findById(existingIdWithWrongRole)).thenReturn(input);

        assertThrows(AccountService.IllegalRoleModificationException.class, () -> accountService.assignAdminRole(existingIdWithWrongRole));
    }

    @Test
    void testAssignAdminRole_withExistingIdAndValidRole_thenReturnAccountsWithUpdatedRole() {
        AccountEntity adminAccount = accountTestDataFactory.buildAccountWithAdminRole();
        adminAccount.setId(1L);
        AccountEntity standardAccount = accountTestDataFactory.buildAccountWithStandardRole();
        standardAccount.setId(2L);
        when(accountRepository.findById(adminAccount.getId())).thenReturn(Optional.of(adminAccount));
        when(accountRepository.findById(standardAccount.getId())).thenReturn(Optional.of(standardAccount));
        List<AccountEntity> accountEntityList = Arrays.asList(adminAccount, standardAccount);

        try {
            for (var accountEntity : accountEntityList) {
                Optional<AccountEntity> optionalAccountEntity = accountService.assignAdminRole(accountEntity.getId());

                optionalAccountEntity.ifPresentOrElse(a -> assertEquals(AccountRole.ADMIN, a.getRole()), Assertions::fail);
            }
        } catch (AccountService.IllegalRoleModificationException e) {
            fail();
        }
    }

    @Test
    void testRevokeAdminRole_withNonExistingId_thenReturnEmpty() {

        long nonExistingId = 1;
        when(accountRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        try {
            Optional<AccountEntity> empty = accountService.revokeAdminRole(nonExistingId);
            assertTrue(empty.isEmpty());
        } catch (AccountService.IllegalRoleModificationException e) {
            fail();
        }
    }

    @Test
    void testRevokeAdminRole_withWrongRole_throwsException() {

        long existingIdWithWrongRole = 2L;
        Optional<AccountEntity> input = Optional.of(accountTestDataFactory.buildAccountWithAzureAdminRole());
        when(accountRepository.findById(existingIdWithWrongRole)).thenReturn(input);

        assertThrows(AccountService.IllegalRoleModificationException.class, () -> accountService.revokeAdminRole(existingIdWithWrongRole));
    }

    @Test
    void testRevokeAdminRole_withExistingIdAndValidRole_thenReturnAccountsWithUpdatedRole() {
        AccountEntity adminAccount = accountTestDataFactory.buildAccountWithAdminRole();
        adminAccount.setId(1L);
        AccountEntity standardAccount = accountTestDataFactory.buildAccountWithStandardRole();
        standardAccount.setId(2L);
        when(accountRepository.findById(adminAccount.getId())).thenReturn(Optional.of(adminAccount));
        when(accountRepository.findById(standardAccount.getId())).thenReturn(Optional.of(standardAccount));
        List<AccountEntity> accountEntityList = Arrays.asList(adminAccount, standardAccount);

        try {
            for (var accountEntity : accountEntityList) {
                Optional<AccountEntity> optionalAccountEntity = accountService.revokeAdminRole(accountEntity.getId());

                optionalAccountEntity.ifPresentOrElse(a -> assertEquals(AccountRole.STANDARD, a.getRole()), Assertions::fail);
            }
        } catch (AccountService.IllegalRoleModificationException e) {
            fail();
        }
    }
}
