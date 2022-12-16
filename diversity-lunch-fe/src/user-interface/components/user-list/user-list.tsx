import React, { FC, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import azureAdminLogo from '../../../resources/icons/azure_modern.svg';
import { AppStoreState } from '../../../data/app-store';
import { AccountsState } from '../../../data/accounts/accounts-reducer';
import { assignAdminRole, getAllAccounts, revokeAdminRole } from '../../../data/accounts/accounts-fetch';
import { ProfilesState } from '../../../data/profiles/profiles-reducer';
import { getAllProfiles } from '../../../data/profiles/profiles-fetch';
import { User } from '../../../model/User';
import { Account } from '../../../model/Account';
import { Profile } from '../../../model/Profile';
import { LoadingAnimation } from '../loading-animation/loading-animation';
import { Role } from '../../../model/Role';

class SortState {
    sortType: SortType;
    sortOrder: SortOrder;

    constructor(type: SortType, order: SortOrder) {
        this.sortType = type;
        this.sortOrder = order;
    }
}

enum SortType {
    BY_NAME,
    BY_ROLE,
}

enum SortOrder {
    ASCENDING,
    DESCENDING
}

export const UserList: FC = () => {
    const accountsState: AccountsState = useSelector((store: AppStoreState) => store.accounts);
    const profilesState: ProfilesState = useSelector((store: AppStoreState) => store.profiles);
    const [searchState, setSearchState] = useState('');
    const [sortState, setSortState] = useState(new SortState(SortType.BY_NAME, SortOrder.ASCENDING));
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

        let sortedList: User[] = [];

        if (sortState.sortType === SortType.BY_NAME) sortedList = sortByName(userList);
        if (sortState.sortType === SortType.BY_ROLE) sortedList = sortByRole(userList);

        if (sortState.sortOrder === SortOrder.DESCENDING) sortedList.reverse();

        return sortedList;
    };

    const sortByName = (userList: User[]): User[] => userList.sort((a, b) => a.profile.name.localeCompare(b.profile.name));

    const sortByRole = (userList: User[]): User[] => {
        const sortedList: User[] = [];
        const azureAdminList: User[] = userList.filter((user) => user.account.role === Role.AZURE_ADMIN);
        const adminList: User[] = userList.filter((user) => user.account.role === Role.ADMIN);
        const standardUserList: User[] = userList.filter((user) => user.account.role === Role.STANDARD);

        sortByName(azureAdminList).forEach((user) => sortedList.push(user));
        sortByName(adminList).forEach((user) => sortedList.push(user));
        sortByName(standardUserList).forEach((user) => sortedList.push(user));

        return sortedList;
    };

    const toggleSortType = (): SortState => {
        let sortType: SortType;
        if (sortState.sortType === SortType.BY_NAME) {
            sortType = SortType.BY_ROLE;
        } else {
            sortType = SortType.BY_NAME;
        }
        return new SortState(sortType, sortState.sortOrder);
    };

    const toggleSortOrder = (): SortState => {
        let newSortOrder;
        if (sortState.sortOrder === SortOrder.ASCENDING) {
            newSortOrder = SortOrder.DESCENDING;
        } else {
            newSortOrder = SortOrder.ASCENDING;
        }
        return new SortState(sortState.sortType, newSortOrder);
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
            <button disabled><img alt="TileIconLink Icon" src={azureAdminLogo} height="20em" /></button>
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
                    <button onClick={() => setSortState(toggleSortType())}>
                        {sortState.sortType === SortType.BY_NAME ? 'Sortiere nach Rollen' : 'Sortiere nach Namen'}
                    </button>
                    <button onClick={() => setSortState(toggleSortOrder())}>
                        {sortState.sortOrder === SortOrder.ASCENDING ? 'Aufsteigend' : 'Absteigend'}
                    </button>
                    <section id="searchContainer">
                        {UserListContainer}
                    </section>
                </details>
            </div>
        </div>
    );
};
