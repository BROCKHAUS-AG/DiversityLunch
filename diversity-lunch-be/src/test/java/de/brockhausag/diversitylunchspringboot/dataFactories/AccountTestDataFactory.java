package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.account.model.AccountDto;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import de.brockhausag.diversitylunchspringboot.security.AccountRole;

public class AccountTestDataFactory {

    private static final Long id = 8L;
    private static final String uniqueName = "Account";
    private static final ProfileEntity profile = new ProfileTestdataFactory().buildEntity(1);
    private static final AccountRole role = AccountRole.STANDARD;

    public AccountEntity.AccountEntityBuilder entityBuilder() {
        return AccountEntity.builder()
                .id(id)
                .uniqueName(uniqueName)
                .profile(profile)
                .role(role);
    }

    public AccountDto.AccountDtoBuilder dtoBuilder() {
        return AccountDto.builder()
                .id(id)
                .profileId(profile.getId())
                .role(role);
    }

    public AccountEntity buildAccountWithAzureAdminRole() {
        return entityBuilder().role(AccountRole.AZURE_ADMIN).build();
    }
    public AccountEntity buildAccountWithAdminRole() {
        return entityBuilder().role(AccountRole.ADMIN).build();
    }
    public AccountEntity buildAccountWithStandardRole() {
        return entityBuilder().role(AccountRole.STANDARD).build();
    }
    public AccountEntity buildAccountWithoutProfile() {
        return entityBuilder().profile(null).build();
    }

    public AccountEntity buildNewAccount() {
        return entityBuilder().id(0L).profile(null).build();
    }

    public AccountEntity buildAccountWithProfile() {
        return entityBuilder().build();
    }

    public AccountDto buildAccountDto() {
        return dtoBuilder().build();
    }

    public AccountDto buildAccountDtoWithoutProfile(){return dtoBuilder().profileId(0L).build();}


}
