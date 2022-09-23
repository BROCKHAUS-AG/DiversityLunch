import React from 'react';
import { useSelector } from 'react-redux';
import { AppStoreState } from '../../store/Store';
import { ProfileOverview } from './ProfileOverview';
import { LoadingAnimation } from '../Shared/LoadingAnimation';

export const ProfileOverviewLoader: React.FC = () => {
    const profileState = useSelector((store: AppStoreState) => store.profile);

    return (
        <>
            {
                (profileState.status === 'OK' || profileState.status === 'UPDATING')
        && <ProfileOverview />
            }
            {
                profileState.status === 'UPDATING' && <LoadingAnimation size="block-app" />
            }
        </>
    );
};
