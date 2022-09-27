/* eslint-disable camelcase */
import { OidcAuthData } from './oidc-auth-data.type';

export type AccessTokenPayload = {
    acr: string,
    aio: string,
    amr: unknown,
    appid: string,
    appidacr: string,
    aud: string,
    exp: number,
    family_name: string,
    given_name: string,
    iat: number,
    ipaddr: string,
    iss: string,
    name: string,
    nbf: number,
    oid: string,
    onprem_sid: string,
    rh: string,
    scp: string,
    sub: string,
    tid: string,
    unique_name: string,
    upn: string,
    uti: string,
    ver: string,
};

export type AuthenticationStateOk = {
    status: 'OK',
    oidcData: OidcAuthData,
    accessTokenPayload: AccessTokenPayload,
};

export type AuthenticationStateError = {
    status: 'ERROR',
};

export type AuthenticationStatePendingLoading = {
    status: 'PENDING',
};

export type AuthenticationState =
    | AuthenticationStateOk
    | AuthenticationStateError
    | AuthenticationStatePendingLoading;
