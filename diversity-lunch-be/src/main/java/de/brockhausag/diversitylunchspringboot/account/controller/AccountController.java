package de.brockhausag.diversitylunchspringboot.account.controller;

import de.brockhausag.diversitylunchspringboot.account.mapper.AccountMapper;
import de.brockhausag.diversitylunchspringboot.account.model.AccountDto;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/account")
@Slf4j
@RequiredArgsConstructor
public class AccountController {
    private final AccountMapper mapper;
    private final AccountService service;

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<AccountDto> getAccount(@Parameter(hidden = true) @AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {

        if (principal == null) {
            log.error("principal null");
            return ResponseEntity.internalServerError().body(null);
        }

        Object claimValue = principal.getAttribute("oid");

        if (claimValue == null) {
            log.error("claimValue/body is null");
            return ResponseEntity.badRequest().body(null);
        }

        AccountEntity accountEntity = service.getOrCreateAccount(claimValue.toString());

        AccountDto accountDto = mapper.mapEntityToDto(accountEntity);

        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).ACCOUNT_READ)")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        Iterable<AccountEntity> accounts = service.getAccounts();
        List<AccountDto> accountDtos = StreamSupport.stream(accounts.spliterator(), true)
                .map(mapper::mapEntityToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(accountDtos);
    }

    @PutMapping("/assignAdminRole/{id}")
    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).ADMIN_ROLE_ASSIGN)")
    public ResponseEntity<?> assignAdminRole(@PathVariable Long id) {
        Optional<AccountEntity> optionalAccount;
        try {
            optionalAccount = service.assignAdminRole(id);
        } catch (AccountService.IllegalRoleModificationException e) {
            log.warn(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (optionalAccount.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AccountDto accountDto = mapper.mapEntityToDto(optionalAccount.get());
        return ResponseEntity.ok().body(accountDto);
    }
    @PutMapping("/revokeAdminRole/{id}")
    @PreAuthorize("hasAccountPermission(T(de.brockhausag.diversitylunchspringboot.security.AccountPermission).ADMIN_ROLE_ASSIGN)")
    public ResponseEntity<?> revokeAdminRole(@PathVariable Long id) {
        Optional<AccountEntity> optionalAccount;
        try {
            optionalAccount = service.revokeAdminRole(id);
        } catch (AccountService.IllegalRoleModificationException e) {
            log.warn(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        if (optionalAccount.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AccountDto accountDto = mapper.mapEntityToDto(optionalAccount.get());
        return ResponseEntity.ok().body(accountDto);
    }
}
