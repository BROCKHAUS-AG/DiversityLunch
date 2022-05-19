import { Reducer } from 'redux';
import {
  AccessTokenPayload,
  AuthenticationState,
  AuthenticationStatePendingLoading,
} from './authentication-state.type';
import { AuthenticationStateAction } from './authentication-state-action.type';

const initialState: AuthenticationStatePendingLoading = {
  status: 'PENDING',
};

export const authenticationReducer: Reducer<AuthenticationState, AuthenticationStateAction> = (
  // eslint-disable-next-line default-param-last
  state: AuthenticationState = initialState,
  action: AuthenticationStateAction,
): AuthenticationState => {
  switch (action.type) {
    case 'AUTH_RESET': {
      return initialState;
    }
    case 'AUTH_LOGIN_FAILED': {
      return {
        status: 'ERROR',
      };
    }
    case 'AUTH_LOGIN_SUCCEEDED':
    case 'AUTH_REFRESH_SUCCEEDED': {
      return {
        status: 'OK',
        oidcData: action.payload,
        accessTokenPayload: parseAccessToken(action.payload.access_token),
      };
    }
    default: {
      return state;
    }
  }
};

const parseAccessToken = (jwt: string): AccessTokenPayload => {
  const [, payload] = jwt.split('.');
  const decodedPayload = decodeURIComponent(escape(atob(payload)));
  return JSON.parse(decodedPayload) as AccessTokenPayload;
};
