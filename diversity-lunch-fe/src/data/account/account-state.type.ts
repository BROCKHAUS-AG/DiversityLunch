import { Account } from '../../model/Account';

export type AccountStateOk = {
    status: 'OK',
    accountData: Account,
};

export type AccountStateError = {
    status: 'ERROR',
};

export type AccountStateLoading = {
    status: 'LOADING',
};

export type AccountStatePending = {
    status: 'PENDING',
};

export type AccountStateOffline = {
    status: 'OFFLINE',
    accountData: Account,
};

export type AccountState =
    | AccountStateOk
    | AccountStateError
    | AccountStateLoading
    | AccountStateOffline
    | AccountStatePending;
