package de.brockhausag.diversitylunchspringboot.account.mapper;

import de.brockhausag.diversitylunchspringboot.account.model.AccountDto;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;

public interface AccountMapper {
    AccountDto mapEntityToDto(AccountEntity entity);
}
