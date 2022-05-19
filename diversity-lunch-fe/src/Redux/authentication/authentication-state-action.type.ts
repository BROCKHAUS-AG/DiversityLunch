import { OidcAuthData } from './oidc-auth-data.type';

type AuthenticationStateOkAction = {
    type: 'AUTH_LOGIN_SUCCEEDED' | 'AUTH_REFRESH_SUCCEEDED',
    payload: OidcAuthData,
};

type AuthenticationStateGeneralAction = {
    type: 'AUTH_RESET' | 'AUTH_LOGIN_FAILED',
};

export type AuthenticationStateAction =
    | AuthenticationStateOkAction
    | AuthenticationStateGeneralAction;
