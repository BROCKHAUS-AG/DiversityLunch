import { Dispatch } from 'redux';
import { MeetingsStateAction } from './meetings-state-action.type';
import {
    authenticatedFetchDelete,
    authenticatedFetchGet,
    authenticatedFetchPost,
} from '../../utils/fetch.utils';
import { MeetingDto } from '../../model/dtos/MeetingDto';
import { mapCreateMeetingToDto, mapDtoToMeeting } from '../../mapper/MeetingMapper';
import { CreateMeeting, Meeting } from '../../model/Meeting';

export const startLoadingAction: MeetingsStateAction = {
    type: 'MEETINGS_LOADING',
};

export const startUpdatingAction: MeetingsStateAction = {
    type: 'MEETINGS_UPDATING',
};

export const meetingCollisionAction: MeetingsStateAction = {
    type: 'MEETINGS_COLLISION',
};

export const meetingsStateErrorAction: MeetingsStateAction = {
    type: 'MEETINGS_ERROR',
    error: {
        statusCode: 500,
    },
};

async function loadingFunction(profileId: number) {
    const result: MeetingDto[] = await authenticatedFetchGet(`/api/meetings/byUser/${profileId}`)
        .then((res) => res.json());

    const meetingsOkAction: MeetingsStateAction = {
        type: 'MEETINGS_LOADING_SUCCEEDED',
        payload: result.map(mapDtoToMeeting),
    };
    return meetingsOkAction;
}

export const loadMeetings = (profileId: number) => async (dispatch: Dispatch) => {
    dispatch(startLoadingAction);
    const meetingsOkAction = await loadingFunction(profileId);

    dispatch(meetingsOkAction);
};

// eslint-disable-next-line max-len
export const createMeetings = (meeting: CreateMeeting, profileId: number) => async (dispatch: Dispatch) => {
    dispatch(startUpdatingAction);

    try {
        const result = await authenticatedFetchPost(`/api/meetings/byUser/${profileId}`, mapCreateMeetingToDto(meeting));
        if (result.status === 409) {
            dispatch(meetingCollisionAction);
        }
        if (result.status === 500) {
            dispatch(meetingsStateErrorAction);
        }
        if (result.ok) {
            const meetingOK = await loadingFunction(profileId);
            dispatch({ ...meetingOK, type: 'MEETINGS_UPDATED' });
        }
    } catch (e) {
        dispatch(meetingsStateErrorAction);
    }
};

// eslint-disable-next-line max-len
export const deleteMeetingProposal = (meeting: Meeting, profileId: number) => async (dispatch: Dispatch) => {
    dispatch(startUpdatingAction);

    const result = await authenticatedFetchDelete(`api/meetings/${meeting.id}`);
    if (result.ok) {
        const meetingOK = await loadingFunction(profileId);
        dispatch({ ...meetingOK, type: 'MEETINGS_UPDATED' });
    }
};

export const deleteMeetingUpcoming = (meeting: Meeting, profileId: number) => async (dispatch: Dispatch) => {
    dispatch(startUpdatingAction);

    const result = await authenticatedFetchPost('api/meetings/{profileId}/cancel/{meetingId}', {});
    if (result.ok) {
        const meetingOK = await loadingFunction(profileId);
        dispatch({ ...meetingOK, type: 'MEETINGS_UPDATED' });
    }
};
