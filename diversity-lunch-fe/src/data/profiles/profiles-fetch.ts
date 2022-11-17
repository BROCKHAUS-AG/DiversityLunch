import { Dispatch } from '@reduxjs/toolkit';
import { authenticatedFetchGet } from '../../utils/fetch.utils';
import { profilesAction } from './profiles-reducer';
import { Profile } from '../../model/Profile';
import { FetchCallbacks } from '../generic/FetchCallbacks';
import { handleFetchResponse } from '../handleFetchResponse';

export function getAllProfiles(fetchCallbacks: FetchCallbacks) {
    return async (dispatch: Dispatch) => {
        const onGoingFetch = authenticatedFetchGet('api/profiles/all');
        const onSuccess = async (response: Response) => {
            try {
                const result : Profile[] = await response.json();
                dispatch(profilesAction.update(result));
                dispatch(profilesAction.initFetch(undefined));
            } catch {
                fetchCallbacks.onNetworkError(new Error(`Parse failed: ${response.url} responded with a non-json body`));
            }
        };
        handleFetchResponse(onGoingFetch, onSuccess, fetchCallbacks);
    };
}
