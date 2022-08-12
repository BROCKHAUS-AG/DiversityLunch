import { Redirect } from 'react-router-dom';
import React, { useEffect } from 'react';

export interface LocalStorageRedirectionProps {
  defaultPath?: string
}

export const SessionStorageRedirection = ({ defaultPath }: LocalStorageRedirectionProps) => {
  const storedPath = getStoredPath();
  useEffect(() => clearStoredPath, []);
  return <Redirect to={storedPath || defaultPath} />;
};

SessionStorageRedirection.defaultProps = { defaultPath: '/dashboard' };

export function storePath(path: string) {
  sessionStorage.setItem(STORED_PATH_KEY, path);
}

export function getStoredPath(): string | null {
  return sessionStorage.getItem(STORED_PATH_KEY);
}

export function clearStoredPath() {
  sessionStorage.removeItem(STORED_PATH_KEY);
}

export const STORED_PATH_KEY = 'storedPath';
