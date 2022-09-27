import { Account } from '../types/Account';
import { AccountDto } from '../types/dtos/AccountDto';

export const mapAccountToDto = (account: Account): AccountDto => ({
    id: account.id,
    profileId: account.profileId,
    role: account.role,
});

export const mapDtoToAccount = (account: AccountDto): Account => ({
    id: account.id,
    profileId: account.profileId,
    role: account.role,
});
