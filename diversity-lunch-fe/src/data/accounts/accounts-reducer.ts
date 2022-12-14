/* eslint no-param-reassign: off */

import {
    CaseReducer,
    createSlice,
    Draft,
    PayloadAction,
    SliceCaseReducers,
} from '@reduxjs/toolkit';
import { Account } from '../../model/Account';

export type AccountsState = {
  items: Account[],
  fetched: boolean,
}

const addFunction: CaseReducer<AccountsState, PayloadAction<Account[]>> = (state, action) => {
    state.items = [...state.items, ...action.payload as Draft<Account>[]];
};

const updateFunction: CaseReducer<AccountsState, PayloadAction<Account[]>> = (state, action) => {
    action.payload.forEach((category) => {
        const idx = state.items.findIndex((e) => e.id === category.id);
        if (idx >= 0) {
            state.items[idx] = category as Draft<Account>;
        } else {
            state.items.push(category as Draft<Account>);
        }
    });
};

const removeFunction: CaseReducer<AccountsState, PayloadAction<number[]>> = (state, action) => {
    state.items = state.items.filter((e) => !action.payload.find((r) => r === e.id));
};

export const accountsSlice = createSlice <AccountsState, SliceCaseReducers<AccountsState>>({
    name: 'accountsReducer',
    initialState: {
        items: [],
        fetched: false,
    },
    reducers: {
        add: addFunction,
        update: updateFunction,
        remove: removeFunction,
        initFetch: (state) => {
            state.fetched = true;
        },
    },
});

export const accountsReducer = accountsSlice.reducer;
export const accountsAction = accountsSlice.actions;
