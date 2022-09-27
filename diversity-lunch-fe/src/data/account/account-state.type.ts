import { Account } from '../../types/Account';

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

export type AccountState =
  | AccountStateOk
  | AccountStateError
  | AccountStateLoading
  | AccountStatePending;
