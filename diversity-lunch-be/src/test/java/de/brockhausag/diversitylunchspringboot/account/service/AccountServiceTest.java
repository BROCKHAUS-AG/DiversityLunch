package de.brockhausag.diversitylunchspringboot.account.service;

import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.repository.AccountRepository;
import de.brockhausag.diversitylunchspringboot.data.AccountTestDataFactory;
import de.brockhausag.diversitylunchspringboot.data.ProfileTestdataFactory;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private AccountTestDataFactory accountTestDataFactory;

    @BeforeEach
    void tearUp(){
        accountTestDataFactory = new AccountTestDataFactory();
    }

    @Test
    void testGetOrCreateAccountCreatesNewAccountIfNoAccountExists() {
        AccountEntity expected = accountTestDataFactory.buildAccountWithoutProfile();

        when(accountRepository.save(accountTestDataFactory.buildNewAccount())).thenReturn(expected);
        when(accountRepository.getAccountEntityByUniqueName("Account")).thenReturn(Optional.empty());

        AccountEntity accountEntity = this.accountService.getOrCreateAccount("Account");

        Assertions.assertNotNull(accountEntity);
        Assertions.assertEquals(expected.getId(), accountEntity.getId());
        Assertions.assertEquals(expected.getUniqueName(), accountEntity.getUniqueName());
        Assertions.assertNull(accountEntity.getProfile());
    }

    @Test
    void testGetOrCreateAccount_withExistentAccount_thenReturnAccount() {
        AccountEntity expected = accountTestDataFactory.buildAccountWithoutProfile();
        when(accountRepository.getAccountEntityByUniqueName("Account")).thenReturn(Optional.of(expected));
        AccountEntity accountEntity = this.accountService.getOrCreateAccount("Account");

        Assertions.assertNotNull(accountEntity);
        Assertions.assertEquals(expected.getId(), accountEntity.getId());
        Assertions.assertEquals(expected.getUniqueName(), accountEntity.getUniqueName());
    }

    @Test
    void testUpdateAccount_withExistentAccount_thenReturnUpdated(){
        AccountEntity account = accountTestDataFactory.buildAccountWithoutProfile();
        ProfileEntity existentProfile = new ProfileTestdataFactory().entity();
        AccountEntity accountWithProfile = accountTestDataFactory.buildAccountWithProfile();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(accountWithProfile)).thenReturn(accountWithProfile);

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
