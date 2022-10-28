import { Dispatch } from '@reduxjs/toolkit';
import {authenticatedFetchPost, authenticatedFetchPostCsv} from '../../utils/fetch.utils';
import { globalErrorSlice } from '../error/global-error-slice';
import { Account } from '../../types/Account';
import { accountsAction } from './accounts-reducer';

export function getAllAccounts() {
    return async (dispatch: Dispatch) => {
        try {
            const response = await authenticatedFetchPostCsv('api/voucher/upload');

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