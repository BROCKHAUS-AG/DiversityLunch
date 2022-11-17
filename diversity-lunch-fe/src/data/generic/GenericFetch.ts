import { Dispatch } from '@reduxjs/toolkit';
import { Identifiable } from './Identifiable';
import { GenericSlice } from './GenericSlice';
import {
    authenticatedFetchDelete,
    authenticatedFetchGet,
    authenticatedFetchPost,
    authenticatedFetchPut,
} from '../../utils/fetch.utils';
import { FetchCallbacks } from './FetchCallbacks';
import { handleFetchResponse } from '../handleFetchResponse';

export class GenericFetch<T extends Identifiable> {
    private readonly update;
    private readonly add;
    private readonly remove;
    private readonly initFetch;
    private readonly endpoint : string;

    private url : string = '/api/';

    constructor(slice: GenericSlice<T>, endpoint : string) {
        this.update = slice.actions.update;
        this.add = slice.actions.add;
        this.remove = slice.actions.remove;
        this.initFetch = slice.actions.initFetch;
        this.endpoint = `${endpoint}/`;
    }

    public getAll(callbacks: FetchCallbacks) {
        return async (dispatch: Dispatch) => {
            const onGoingFetch = authenticatedFetchGet(`${this.url}${this.endpoint}all`);
            const onSuccess = async (response: Response) => {
                try {
                    const result: T = await response.json();
                    dispatch(this.update(result));
                    dispatch(this.initFetch(true));
                } catch {
                    callbacks.onNetworkError(new Error("Couldn't parse response body to json."));
                }
            };
            handleFetchResponse(onGoingFetch, onSuccess, callbacks);
        };
    }

    public getById(id: number, callbacks: FetchCallbacks) {
        return async (dispatch: Dispatch) => {
            const onGoingFetch = authenticatedFetchGet(this.url + this.endpoint + id);
            const onSuccess = async (response: Response) => {
                try {
                    const result : T[] = await response.json();
                    dispatch(this.update(result));
                } catch {
                    callbacks.onNetworkError(new Error("Couldn't parse response body to json."));
                }
            };
            handleFetchResponse(onGoingFetch, onSuccess, callbacks);
        };
    }

    public post(item : T, callbacks: FetchCallbacks) {
        return async (dispatch: Dispatch) => {
            const onGoingFetch = authenticatedFetchPost(this.url + this.endpoint, item);
            const onSuccess = async (response: Response) => {
                try {
                    const result: T = await response.json();
                    dispatch(this.add([result]));
                } catch {
                    callbacks.onNetworkError(new Error('Couldn\'t parse response body to json.'));
                }
            };
            handleFetchResponse(onGoingFetch, onSuccess, callbacks);
        };
    }

    public put(item : T, callbacks: FetchCallbacks) {
        return async (dispatch: Dispatch) => {
            const onGoingFetch = authenticatedFetchPut(this.url + this.endpoint, item);
            const onSuccess = async (response: Response) => {
                try {
                    const result: T = await response.json();
                    dispatch(this.update([result]));
                } catch {
                    callbacks.onNetworkError(new Error("Couldn't parse response body to json."));
                }
            };
            handleFetchResponse(onGoingFetch, onSuccess, callbacks);
        };
    }

    public removeById(id : number, callbacks: FetchCallbacks) {
        return async (dispatch: Dispatch) => {
            const onGoingFetch = authenticatedFetchDelete(this.url + this.endpoint + id);
            const onSuccess = () => dispatch(this.remove([id]));
            handleFetchResponse(onGoingFetch, onSuccess, callbacks);
        };
    }
}
