import { StatusCodeCallbackMap } from './StatusCodeCallbackMap';

export interface FetchCallbacks {
    onNetworkError: (e: Error) => void,
    statusCodeHandlers: StatusCodeCallbackMap,
}
