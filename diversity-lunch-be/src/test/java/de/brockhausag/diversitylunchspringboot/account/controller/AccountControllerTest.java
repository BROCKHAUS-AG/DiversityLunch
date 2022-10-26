package de.brockhausag.diversitylunchspringboot.account.controller;

import de.brockhausag.diversitylunchspringboot.account.mapper.AccountMapper;
import de.brockhausag.diversitylunchspringboot.account.model.AccountDto;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import de.brockhausag.diversitylunchspringboot.dataFactories.AccountTestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private OAuth2AuthenticatedPrincipal principal;

    @InjectMocks
    private AccountController accountController;

    private AccountTestDataFactory accountTestDataFactory;

    @BeforeEach
    void tearUp() {
        accountTestDataFactory = new AccountTestDataFactory();
    }

    @Test
    void testGetAccount_withExistentAccount_expectedGetAccountDtoWithStatusOk() {

        AccountDto expectedAccountDto = accountTestDataFactory.buildAccountDto();
        AccountEntity accountEntity = accountTestDataFactory.buildAccountWithoutProfile();

        when(accountService.getOrCreateAccount("irgendwas")).thenReturn(accountEntity);
        when(accountMapper.mapEntityToDto(accountEntity)).thenReturn(expectedAccountDto);

        when(principal.getAttribute("unique_name")).thenReturn("irgendwas");

        ResponseEntity<AccountDto> response = accountController.getAccount(principal);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAccountDto, response.getBody());
    }

    @Test
    void testGetAccount_withInvalidPrincipal_expectBadRequest() {
        when(principal.getAttribute("unique_name")).thenReturn(null);

        ResponseEntity<AccountDto> response = accountController.getAccount(principal);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetAccount_withNoPrincipal_expectInternalServerError() {
        ResponseEntity<AccountDto> response = accountController.getAccount(null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testAssignAdminRole_whenServiceReturnsEntity_expectIsOk() {
        AccountDto expectedAccountDto = accountTestDataFactory.buildAccountDto();
        AccountEntity accountEntity = accountTestDataFactory.buildAccountWithoutProfile();
        try {
            when(accountService.assignAdminRole(any())).thenReturn(Optional.of(accountEntity));
        } catch (Exception e) {
        }
        when(accountMapper.mapEntityToDto(accountEntity)).thenReturn(expectedAccountDto);

        ResponseEntity<?> response = accountController.assignAdminRole(expectedAccountDto.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testAssignAdminRole_whenServiceReturnsEmpty_expectNotFound() {
        try {
            when(accountService.assignAdminRole(any())).thenReturn(Optional.empty());
        } catch (Exception e) {
        }
        long nonExistingID = 1;

        ResponseEntity<?> response = accountController.assignAdminRole(nonExistingID);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAssignAdminRole_whenServiceThrowsException_expectBadRequest() {
        try {
            when(accountService.assignAdminRole(any())).thenThrow(AccountService.IllegalRoleModificationException.class);
        } catch (Exception e) {
        }
        long existingIdWithWrongRole = 1;

        ResponseEntity<?> response = accountController.assignAdminRole(existingIdWithWrongRole);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testRevokeAdminRole_whenServiceReturnsEntity_expectIsOk() {
        AccountDto expectedAccountDto = accountTestDataFactory.buildAccountDto();
        AccountEntity accountEntity = accountTestDataFactory.buildAccountWithoutProfile();
        try {
            when(accountService.revokeAdminRole(any())).thenReturn(Optional.of(accountEntity));
        } catch (Exception e) {
        }
        when(accountMapper.mapEntityToDto(accountEntity)).thenReturn(expectedAccountDto);

        ResponseEntity<?> response = accountController.revokeAdminRole(expectedAccountDto.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testRevokeAdminRole_whenServiceReturnsEmpty_expectNotFound() {
        try {
            when(accountService.revokeAdminRole(any())).thenReturn(Optional.empty());
        } catch (Exception e) {
        }
        long nonExistingID = 1;

        ResponseEntity<?> response = accountController.revokeAdminRole(nonExistingID);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testRevokeAdminRole_whenServiceThrowsException_expectBadRequest() {
        try {
            when(accountService.revokeAdminRole(any())).thenThrow(AccountService.IllegalRoleModificationException.class);
        } catch (Exception e) {
        }
        long existingIdWithWrongRole = 1;

        ResponseEntity<?> response = accountController.revokeAdminRole(existingIdWithWrongRole);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
