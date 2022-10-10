package de.brockhausag.diversitylunchspringboot.account.controller;

import de.brockhausag.diversitylunchspringboot.account.mapper.AccountMapper;
import de.brockhausag.diversitylunchspringboot.account.model.AccountDto;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.service.AccountService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

        Object claimValue = principal.getAttribute("unique_name");

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
    public  ResponseEntity<List<AccountDto>> getAccounts(){
        Iterable<AccountEntity> accounts =  service.getAccounts();
        List<AccountDto> accountDtos = StreamSupport.stream(accounts.spliterator(), true)
                .map(mapper::mapEntityToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(accountDtos);
    }
}
