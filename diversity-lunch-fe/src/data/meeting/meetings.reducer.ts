import { Reducer } from 'redux';
import {
    MeetingsCollision,
    MeetingsState,
    MeetingsUpdated,
    MeetingsUpdating,
} from './meetings-state.type';
import { MeetingsStateAction } from './meetings-state-action.type';

const initialState: MeetingsState = {
    status: 'PENDING',
};

export const meetingsReducer: Reducer<MeetingsState, MeetingsStateAction> = (
    // eslint-disable-next-line default-param-last
    state: MeetingsState = initialState,
    action: MeetingsStateAction,
): MeetingsState => {
    switch (action.type) {
        case 'MEETINGS_PENDING': {
            return initialState;
        }
        case 'MEETINGS_ERROR': {
            return {
                status: 'ERROR',
                error: {
                    statusCode: action.error.statusCode,
                },
            };
        }
        case 'MEETINGS_LOADING': {
            return {
                status: 'LOADING',
            };
        }
        case 'MEETINGS_LOADING_SUCCEEDED': {
            return {
                status: 'OK',
                meetings: action.payload,
            };
        }
        case 'MEETINGS_UPDATING': {
            return {
                ...state,
                status: 'UPDATING',
            } as MeetingsUpdating;
        }
        case 'MEETINGS_COLLISION': {
            return {
                ...state,
                status: 'COLLISION',
            } as MeetingsCollision;
        }
        case 'MEETINGS_UPDATED': {
            return {
                status: 'UPDATED',
                meetings: action.payload,
            } as MeetingsUpdated;
        }
        default: {
            return state;
        }
    }
};
