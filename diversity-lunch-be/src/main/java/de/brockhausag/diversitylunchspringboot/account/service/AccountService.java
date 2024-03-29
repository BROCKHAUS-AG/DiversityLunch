package de.brockhausag.diversitylunchspringboot.account.service;

import com.microsoft.graph.models.Group;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.account.repository.AccountRepository;
import de.brockhausag.diversitylunchspringboot.meeting.service.MicrosoftGraphService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.properties.DiversityLunchGroupProperties;
import de.brockhausag.diversitylunchspringboot.security.AccountRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository repository;

    private final MicrosoftGraphService microsoftGraphService;

    private final DiversityLunchGroupProperties diversityLunchGroupProperties;

    public Optional<AccountEntity> getAccount(String oid) {

        return repository.getAccountEntityByOid(oid);
    }

    public Optional<AccountEntity> updateAccount(ProfileEntity profileEntity, Long id) {
        Optional<AccountEntity> optionalAccount = repository.findById(id);

        return optionalAccount.map(accountEntity -> {
            accountEntity.setRole(determineRole());
            accountEntity.setProfile(profileEntity);
            return repository.save(accountEntity);
        });
    }

    public AccountEntity getOrCreateAccount(String oid) {
        Optional<AccountEntity> accountEntity = getAccount(oid);
        AccountRole role = determineRole();
        return accountEntity.orElseGet(
                () -> repository.save(new AccountEntity(0L, null, oid, role))
        );
    }

    public Optional<AccountEntity> assignAdminRole(Long id) throws IllegalRoleModificationException {
        Optional<AccountEntity> optionalAccount = repository.findById(id);
        if (optionalAccount.isEmpty()) {
            return optionalAccount;
        }
        AccountEntity account = optionalAccount.get();
        if (account.getRole() == AccountRole.AZURE_ADMIN) {
            throw new IllegalRoleModificationException("Tried to revoke Azure Admin Role by reassigning to Admin Role");
        }

        account.setRole(AccountRole.ADMIN);
        repository.save(account);
        return optionalAccount;
    }

    public Optional<AccountEntity> revokeAdminRole(Long id) throws IllegalRoleModificationException {
        Optional<AccountEntity> optionalAccount = repository.findById(id);
        if (optionalAccount.isEmpty()) {
            return optionalAccount;
        }
        AccountEntity account = optionalAccount.get();
        if (account.getRole() == AccountRole.AZURE_ADMIN) {
            throw new IllegalRoleModificationException("Tried to revoke Azure Admin Role");
        }

        account.setRole(determineRole());
        repository.save(account);
        return optionalAccount;
    }

    private AccountRole determineRole() {
        return isAccountInAdminGroup() ? AccountRole.AZURE_ADMIN : AccountRole.STANDARD;
    }

    private boolean isAccountInAdminGroup() {
        Optional<List<Group>> optionalGroups = microsoftGraphService.getGroups();
        return optionalGroups.map(groups -> groups.stream()
                        .anyMatch(group -> Objects.equals(group.displayName, diversityLunchGroupProperties.getAdminGroupName())))
                .orElse(false);
    }

    public Iterable<AccountEntity> getAccounts() {
        return repository.findAll();
    }

    public static class IllegalRoleModificationException extends Exception {

        public IllegalRoleModificationException(String s) {
            super(s);
        }
    }
}

