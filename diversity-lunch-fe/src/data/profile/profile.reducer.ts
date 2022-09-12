import { Reducer } from 'redux';
import { ProfileState, ProfileStateUpdating } from './profile-state.type';
import { ProfileStateAction } from './profile-state-action.type';

const initialState: ProfileState = {
    status: 'PENDING',
};

export const profileReducer: Reducer<ProfileState, ProfileStateAction> = (
    // eslint-disable-next-line default-param-last
    state: ProfileState = initialState,
    action: ProfileStateAction,
): ProfileState => {
    switch (action.type) {
        case 'PROFILE_ERROR': {
            return {
                status: 'ERROR',
            };
        }
        case 'PROFILE_LOADING': {
            return {
                status: 'LOADING',
            };
        }
        // Transition to updating can only happen from status 'OK' - so payload is always set
        case 'PROFILE_UPDATING': {
            return {
                ...state,
                status: 'UPDATING',
            } as ProfileStateUpdating;
        }
        case 'PROFILE_DOES_NOT_EXIST': {
            return {
                status: 'NOT_CREATED_YET',
            };
        }
        case 'PROFILE_LOADING_SUCCEEDED': {
            return {
                status: 'OK',
                profileData: action.payload,
            };
        }
        default: {
            return state;
        }
    }
};
