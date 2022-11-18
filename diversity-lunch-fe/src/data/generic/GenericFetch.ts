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
            const onGoindFetch = authenticatedFetchGet(`${this.url}${this.endpoint}all`);
            const onSuccess = async (response: Response) => {
                try {
                    const result: T = await response.json();
                    dispatch(this.update(result));
                    dispatch(this.initFetch(true));
                } catch {
                    callbacks.onNetworkError(new Error("Couldn't parse response body to json."));
                }
            };
            GenericFetch.handleFetchResponse(onGoindFetch, onSuccess, callbacks);
        };
    }

    public getById(id: number, callbacks: FetchCallbacks) {
        return async (dispatch: Dispatch) => {
            const onGoindFetch = authenticatedFetchGet(this.url + this.endpoint + id);
            const onSuccess = async (response: Response) => {
                try {
                    const result : T[] = await response.json();
                    dispatch(this.update(result));
                } catch {
                    callbacks.onNetworkError(new Error("Couldn't parse response body to json."));
                }
            };
            GenericFetch.handleFetchResponse(onGoindFetch, onSuccess, callbacks);
        };
    }

    public post(item : T, callbacks: FetchCallbacks) {
        return async (dispatch: Dispatch) => {
            const onGoindFetch = authenticatedFetchPost(this.url + this.endpoint, item);
            const onSuccess = async (response: Response) => {
                try {
                    const result: T = await response.json();
                    dispatch(this.add([result]));
                } catch {
                    callbacks.onNetworkError(new Error('Couldn\'t parse response body to json.'));
                }
            };
            GenericFetch.handleFetchResponse(onGoindFetch, onSuccess, callbacks);
        };
    }

    public put(item : T, callbacks: FetchCallbacks) {
        return async (dispatch: Dispatch) => {
            const onGoindFetch = authenticatedFetchPut(this.url + this.endpoint, item);
            const onSuccess = async (response: Response) => {
                try {
                    const result: T = await response.json();
                    dispatch(this.update([result]));
                } catch {
                    callbacks.onNetworkError(new Error("Couldn't parse response body to json."));
                }
            };
            GenericFetch.handleFetchResponse(onGoindFetch, onSuccess, callbacks);
        };
    }

    public removeById(id : number, callbacks: FetchCallbacks) {
        return async (dispatch: Dispatch) => {
            const onGoindFetch = authenticatedFetchDelete(this.url + this.endpoint + id);
            const onSuccess = () => dispatch(this.remove([id]));
            GenericFetch.handleFetchResponse(onGoindFetch, onSuccess, callbacks);
        };
    }

    private static async handleFetchResponse(onGoingFetch: Promise<Response>, onSuccess: (_:Response)=>void, callbacks: FetchCallbacks) {
        try {
            const response = await onGoingFetch;
            const statusCode: StatusCode = response.status.toString() as StatusCode;

            if (response.ok) {
                onSuccess(response);
            } else if (callbacks.statusCodeHandlers[statusCode]) {
                callbacks.statusCodeHandlers[statusCode]!(response);
            }
        } catch (error) {
            callbacks.onNetworkError(error as Error);
        }
    }
}
