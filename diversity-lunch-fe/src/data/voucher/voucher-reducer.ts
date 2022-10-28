/* eslint no-param-reassign: off */

import {
    CaseReducer,
    createSlice,
    Draft,
    PayloadAction,
    SliceCaseReducers,
} from '@reduxjs/toolkit';
import { Voucher } from '../../types/Voucher';

export type VoucherState = {
    items: Voucher[],
    fetched: boolean,
}

const addFunction: CaseReducer<VoucherState, PayloadAction<Voucher[]>> = (state, action) => {
    state.items = [...state.items, ...action.payload as Draft<Voucher>[]];
};

const updateFunction: CaseReducer<VoucherState, PayloadAction<Voucher[]>> = (state, action) => {
    action.payload.forEach((category) => {
        const idx = state.items.findIndex((e) => e.id === category.id);
        if (idx >= 0) {
            state.items[idx] = category as Draft<Voucher>;
        } else {
            state.items.push(category as Draft<Voucher>);
        }
    });
};

const removeFunction: CaseReducer<VoucherState, PayloadAction<number[]>> = (state, action) => {
    state.items = state.items.filter((e) => !action.payload.find((r) => r === e.id));
};

export const VoucherSlice = createSlice <VoucherState, SliceCaseReducers<VoucherState>>({
    name: 'VoucherReducer',
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

export const VoucherReducer = VoucherSlice.reducer;
export const VoucherAction = VoucherSlice.actions;
