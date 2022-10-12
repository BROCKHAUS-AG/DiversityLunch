import { FC, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { AppStoreState } from '../../store/Store';
import { AccountsState } from '../../data/accounts/accounts-reducer';
import { getAllAccounts } from '../../data/accounts/accounts-fetch';
import { ProfilesState } from '../../data/profiles/profiles-reducer';
import { getAllProfiles } from '../../data/profiles/profiles-fetch';

export const UserList: FC = () => {
    const accountsState: AccountsState = useSelector((store: AppStoreState) => store.accounts);
    const profilesState: ProfilesState = useSelector((store: AppStoreState) => store.profiles);
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(getAllAccounts());
        dispatch(getAllProfiles());
    }, []);

    return (
        <div className="UserContainer">
            <p>Users</p>
            <ul>
                {accountsState.items.map((accounts) => <li key={accounts.role}>{accounts.role}</li>)}
                {profilesState.items.map((profiles) => <li key={profiles.id}>{profiles.name}</li>)}
            </ul>
        </div>
    );
};
