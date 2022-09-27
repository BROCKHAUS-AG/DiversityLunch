package de.brockhausag.diversitylunchspringboot.account.mapper;

import de.brockhausag.diversitylunchspringboot.account.model.AccountDto;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.dataFactories.AccountTestDataFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountMapperTest {
    private AccountMapper mapper;

    private AccountTestDataFactory accountTestDataFactory;

    @BeforeEach
    void setup(){
        accountTestDataFactory = new AccountTestDataFactory();
        mapper = new AccountMapperImpl();
    }

    @Test
    void test(){
        AccountEntity accountEntity = accountTestDataFactory.buildAccountWithProfile();
        AccountDto expectedDto = accountTestDataFactory.buildAccountDto();

        AccountDto accountDto = mapper.mapEntityToDto(accountEntity);

        Assertions.assertEquals(expectedDto, accountDto);
    }

    @Test
    void testWithoutProfile(){
        AccountEntity accountEntity = accountTestDataFactory.buildAccountWithoutProfile();
        AccountDto expectedDto = accountTestDataFactory.buildAccountDtoWithoutProfile();

        AccountDto accountDto = mapper.mapEntityToDto(accountEntity);

        Assertions.assertEquals(expectedDto, accountDto);
    }

}
