package de.brockhausag.diversitylunchspringboot.account.repository;

import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
    Optional<AccountEntity> getAccountEntityByUniqueName(String uniqueName);
}
