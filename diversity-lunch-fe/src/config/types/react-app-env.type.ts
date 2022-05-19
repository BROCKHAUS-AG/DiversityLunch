export type ReactAppEnv = {
    REACT_APP_OIDC_CLIENT_ID: string,
    REACT_APP_OIDC_SCOPE: string,
    REACT_APP_OIDC_CONFIG_ENDPOINT: string,
    REACT_APP_OIDC_REDIRECT_URI: string,
    REACT_APP_OIDC_AUTHORIZATION_ENDPOINT: string,
    PUBLIC_URL: string,
    NODE_ENV: 'production' | string,
}
