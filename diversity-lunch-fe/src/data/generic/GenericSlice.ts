/* eslint no-param-reassign: off */

import {
    CaseReducer,
    CaseReducerActions,
    createSlice,
    Draft,
    PayloadAction,
    Reducer,
    SliceCaseReducers,
} from '@reduxjs/toolkit';
import { Identifiable } from './Identifiable';

export type IdentifiableState<T extends Identifiable> = {
    items: T[],
    fetched: boolean,
}

export class GenericSlice<T extends Identifiable> {
    private readonly _genericSlice;

    private readonly _actions : CaseReducerActions<SliceCaseReducers<IdentifiableState<T>>>;

    private readonly _reducer : Reducer<IdentifiableState<T>>;

    constructor(name: string, initialData : T[] = []) {
        this._genericSlice = createSlice<IdentifiableState<T>, SliceCaseReducers<IdentifiableState<T>>>({
            name,
            initialState: { items: initialData, fetched: false },
            reducers: {
                add: (state, action) => this.addFunction(state, action),
                update: (state, action) => this.updateFunction(state, action),
                remove: (state, action) => this.removeFunction(state, action),
                initFetch: (state) => {
                    state.fetched = true;
                },
            },
        });
        this._actions = this._genericSlice.actions;
        this._reducer = this._genericSlice.reducer;
    }

    get actions(): CaseReducerActions<SliceCaseReducers<IdentifiableState<T>>> {
        return this._actions;
    }

    get reducer(): Reducer<IdentifiableState<T>> {
        return this._reducer;
    }

    private addFunction: CaseReducer<IdentifiableState<T>, PayloadAction<T[]>> = (state, action) => {
        state.items = [...state.items, ...action.payload as Draft<T>[]];
    }

    private updateFunction: CaseReducer<IdentifiableState<T>, PayloadAction<T[]>> = (state, action) => {
        action.payload.forEach((category) => {
            const idx = state.items.findIndex((e) => e.id === category.id);
            if (idx >= 0) {
                state.items[idx] = category as Draft<T>;
            } else {
                state.items.push(category as Draft<T>);
            }
        });
    }

    private removeFunction: CaseReducer<IdentifiableState<T>, PayloadAction<number[]>> = (state, action) => {
        state.items.filter((e) => !action.payload.find((r) => r === e.id));
    }
}
