import React from 'react';
import { LoadingAnimation } from '../Shared/LoadingAnimation';
import { APP_CONFIG } from '../../config/app-config.const';
import { STORED_PATH_KEY } from '../Shared/SessionStorageRedirection';

const oidcWellKnown = {
  authorization_endpoint: APP_CONFIG.REACT_APP_OIDC_AUTHORIZATION_ENDPOINT,
};

const createOidcUrl = async () => `${oidcWellKnown.authorization_endpoint
}?response_type=${encodeURIComponent('token')}`
        + `&client_id=${encodeURIComponent(APP_CONFIG.REACT_APP_OIDC_CLIENT_ID)}`
        + `&redirect_uri=${encodeURIComponent(window.location.origin)}`
        + `&scope=${encodeURIComponent(APP_CONFIG.REACT_APP_OIDC_SCOPE)}`;

export const Login: React.FC = () => {
  React.useEffect(() => {
    createOidcUrl().then((res) => {
      sessionStorage.setItem(STORED_PATH_KEY, window.location.pathname);
      window.location.href = res;
    });
  }, []);

  return (
    <LoadingAnimation size="block-app" />
  );
};
