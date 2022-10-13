import { FC, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { AppStoreState } from '../../store/Store';
import { AccountsState } from '../../data/accounts/accounts-reducer';
import { getAllAccounts } from '../../data/accounts/accounts-fetch';
import { ProfilesState } from '../../data/profiles/profiles-reducer';
import { getAllProfiles } from '../../data/profiles/profiles-fetch';
import { User } from './userAdministration/User';
import { Account } from '../../types/Account';
import { Profile } from '../../model/Profile';
import {authenticatedFetchPut} from "../../utils/fetch.utils";

export const UserList: FC = () => {
    const accountsState: AccountsState = useSelector((store: AppStoreState) => store.accounts);
    const profilesState: ProfilesState = useSelector((store: AppStoreState) => store.profiles);
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(getAllAccounts());
        dispatch(getAllProfiles());
    }, []);

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
        dispatch(
            authenticatedFetchPut('api/account/assignAdminRole/$(accountId)', null),
        );
    };

    return (
        <div className="optionsListContainer">
            <p>Users</p>
            <ul>
                {mapAccountandProfileToUser().map((user) => (
                    <li key={user.account.profileId}>
                        {user.profile.name}
                        &nbsp;
                        {user.account.role}

                        <button onClick={() => assignAdmin(user.account.profileId)}>Speichern</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};
