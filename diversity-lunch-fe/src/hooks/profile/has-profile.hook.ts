import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { AppStoreState } from '../../data/app-store';
import { hasNoProfileAction, loadProfile } from '../../data/profile/profile.actions';
import { loadAccount } from '../../data/account/account.actions';

export const useHasProfile = () => {
    const dispatch = useDispatch();

    const profileStatus = useSelector((state: AppStoreState) => state.profile.status);

    const accountState = useSelector((state: AppStoreState) => state.account);
    const authenticationState = useSelector((state: AppStoreState) => state.authentication);

    React.useEffect(() => {
        if (accountState.status === 'PENDING' && authenticationState.status === 'OK') {
            dispatch(loadAccount);
        }
        if (accountState.status === 'OK' && profileStatus === 'PENDING') {
            if (accountState.accountData.profileId === 0) {
                dispatch(hasNoProfileAction);
            } else {
                const { profileId } = accountState.accountData;
                dispatch(loadProfile(profileId));
                console.log('Marker01 ', profileStatus);
            }
        }
    }, [profileStatus, dispatch, authenticationState, accountState]);

    return { profileStatus };
};
