import { FC, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { AppStoreState } from '../../store/Store';
import { AccountsState } from '../../data/accounts/accounts-reducer';
import { assignAdminRole, getAllAccounts, revokeAdminRole } from '../../data/accounts/accounts-fetch';
import { ProfilesState } from '../../data/profiles/profiles-reducer';
import { getAllProfiles } from '../../data/profiles/profiles-fetch';
import { User } from './userAdministration/User';
import { Account } from '../../types/Account';
import { Profile } from '../../model/Profile';
import { LoadingAnimation } from '../Shared/LoadingAnimation';
import { Role } from '../../model/Role';

export const UserList: FC = () => {
    const accountsState: AccountsState = useSelector((store: AppStoreState) => store.accounts);
    const profilesState: ProfilesState = useSelector((store: AppStoreState) => store.profiles);
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(getAllAccounts());
        dispatch(getAllProfiles());
    }, []);

    if (!accountsState.fetched || !profilesState.fetched) {
        return <LoadingAnimation />;
    }
    const mapAccountandProfileToUser = () => {
        const userList: User[] = [];
        const accountList : Account[] = accountsState.items;
        const profileList : Profile[] = profilesState.items;

        profileList.forEach((profile) => {
            const account : Account = accountList.filter((v) => v.profileId === profile.id)[0];
            userList.push({ profile, account });
        });

        return userList;
    };

    const assignAdmin = (accountId: number) => {
        dispatch(assignAdminRole(accountId));
    };
    const revokeAdmin = (accountId: number) => {
        dispatch(revokeAdminRole(accountId));
    };

    const generateAdminListButton = (user : User) => {
        if (user.account.role === Role.ADMIN) {
            return <button onClick={() => revokeAdmin(user.account.profileId)}>-</button>;
        } if (user.account.role === Role.STANDARD) {
            return <button onClick={() => assignAdmin(user.account.profileId)}>+</button>;
        }
        return <button disabled>  </button>;
    };

    return (
        <div className="optionsListContainer">
            <p className="editListTitle">Users</p>
            <div>
                <section>
                    {mapAccountandProfileToUser().map((user) => (

                        <section className="usersList" key={user.account.profileId}>
                            <span>
                                {user.profile.email}
                            </span>
                            <span>
                                {user.account.role}
                            </span>
                            {generateAdminListButton(user)}
                        </section>
                    ))}
                </section>
            </div>
        </div>
    );
};
