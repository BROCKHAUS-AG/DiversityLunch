import { Dispatch } from '@reduxjs/toolkit';
import { authenticatedFetchGet } from '../../utils/fetch.utils';
import { globalErrorSlice } from '../error/global-error-slice';
import { profilesAction } from './profiles-reducer';
import { Profile } from '../../model/Profile';

export function getAllProfiles() {
    return async (dispatch: Dispatch) => {
        try {
            const response = await authenticatedFetchGet('api/profiles/all');

            if (!response.ok) {
                dispatch(globalErrorSlice.httpError({ statusCode: response.status }));
            } else {
                const result : Profile[] = await response.json();

                dispatch(profilesAction.update(result));
                dispatch(profilesAction.initFetch(undefined));
            }
        } catch (error) {
            dispatch(globalErrorSlice.error(undefined));
        }
    };
}
