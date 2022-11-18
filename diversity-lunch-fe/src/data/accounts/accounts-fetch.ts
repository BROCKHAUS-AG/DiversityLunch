import { Dispatch } from '@reduxjs/toolkit';
import { authenticatedFetchGet, authenticatedFetchPut } from '../../utils/fetch.utils';
import { Account } from '../../types/Account';
import { accountsAction } from './accounts-reducer';
import { FetchCallbacks } from '../generic/FetchCallbacks';
import { handleFetchResponse } from '../handleFetchResponse';

export function getAllAccounts(fetchCallbacks: FetchCallbacks) {
    return async (dispatch: Dispatch) => {
        const onGoingFetch = authenticatedFetchGet('api/account/all');
        const onSuccess = async (response: Response) => {
            try {
                const result : Account[] = await response.json();
                dispatch(accountsAction.update(result));
                dispatch(accountsAction.initFetch(undefined));
            } catch {
                fetchCallbacks.onNetworkError(new Error(`Parse failed: ${response.url} responded with a non-json body`));
            }
        };
        handleFetchResponse(onGoingFetch, onSuccess, fetchCallbacks);
    };
}

export function revokeAdminRole(accountId: number, fetchCallbacks: FetchCallbacks) {
    return async (dispatch: Dispatch) => {
        const onGoingFetch = authenticatedFetchPut(`api/account/revokeAdminRole/${accountId}`, '');
        const onSuccess = async (response: Response) => {
            try {
                const result : Account[] = await response.json();
                dispatch(accountsAction.update(result));
                dispatch(accountsAction.initFetch(undefined));
            } catch {
                fetchCallbacks.onNetworkError(new Error(`Parse failed: ${response.url} responded with a non-json body`));
            }
        };
        handleFetchResponse(onGoingFetch, onSuccess, fetchCallbacks);
    };
}

export function assignAdminRole(accountId: number, fetchCallbacks: FetchCallbacks) {
    return async (dispatch: Dispatch) => {
        const onGoingFetch = authenticatedFetchPut(`api/account/assignAdminRole/${accountId}`, '');
        const onSuccess = async (response: Response) => {
            try {
                const result: Account = await response.json();
                dispatch(accountsAction.update([result]));
            } catch {
                fetchCallbacks.onNetworkError(new Error(`Parse failed: ${response.url} responded with a non-json body`));
            }
        };
        handleFetchResponse(onGoingFetch, onSuccess, fetchCallbacks);
    };
}
