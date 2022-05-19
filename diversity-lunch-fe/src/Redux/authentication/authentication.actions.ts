import { AuthenticationStateAction } from './authentication-state-action.type';
import { OidcAuthData } from './oidc-auth-data.type';

export const loginAfterRedirectAction = (authData: OidcAuthData): AuthenticationStateAction => ({
  type: 'AUTH_LOGIN_SUCCEEDED',
  payload: authData,
});
