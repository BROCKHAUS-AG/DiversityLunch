import { Dispatch } from 'redux';
import { ProfileStateAction } from './profile-state-action.type';
import { authenticatedFetchGet, authenticatedFetchPost, authenticatedFetchPut } from '../../utils/fetch.utils';
import { Profile } from '../../model/Profile';
import { startAccountPendingAction } from '../account/account.actions';

export const startProfileLoadingAction: ProfileStateAction = {
    type: 'PROFILE_LOADING',
};

export const hasNoProfileAction: ProfileStateAction = {
    type: 'PROFILE_DOES_NOT_EXIST',
};

export const loadProfileErroredAction: ProfileStateAction = {
    type: 'PROFILE_ERROR',
};

export const startProfileUpdatingAction: ProfileStateAction = {
    type: 'PROFILE_UPDATING',
};

export const loadProfile = (profileId: number) => async (dispatch: Dispatch) => {
    dispatch(startProfileLoadingAction);

    const result: Response = await authenticatedFetchGet(`/api/profiles/${profileId}`);

    const hasError = !result.ok;

    // kein profil gefunden -> es gibt noch keins
    if (hasError && result.status === 404) {
        dispatch(hasNoProfileAction);
    } else if (hasError) {
        dispatch(loadProfileErroredAction);
    } else {
        const profile : Profile = await result.json();

        const profileOkAction: ProfileStateAction = {
            type: 'PROFILE_LOADING_SUCCEEDED',
            payload: profile,
        };

        dispatch(profileOkAction);
    }
};

// eslint-disable-next-line max-len
export const createProfile = (profile: Profile, accountId: number) => async (dispatch: Dispatch) => {
    dispatch(startProfileLoadingAction);

    const result: Response = await authenticatedFetchPost(`/api/profiles/byAccount/${accountId}`, profile);

    if (result.ok) {
        const profileResult : Profile = await result.json();

        const profileOkAction: ProfileStateAction = {
            type: 'PROFILE_LOADING_SUCCEEDED',
            payload: profileResult,
        };
        dispatch(profileOkAction);
    } else {
        dispatch(loadProfileErroredAction);
    }
};

export const updateProfile = (profile: Profile) => async (dispatch: Dispatch) => {
    dispatch(startProfileUpdatingAction);

    const result: Response = await authenticatedFetchPut(`/api/profiles/${profile.id}`, profile);

    if (result.ok) {
        const profileResult : Profile = await result.json();

        const profileOkAction: ProfileStateAction = {
            type: 'PROFILE_LOADING_SUCCEEDED',
            payload: profileResult,
        };
        dispatch(profileOkAction);
    } else {
        dispatch(loadProfileErroredAction);
    }
};
