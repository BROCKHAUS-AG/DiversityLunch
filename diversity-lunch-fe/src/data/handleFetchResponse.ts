import { FetchCallbacks } from './generic/FetchCallbacks';
import { StatusCode } from './generic/StatusCode';

export async function handleFetchResponse(onGoingFetch: Promise<Response>, onSuccess: (_:Response)=>void, callbacks: FetchCallbacks) {
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
