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

export const UserList: FC = () => {
    const accountsState: AccountsState = useSelector((store: AppStoreState) => store.accounts);
    const profilesState: ProfilesState = useSelector((store: AppStoreState) => store.profiles);
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(getAllAccounts());
        dispatch(getAllProfiles());
    }, []);

    const mapAccountandProfileToUser = () => {
        let userList : User[];
        const accountList : Account[] = accountsState.items;

        const profileList : Profile[] = profilesState.items;

        profileList.forEach((profile) => {
            // @ts-ignore
            const account : Account = accountList.find((v) => v.profileId === profile.id);
            userList.push({ profile, account });
        });

        // @ts-ignore
        return userList;
    };

    return (
        <div className="optionsListContainer">
            <p>Users</p>
            <ul>
                {mapAccountandProfileToUser().map((user) => (
                    <li key={user.account.profileId}>
                        {user.profile.name}
                        {user.account.role}
                    </li>
                ))}
            </ul>
        </div>
    );
};
