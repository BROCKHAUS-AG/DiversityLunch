import React, { FC, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { AccountsState } from '../../data/accounts/accounts-reducer';
import { AppStoreState } from '../../store/Store';
import { ProfilesState } from '../../data/profiles/profiles-reducer';
import { LoadingAnimation } from '../Shared/LoadingAnimation';

export const UserList: FC = () => {
    const accountsState: AccountsState = useSelector((store: AppStoreState) => store.accounts);
    const profilesState: ProfilesState = useSelector((store: AppStoreState) => store.profiles);
    useEffect(() => {

    }, []);

    if (!accountsState.fetched || !profilesState.fetched) {
        return <LoadingAnimation />;
    }

    return (
        <div className="CSVupload">
            <h3>Upload</h3>
            <p>Zum Uploaden ziehe .csv Files im Standardformat in das Upload Fenster</p>
            <button>Upload</button>
        </div>

    );
};
