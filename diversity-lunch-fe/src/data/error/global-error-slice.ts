/* eslint no-unused-vars: off */
/* eslint no-param-reassign: off */

import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { HttpError } from '../../types/http-error';

type HttpErrorAction = {
    payload: {
        statusCode : number,
        message? : string,
    }
}

export class GlobalErrorSlice {
    protected readonly _slice;
    protected readonly _actions;
    protected readonly _reducer;

    constructor(private readonly _name: string = 'globalError') {
        this._slice = createSlice({
            name: _name,
            initialState: null as null | Error,
            reducers: {
                // eslint-disable-next-line @typescript-eslint/no-unused-vars
                ok: (state) => { state = null; },
                error: (state, action) => { state = action.payload; },
                httpError: (state, action:HttpErrorAction) => { state = new HttpError(action.payload.statusCode, action.payload.message); },
            },
        });

        this._actions = this._slice.actions;
        this._reducer = this._slice.reducer;
    }

    get slice() {
        return this._slice;
    }

    get ok() {
        return this._actions.ok;
    }

    get error() {
        return this._actions.error;
    }

    get httpError() {
        return this._actions.httpError;
    }

    get reducer() {
        return this._reducer;
    }
}

export const globalErrorSlice = new GlobalErrorSlice();
