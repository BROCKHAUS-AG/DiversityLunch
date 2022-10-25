/* eslint no-param-reassign: off */

import {
    CaseReducer,
    createSlice,
    Draft,
    PayloadAction,
    SliceCaseReducers,
} from '@reduxjs/toolkit';
import { Profile } from '../../model/Profile';

export type ProfilesState = {
  items: Profile[],
  fetched: boolean,
}

const addFunction: CaseReducer<ProfilesState, PayloadAction<Profile[]>> = (state, action) => {
    state.items = [...state.items, ...action.payload as Draft<Profile>[]];
};

const updateFunction: CaseReducer<ProfilesState, PayloadAction<Profile[]>> = (state, action) => {
    action.payload.forEach((category) => {
        const idx = state.items.findIndex((e) => e.id === category.id);
        if (idx >= 0) {
            state.items[idx] = category as Draft<Profile>;
        } else {
            state.items.push(category as Draft<Profile>);
        }
    });
};

const removeFunction: CaseReducer<ProfilesState, PayloadAction<number[]>> = (state, action) => {
    state.items = state.items.filter((e) => !action.payload.find((r) => r === e.id));
};

export const accountsSlice = createSlice <ProfilesState, SliceCaseReducers<ProfilesState>>({
    name: 'profilesReducer',
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

export const profilesReducer = accountsSlice.reducer;
export const profilesAction = accountsSlice.actions;
