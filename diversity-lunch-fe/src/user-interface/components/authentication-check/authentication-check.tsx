import React from 'react';
import { useIsAuthenticated } from '../../../hooks/authentication/is-authenticated.hook';
import { App } from '../../pages/app';
import { Login } from '../login/login';
import { RedirectHandler } from '../redirect-handler/redirect-handler';
import { OidcAuthData } from '../../../data/authentication/oidc-auth-data.type';

const getQueryParams = (): Record<string, string> => {
    const [, hashedValue] = window.location.href.split('#');
    return (hashedValue || '')
        .split('&')
        .map((query) => query.split('='))
        .map(([key, value]) => ({ [key]: value }))
        .reduce((prev, curr) => ({
            ...prev,
            ...curr,
        }), {});
};

const mapRecordToAuthData = (authDataRecord: Record<keyof OidcAuthData, string>): OidcAuthData => ({
    access_token: authDataRecord.access_token,
    expires_in: +authDataRecord.expires_in,
    scope: authDataRecord.scope,
    session_state: authDataRecord.session_state,
    token_type: authDataRecord.token_type,
});

const RenderLogin: React.FC = () => {
    const authenticationResult = getQueryParams();

    return authenticationResult.access_token
        ? <RedirectHandler authData={mapRecordToAuthData(authenticationResult)} />
        : <Login />;
};

export const AuthenticationCheck: React.FC = () => {
    const isAuthenticated = useIsAuthenticated();

    return isAuthenticated ? <App /> : <RenderLogin />;
};
