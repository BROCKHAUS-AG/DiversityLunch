package de.brockhausag.diversitylunchspringboot.account.service;

import com.microsoft.graph.models.Group;
import de.brockhausag.diversitylunchspringboot.account.repository.AccountRepository;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.meeting.service.MicrosoftGraphService;
import de.brockhausag.diversitylunchspringboot.profile.model.ProfileEntity;
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

    public Optional<AccountEntity> getAccount(String uniqueName){

        return repository.getAccountEntityByUniqueName(uniqueName);
    }

    public Optional<AccountEntity> updateAccount(ProfileEntity profileEntity, long id) {
        Optional<AccountEntity> optionalAccount = repository.findById(id);

        return optionalAccount.map(accountEntity -> {
            accountEntity.setRole(createRole());
            accountEntity.setProfile(profileEntity);
            return repository.save(accountEntity);
        });
    }

    public AccountEntity getOrCreateAccount(String uniqueName) {
        Optional<AccountEntity> optionaleAccountEntity = getAccount(uniqueName);
        AccountRole role = createRole();
        return optionaleAccountEntity.orElseGet(
                () -> repository.save(new AccountEntity(0, null, uniqueName, role))
        );
    }

    private AccountRole createRole() {
        return isAccountInAdminGroup() ? AccountRole.ADMIN : AccountRole.STANDARD;
    }

    private boolean isAccountInAdminGroup() {
        Optional<List<Group>> optionalGroups = microsoftGraphService.getGroups();
        return optionalGroups.map(groups -> groups.stream()
                .anyMatch(group -> Objects.equals(group.displayName, "Talents")))
                .orElse(false);
    }
}
