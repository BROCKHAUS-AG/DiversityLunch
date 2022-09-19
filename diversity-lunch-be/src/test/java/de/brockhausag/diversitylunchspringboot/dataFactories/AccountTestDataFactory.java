package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.account.model.AccountDto;
import de.brockhausag.diversitylunchspringboot.account.model.AccountEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;

public class AccountTestDataFactory {

    private static final long id = 8L;
    private static final String uniqueName = "Account";
    private static final ProfileEntity profile = new ProfileTestdataFactory().buildEntity(1);

    public AccountEntity.AccountEntityBuilder entityBuilder() {
        return AccountEntity.builder()
                .id(id)
                .uniqueName(uniqueName)
                .profile(profile);
    }

    public AccountDto.AccountDtoBuilder dtoBuilder() {
        return AccountDto.builder()
                .id(id)
                .profileId(profile.getId());
    }

    public AccountEntity buildAccountWithoutProfile() {
        return entityBuilder().profile(null).build();
    }

    public AccountEntity buildNewAccount() {
        return entityBuilder().id(0).profile(null).build();
    }

    public AccountEntity buildAccountWithProfile() {
        return entityBuilder().build();
    }

    public AccountDto buildAccountDto() {
        return dtoBuilder().build();
    }

    public AccountDto buildAccountDtoWithoutProfile(){return dtoBuilder().profileId(0).build();}


}
