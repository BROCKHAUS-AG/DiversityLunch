import { Meeting } from '../../types/Meeting';

type MeetingsStateOkAction = {
  type: 'MEETINGS_LOADING_SUCCEEDED',
  payload: Meeting[],
};

type MeetingsStateGeneralAction = {
  type: 'MEETINGS_ERROR',
  error: {
    statusCode: number,
  }
};

type MeetingsStateLoading = {
  type: 'MEETINGS_LOADING',
};

type MeetingsStateUpdating = {
  type: 'MEETINGS_UPDATING'
}

type MeetingsStateCollision = {
    type: 'MEETINGS_COLLISION'
}

type MeetingsStateUpdated = {
    type: 'MEETINGS_UPDATED',
    payload: Meeting[],
}

type MeetingsStatePending = {
    type: 'MEETINGS_PENDING',
}

export type MeetingsStateAction =
  | MeetingsStateOkAction
  | MeetingsStateGeneralAction
  | MeetingsStateLoading
  | MeetingsStateUpdating
  | MeetingsStateCollision
  | MeetingsStateUpdated
  | MeetingsStatePending;
