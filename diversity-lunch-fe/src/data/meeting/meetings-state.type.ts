import { Meeting } from '../../types/Meeting';

export type MeetingsPending = {
  status: 'PENDING',
};

export type MeetingsOk = {
  status: 'OK',
  meetings: Meeting[],
};

export type MeetingsLoading = {
  status: 'LOADING',
}

export type MeetingsError = {
  status: 'ERROR',
  error: {
    statusCode: number,
  },
};

export type MeetingsUpdating = {
  status: 'UPDATING',
  meetings: Meeting[],
}

export type MeetingsCollision = {
    status: 'COLLISION',
    meetings: Meeting[],
}

export type MeetingsUpdated = {
    status: 'UPDATED',
    meetings: Meeting[],
}

export type MeetingsState =
  | MeetingsPending
  | MeetingsOk
  | MeetingsError
  | MeetingsLoading
  | MeetingsUpdating
  | MeetingsCollision
  | MeetingsUpdated;
