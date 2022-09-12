import { Profile } from '../../types/Profile';

type ProfileStateOkAction = {
    type: 'PROFILE_LOADING_SUCCEEDED',
    payload: Profile,
};

type ProfileStateGeneralAction = {
    type: 'PROFILE_ERROR' | 'PROFILE_DOES_NOT_EXIST' | 'PROFILE_LOADING' | 'PROFILE_UPDATING',
};

export type ProfileStateAction =
    | ProfileStateOkAction
    | ProfileStateGeneralAction;
