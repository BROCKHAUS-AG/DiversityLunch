/* eslint-disable camelcase */
export type OidcAuthData = {
    access_token: string,
    expires_in: number,
    scope: string,
    session_state: string,
    token_type: 'Bearer' | string,
};
