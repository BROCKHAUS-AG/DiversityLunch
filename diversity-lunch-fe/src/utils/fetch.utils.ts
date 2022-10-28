import { APP_STORE } from '../store/Store';

/**
 * Das soll nur demonstrieren wie man einen Request mit Authentifizierung absetzen kann.
 * Fehlerbehandlung und Edge-Cases dürft ihr selbst behandeln :)
 * @param url
 */
export const authenticatedFetchGet = (url: string) => {
    const authState = APP_STORE.getState().authentication;

    let bearerToken = ''; // if not logged in -> no token
    if (authState.status === 'OK') {
        bearerToken = `Bearer ${authState.oidcData.access_token}`;
    }

    return fetch(url, {
        mode: 'cors',
        headers: {
            Authorization: bearerToken,
        },
    });
};

export const authenticatedFetchPost = (url: string, data: any) => {
    const authState = APP_STORE.getState().authentication;

    let bearerToken = '';
    if (authState.status === 'OK') {
        bearerToken = `Bearer ${authState.oidcData.access_token}`;
    }

    return fetch(url, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            Authorization: bearerToken,
            'Content-Type': 'application/json',
        },
    });
};
export const authenticatedFetchPostCsv = (url: string, data: any) => {
    const authState = APP_STORE.getState().authentication;

    let bearerToken = '';
    if (authState.status === 'OK') {
        bearerToken = `Bearer ${authState.oidcData.access_token}`;
    }

    return fetch(url, {
        method: 'POST',
        body: data,
        headers: {
            Authorization: bearerToken,
            'Content-Type': 'multipart/form-data',
        },
    });
};

export const authenticatedFetchPut = (url: string, data: any) => {
    const authState = APP_STORE.getState().authentication;

    let bearerToken = '';
    if (authState.status === 'OK') {
        bearerToken = `Bearer ${authState.oidcData.access_token}`;
    }

    return fetch(url, {
        method: 'PUT',
        body: JSON.stringify(data),
        headers: {
            Authorization: bearerToken,
            'Content-Type': 'application/json',
        },
    });
};

export const authenticatedFetchDelete = (url: string) => {
    const authState = APP_STORE.getState().authentication;

    let bearerToken = '';
    if (authState.status === 'OK') {
        bearerToken = `Bearer ${authState.oidcData.access_token}`;
    }

    return fetch(url, {
        method: 'DELETE',
        headers: {
            Authorization: bearerToken,
        },
    });
};
