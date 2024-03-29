import React from 'react';
import { useSelector } from 'react-redux';
import { AppStoreState } from '../../../data/app-store';
import { ProfileOverview } from './profile-overview';
import { LoadingAnimation } from '../../components/loading-animation/loading-animation';

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
