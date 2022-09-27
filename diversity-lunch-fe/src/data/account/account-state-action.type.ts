import { Account } from '../../types/Account';

type AccountStateOkAction = {
  type: 'ACCOUNT_LOADING_SUCCEEDED',
  payload: Account,
};

type AccountStateGeneralAction = {
  type: 'ACCOUNT_ERROR' | 'ACCOUNT_LOADING' | 'ACCOUNT_PENDING',
};

export type AccountStateAction =
  | AccountStateOkAction
  | AccountStateGeneralAction;
