import { Profile } from '../../model/Profile';

export type ProfileStateOk = {
    status: 'OK',
    profileData: Profile,
};

export type ProfileStateUpdating = {
    status: 'UPDATING',
    profileData: Profile,
}

export type ProfileNotCreatedYet = {
    status: 'NOT_CREATED_YET',
};

export type ProfileStateError = {
    status: 'ERROR',
};

export type ProfileStatePending = {
    status: 'PENDING',
};

export type ProfileStateLoading = {
    status: 'LOADING',
};

export type ProfileState =
    | ProfileStateOk
    | ProfileStateUpdating
    | ProfileNotCreatedYet
    | ProfileStateError
    | ProfileStatePending
    | ProfileStateLoading;
