import React, { FC, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import azureAdminLogo from '../../resources/icons/azure_modern.svg';
import { AppStoreState } from '../../data/app-store';
import { AccountsState } from '../../data/accounts/accounts-reducer';
import { assignAdminRole, getAllAccounts, revokeAdminRole } from '../../data/accounts/accounts-fetch';
import { ProfilesState } from '../../data/profiles/profiles-reducer';
import { getAllProfiles } from '../../data/profiles/profiles-fetch';
import { User } from './userAdministration/User';
import { Account } from '../../model/Account';
import { Profile } from '../../model/Profile';
import { LoadingAnimation } from '../Shared/LoadingAnimation';
import { Role } from '../../model/Role';

export const UserList: FC = () => {
    const accountsState: AccountsState = useSelector((store: AppStoreState) => store.accounts);
    const profilesState: ProfilesState = useSelector((store: AppStoreState) => store.profiles);
    const [searchState, setSearchState] = useState('');
    const dispatch = useDispatch();
    useEffect(() => {
        // TODO: Handle network and http errors properly tgohlisch 17.11.2022
        dispatch(getAllAccounts({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(getAllProfiles({ onNetworkError: console.error, statusCodeHandlers: {} }));
    }, []);

    if (!accountsState.fetched || !profilesState.fetched) {
        return <LoadingAnimation />;
    }
    const mapAccountAndProfileToUser = () => {
        const userList: User[] = [];
        const accountList: Account[] = accountsState.items;
        const profileList: Profile[] = profilesState.items;

        profileList.forEach((profile) => {
            const account: Account = accountList.filter((v) => v.profileId === profile.id)[0];
            userList.push({
                profile,
                account,
            });
        });

        return userList;
    };

    // TODO: Handle network and http errors properly tgohlisch 17.11.2022
    const assignAdmin = (accountId: number) => {
        dispatch(assignAdminRole(accountId, { onNetworkError: console.error, statusCodeHandlers: {} }));
    };

    // TODO: Handle network and http errors properly tgohlisch 17.11.2022
    const revokeAdmin = (accountId: number) => {
        dispatch(revokeAdminRole(accountId, { onNetworkError: console.error, statusCodeHandlers: {} }));
    };

    const generateAdminListButton = (user: User) => {
        if (user.account.role === Role.ADMIN) {
            return <button onClick={() => revokeAdmin(user.account.id)}>-</button>;
        }
        if (user.account.role === Role.STANDARD) {
            return <button onClick={() => assignAdmin(user.account.id)}>+</button>;
        }
        return (
            <button disabled><img alt="Tile Icon" src={azureAdminLogo} height="20em" /></button>
        );
    };

    const dynamicSearch = () => (mapAccountAndProfileToUser()
        .filter(((user) => user.profile.email.toLowerCase().includes(searchState.toLowerCase()))).map((user) => (
            <section className="usersList" key={user.account.profileId}>
                <span>
                    {user.profile.email}
                </span>
                <span>
                    {user.account.role}
                </span>
                {generateAdminListButton(user)}
            </section>
        )));
    const UserListContainer = dynamicSearch();
    return (
        <div className="optionsListContainer">
            <div>
                <details>
                    <summary className="editListTitle">
                        Userrechte vergeben
                    </summary>
                    <br />
                    <input defaultValue={searchState} onChange={(e) => setSearchState(e.target.value)} placeholder="SUCHEN" />
                    <section id="searchContainer">
                        {UserListContainer}
                    </section>
                </details>
            </div>
        </div>
    );
};
