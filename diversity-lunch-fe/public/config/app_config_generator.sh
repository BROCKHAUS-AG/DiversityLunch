#!/bin/sh

file_path=/usr/share/nginx/html/config/app-config.js

echo "window.appConfig = {
  REACT_APP_OIDC_CLIENT_ID: test,
  REACT_APP_OIDC_SCOPE: test ,
  REACT_APP_OIDC_CONFIG_ENDPOINT: test,
  REACT_APP_OIDC_REDIRECT_URI: test,
  REACT_APP_OIDC_AUTHORIZATION_ENDPOINT: test,
};" > $file_path