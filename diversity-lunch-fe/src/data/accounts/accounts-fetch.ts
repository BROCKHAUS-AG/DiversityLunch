import { Dispatch } from '@reduxjs/toolkit';
import { authenticatedFetchGet, authenticatedFetchPut } from '../../utils/fetch.utils';
import { globalErrorSlice } from '../error/global-error-slice';
import { Account } from '../../types/Account';
import { accountsAction } from './accounts-reducer';

export function getAllAccounts() {
    return async (dispatch: Dispatch) => {
        try {
            const response = await authenticatedFetchGet('api/account/revokeAdmin/all');

            if (!response.ok) {
                dispatch(globalErrorSlice.httpError({ statusCode: response.status }));
            } else {
                const result : Account[] = await response.json();

                dispatch(accountsAction.update(result));
                dispatch(accountsAction.initFetch(undefined));
            }
        } catch (error) {
            dispatch(globalErrorSlice.error(undefined));
        }
    };
}

export function revokeAdminRole(accountId: number) {
    return async (dispatch: Dispatch) => {
        try {
            const response = await authenticatedFetchPut(`api/account/revokeAdmin/${accountId}`, '');

            if (!response.ok) {
                dispatch(globalErrorSlice.httpError({ statusCode: response.status }));
            } else {
                const result : Account = await response.json();
                dispatch(accountsAction.update([result]));
            }
        } catch (error) {
            dispatch(globalErrorSlice.error(undefined));
        }
    };
}

// TODO: Assign Admin
