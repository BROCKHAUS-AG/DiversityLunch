import React from 'react';
import { Redirect } from 'react-router-dom';
import { useHasProfile } from '../../../hooks/profile/has-profile.hook';
import { LoadingAnimation } from '../loading-animation/loading-animation';
import { GenericErrorPage } from '../../pages/generic-error-page/generic-error-page';
import { SessionStorageRedirection } from '../session-storage-redirection/session-storage-redirection';

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

    return <SessionStorageRedirection />;
    // return <LoadingAnimation size="block-app" />;
};
