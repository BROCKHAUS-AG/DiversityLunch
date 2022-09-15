package de.brockhausag.diversitylunchspringboot.account.service;

import de.brockhausag.diversitylunchspringboot.account.repository.AccountRepository;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.security.AccountRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository repository;

    public Optional<AccountEntity> getAccount(String uniqueName){

        return repository.getAccountEntityByUniqueName(uniqueName);
    }

    public Optional<AccountEntity> updateAccount(ProfileEntity profileEntity, long id) {
        Optional<AccountEntity> optionalAccount = repository.findById(id);

        return optionalAccount.map(accountEntity -> {
            accountEntity.setProfile(profileEntity);
            return repository.save(accountEntity);
        });
    }

    public AccountEntity getOrCreateAccount(String uniqueName) {
        Optional<AccountEntity> optionaleAccountEntity = getAccount(uniqueName);
        return optionaleAccountEntity.orElseGet(
                () -> repository.save(new AccountEntity(0, null, uniqueName, AccountRole.STANDARD))
        );
    }
}
