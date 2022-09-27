import React from 'react';
import { Redirect } from 'react-router-dom';
import { useHasProfile } from '../../hooks/profile/has-profile.hook';
import { LoadingAnimation } from '../Shared/LoadingAnimation';
import { GenericErrorPage } from '../Shared/GenericErrorPage';
import { SessionStorageRedirection } from '../Shared/SessionStorageRedirection';

export const HasProfileCheck: React.FC = () => {
    const { profileStatus } = useHasProfile();

    if (profileStatus === 'ERROR') {
        return <GenericErrorPage />;
    }

    if (profileStatus === 'OK') {
        return <SessionStorageRedirection />;
    }

    if (profileStatus === 'NOT_CREATED_YET') {
        return <Redirect to="/questions" />;
    }

    return <LoadingAnimation size="block-app" />;
};
