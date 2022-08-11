import { Redirect } from 'react-router-dom';
import React from 'react';

export interface LocalStorageRedirectionProps {
  defaultPath?: string
}

export const SessionStorageRedirection = ({ defaultPath }: LocalStorageRedirectionProps) => {
  const storedPath = sessionStorage.getItem(STORED_PATH_KEY);
  if (!storedPath) {
    return <Redirect to={defaultPath} />;
  }

  /*
   TODO 08.08.2022 TGohlisch: Herausfinden, warum, wenn hier das Item removed wird,
   IMMER auf den defaultPath weitergeleitet wird. Nach dem Auslesen sollte der gespeicherte
   Pfad gelöscht werden, damit bei weiteren Ausleseversuchen der defaultPath benutzt wird.
   */
  // sessionStorage.removeItem(STORED_PATH_KEY);
  return <Redirect to={storedPath} />;
};

SessionStorageRedirection.defaultProps = { defaultPath: '/dashboard' };

export const STORED_PATH_KEY = 'storedPath';
