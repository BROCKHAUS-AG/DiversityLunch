import { Dispatch } from 'redux';
import { authenticatedFetchGet } from '../../utils/fetch.utils';
import { AccountStateAction } from './account-state-action.type';
import { Account } from '../../types/Account';

export const loadAccountErroredAction: AccountStateAction = {
    type: 'ACCOUNT_ERROR',
};

export const startAccountLoadingAction: AccountStateAction = {
    type: 'ACCOUNT_LOADING',
};

export const startAccountPendingAction: AccountStateAction = {
    type: 'ACCOUNT_PENDING',
};

export const loadAccount = async (dispatch: Dispatch) => {
    dispatch(startAccountLoadingAction);

    const result: Response = await authenticatedFetchGet('/api/account');

    if (result.ok) {
        const account: Account = await result.json();

        const accountOkAction: AccountStateAction = {
            type: 'ACCOUNT_LOADING_SUCCEEDED',
            payload: account,
        };

        dispatch(accountOkAction);
    } else {
        dispatch(loadAccountErroredAction);
    }
};
