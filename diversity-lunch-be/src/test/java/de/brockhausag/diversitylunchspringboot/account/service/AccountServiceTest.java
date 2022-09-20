package de.brockhausag.diversitylunchspringboot.account.service;

import com.microsoft.graph.models.Group;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.repository.AccountRepository;
import de.brockhausag.diversitylunchspringboot.data.AccountTestDataFactory;
import de.brockhausag.diversitylunchspringboot.data.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.meeting.service.MicrosoftGraphService;
import de.brockhausag.diversitylunchspringboot.profile.model.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchGroupProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private List<Group> groupForStandardAccount;

    private List<Group> groupForAzureAdminAccount;

    @BeforeEach
    void tearUp(){
        accountTestDataFactory = new AccountTestDataFactory();

        groupForStandardAccount = new ArrayList<>();
        var standardGroup = new Group();
        // should be a standard account
        standardGroup.displayName = "NOAdminGroup";
        groupForStandardAccount.add(standardGroup);

        groupForAzureAdminAccount = new ArrayList<>();
        var azureAdminGroup = new Group();
        // should be a azure admin account
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
    void testUpdateAccount_withExistentAccount_thenReturnUpdated(){
        AccountEntity account = accountTestDataFactory.buildAccountWithoutProfile();
        ProfileEntity existentProfile = new ProfileTestdataFactory().entity();
        AccountEntity accountWithProfile = accountTestDataFactory.buildAccountWithProfile();

        when(diversityLunchGroupProperties.getAdminGroupName()).thenReturn("AdminGroup");
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(accountWithProfile)).thenReturn(accountWithProfile);
        when(microsoftGraphService.getGroups()).thenReturn(Optional.of(groupForStandardAccount));

        Optional<AccountEntity> updatedAccount = accountService.updateAccount(existentProfile, 1);

        Assertions.assertTrue(updatedAccount.isPresent());
        Assertions.assertNotSame(account, updatedAccount.get());
        Assertions.assertEquals(accountWithProfile, updatedAccount.get());
    }

    @Test
    void testUpdateAccount_withNonExistentAccount_thenReturnEmpty(){
        ProfileEntity existentProfile = new ProfileTestdataFactory().createEntity();

        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<AccountEntity> empty = accountService.updateAccount(existentProfile, 1);

        Assertions.assertTrue(empty.isEmpty());
    }
}
