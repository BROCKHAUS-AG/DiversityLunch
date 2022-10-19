#!/bin/sh

FILE_PATH=/usr/share/nginx/html/config/app-config.js

echo "window.appConfig = {
  REACT_APP_OIDC_CLIENT_ID: 'OIDC-Client-Id',
  REACT_APP_OIDC_SCOPE: 'OIDC-Scopes',
  REACT_APP_OIDC_CONFIG_ENDPOINT: 'OIDC-Well-Know-Endpoint',
  REACT_APP_OIDC_REDIRECT_URI: 'OIDC-Redirect-URI',
  REACT_APP_OIDC_AUTHORIZATION_ENDPOINT: 'OIDC-Authorization-Endpoint',
};" > $FILE_PATH

sed -i -- "s%OIDC-Client-Id%$CLIENT_ID%g" $FILE_PATH \
&& sed -i -- "s%OIDC-Scopes%$OIDC_SCOPE%g" $FILE_PATH \
&& sed -i -- "s%OIDC-Well-Know-Endpoint%$OIDC_CONFIG_ENDPOINT%g"  $FILE_PATH \
&& sed -i -- "s%OIDC-Redirect-URI%$OIDC_REDIRECT_URI%g" $FILE_PATH \
&& sed -i -- "s%OIDC-Authorization-Endpoint%$OIDC_AUTHORIZATION_ENDPOINT%g" $FILE_PATH