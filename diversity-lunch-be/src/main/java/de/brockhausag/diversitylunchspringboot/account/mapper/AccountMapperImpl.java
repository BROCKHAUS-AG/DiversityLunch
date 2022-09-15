package de.brockhausag.diversitylunchspringboot.account.mapper;

import de.brockhausag.diversitylunchspringboot.account.model.AccountDto;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDto mapEntityToDto(AccountEntity entity) {

        long profileId = entity.getProfile() != null ? entity.getProfile().getId() : 0;
        return new AccountDto(
                entity.getId(),
                profileId,
                entity.getRole()
        );
    }
}
