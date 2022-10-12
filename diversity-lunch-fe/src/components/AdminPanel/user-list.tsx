import { FC, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { AppStoreState } from '../../store/Store';
import { AccountsState } from '../../data/accounts/accounts-reducer';
import { getAllAccounts } from '../../data/accounts/accounts-fetch';

export const UserList: FC = () => {
    const accountsState: AccountsState = useSelector((store: AppStoreState) => store.accounts);
    const profilesState: ProfilesState = useSelector((store: AppStoreState) => store.profiles);
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(getAllAccounts());
    }, []);

    return (<ul>{accountsState.items.map((accounts) => <li>{accounts.id}</li>)}</ul>);
};
