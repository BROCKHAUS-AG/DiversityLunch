import { Dispatch } from '@reduxjs/toolkit';
import { Identifiable } from './Identifiable';
import { GenericSlice } from './GenericSlice';
import {
    authenticatedFetchDelete,
    authenticatedFetchGet,
    authenticatedFetchPost,
    authenticatedFetchPut,
} from '../../utils/fetch.utils';
import { StatusCode } from './StatusCode';
import { FetchCallbacks } from './FetchCallbacks';

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
            try {
                const response = await authenticatedFetchGet(`${this.url}${this.endpoint}all`);
                const statusCode: StatusCode = response.status.toString() as StatusCode;

                if (response.ok) {
                    try {
                        const result: T = await response.json();
                        dispatch(this.update(result));
                        dispatch(this.initFetch(true));
                    } catch {
                        callbacks.onNetworkError(new Error("Couldn't parse response body to json."));
                    }
                } else if (callbacks.statusCodeHandlers[statusCode]) {
                    callbacks.statusCodeHandlers[statusCode]!(response);
                }
            } catch (error) {
                callbacks.onNetworkError(error as Error);
            }
        };
    }

    public getById(id: number, callbacks: FetchCallbacks) {
        return async (dispatch: Dispatch) => {
            try {
                const response = await authenticatedFetchGet(this.url + this.endpoint + id);
                const statusCode: StatusCode = response.status.toString() as StatusCode;

                if (response.ok) {
                    try {
                        const result : T[] = await response.json();
                        dispatch(this.update(result));
                    } catch {
                        callbacks.onNetworkError(new Error("Couldn't parse response body to json."));
                    }
                } else if (callbacks.statusCodeHandlers[statusCode]) {
                    callbacks.statusCodeHandlers[statusCode]!(response);
                }
            } catch (error) {
                callbacks.onNetworkError(error as Error);
            }
        };
    }

    public post(item : T, callbacks: FetchCallbacks) {
        return async (dispatch: Dispatch) => {
            try {
                const response = await authenticatedFetchPost(this.url + this.endpoint, item);
                const statusCode: StatusCode = response.status.toString() as StatusCode;

                if (response.ok) {
                    try {
                        const result: T = await response.json();
                        dispatch(this.add([result]));
                    } catch {
                        callbacks.onNetworkError(new Error("Couldn't parse response body to json."));
                    }
                } else if (callbacks.statusCodeHandlers[statusCode]) {
                    callbacks.statusCodeHandlers[statusCode]!(response);
                }
            } catch (error) {
                callbacks.onNetworkError(error as Error);
            }
        };
    }

    public put(item : T, callbacks: FetchCallbacks) {
        return async (dispatch: Dispatch) => {
            try {
                const response = await authenticatedFetchPut(this.url + this.endpoint, item);
                const statusCode: StatusCode = response.status.toString() as StatusCode;

                if (response.ok) {
                    try {
                        const result: T = await response.json();
                        dispatch(this.update([result]));
                    } catch {
                        callbacks.onNetworkError(new Error("Couldn't parse response body to json."));
                    }
                } else if (callbacks.statusCodeHandlers[statusCode]) {
                    callbacks.statusCodeHandlers[statusCode]!(response);
                }
            } catch (error) {
                callbacks.onNetworkError(error as Error);
            }
        };
    }

    public removeById(id : number, callbacks: FetchCallbacks) {
        return async (dispatch: Dispatch) => {
            try {
                const response = await authenticatedFetchDelete(this.url + this.endpoint + id);
                const statusCode: StatusCode = response.status.toString() as StatusCode;

                if (response.ok) {
                    dispatch(this.remove([id]));
                } else if (callbacks.statusCodeHandlers[statusCode]) {
                    callbacks.statusCodeHandlers[statusCode]!(response);
                }
            } catch (error) {
                callbacks.onNetworkError(error as Error);
            }
        };
    }
}
