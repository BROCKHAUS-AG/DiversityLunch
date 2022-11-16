import { Dispatch } from '@reduxjs/toolkit';
import { Identifiable } from './Identifiable';
import { GenericSlice } from './GenericSlice';
import {
    authenticatedFetchDelete,
    authenticatedFetchGet,
    authenticatedFetchPost,
    authenticatedFetchPut,
} from '../../utils/fetch.utils';
import { globalErrorSlice } from '../error/global-error-slice';
import { StatusCodeMap } from './StatusCodeMap';
import { StatusCode } from './StatusCode';

export class GenericFetch<T extends Identifiable> {
    private readonly update;
    private readonly add;
    private readonly remove;
    private readonly initFetch;
    private readonly endpoint : string;

    private url : string = '/api/';

    constructor(slice: GenericSlice<T>, endpoint : string, private readonly _errorSlice = globalErrorSlice) {
        this.update = slice.actions.update;
        this.add = slice.actions.add;
        this.remove = slice.actions.remove;
        this.initFetch = slice.actions.initFetch;
        this.endpoint = `${endpoint}/`;
    }

    public getAll(statusCodeHandlerMap?: StatusCodeMap, onNetworkError?: (networkError: Error)=>void) {
        return async (dispatch: Dispatch) => {
            try {
                const response = await authenticatedFetchGet(`${this.url}${this.endpoint}all`);

                if (response.ok) {
                    const result : T = await response.json();

                    dispatch(this.update(result));
                    dispatch(this.initFetch(true));
                } else if (statusCodeHandlerMap) {
                    const statusCode = response.status.toString();
                    if (statusCodeHandlerMap) {
                        const handler = statusCodeHandlerMap[statusCode as StatusCode];
                        if (handler) handler(response);
                    }
                } else {
                    dispatch(this._errorSlice.httpError({ statusCode: response.status }));
                }
            } catch (error) {
                if (onNetworkError) {
                    onNetworkError(error as Error);
                } else {
                    dispatch(this._errorSlice.error(undefined));
                }
            }
        };
    }

    public getById(id: number, statusCodeHandlerMap?: StatusCodeMap, onNetworkError?: (networkError: Error)=>void) {
        return async (dispatch: Dispatch) => {
            try {
                const response = await authenticatedFetchGet(this.url + this.endpoint + id);

                if (response.ok) {
                    const result : T[] = await response.json();
                    dispatch(this.update(result));
                } else if (statusCodeHandlerMap) {
                    const statusCode = response.status.toString();
                    if (statusCodeHandlerMap) {
                        const handler = statusCodeHandlerMap[statusCode as StatusCode];
                        if (handler) handler(response);
                    }
                } else {
                    dispatch(this._errorSlice.httpError({ statusCode: response.status }));
                }
            } catch (error) {
                if (onNetworkError) {
                    onNetworkError(error as Error);
                } else {
                    dispatch(this._errorSlice.error(undefined));
                }
            }
        };
    }

    public post(item : T, statusCodeHandlerMap?: StatusCodeMap, onNetworkError?: (networkError: Error)=>void) {
        return async (dispatch: Dispatch) => {
            try {
                const response = await authenticatedFetchPost(this.url + this.endpoint, item);

                if (response.ok) {
                    const result : T = await response.json();
                    dispatch(this.add([result]));
                } else if (statusCodeHandlerMap) {
                    const statusCode = response.status.toString();
                    if (statusCodeHandlerMap) {
                        const handler = statusCodeHandlerMap[statusCode as StatusCode];
                        if (handler) handler(response);
                    }
                } else {
                    dispatch(this._errorSlice.httpError({ statusCode: response.status }));
                }
            } catch (error) {
                if (onNetworkError) {
                    onNetworkError(error as Error);
                } else {
                    dispatch(this._errorSlice.error(undefined));
                }
            }
        };
    }

    public put(item : T, statusCodeHandlerMap?: StatusCodeMap, onNetworkError?: (networkError: Error)=>void) {
        return async (dispatch: Dispatch) => {
            try {
                const response = await authenticatedFetchPut(this.url + this.endpoint, item);

                if (response.ok) {
                    const result : T = await response.json();
                    dispatch(this.update([result]));
                } else if (statusCodeHandlerMap) {
                    const statusCode = response.status.toString();
                    if (statusCodeHandlerMap) {
                        const handler = statusCodeHandlerMap[statusCode as StatusCode];
                        if (handler) handler(response);
                    }
                } else {
                    dispatch(this._errorSlice.httpError({ statusCode: response.status }));
                }
            } catch (error) {
                if (onNetworkError) {
                    onNetworkError(error as Error);
                } else {
                    dispatch(this._errorSlice.error(undefined));
                }
            }
        };
    }

    public removeById(id : number, statusCodeHandlerMap?: StatusCodeMap, onNetworkError?: (networkError: Error)=>void) {
        return async (dispatch: Dispatch) => {
            try {
                const response = await authenticatedFetchDelete(this.url + this.endpoint + id);

                if (response.ok) {
                    dispatch(this.remove([id]));
                } else if (statusCodeHandlerMap) {
                    const statusCode = response.status.toString();
                    if (statusCodeHandlerMap) {
                        const handler = statusCodeHandlerMap[statusCode as StatusCode];
                        if (handler) handler(response);
                    }
                } else {
                    dispatch(this._errorSlice.httpError({ statusCode: response.status }));
                }
            } catch (error) {
                if (onNetworkError) {
                    onNetworkError(error as Error);
                } else {
                    dispatch(this._errorSlice.error(undefined));
                }
            }
        };
    }
}
