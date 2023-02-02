import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Redirect } from 'react-router-dom';

import { AppStoreState } from '../../../data/app-store';
import './question-site.scss';
import { ProfileForm } from '../../components/profile-form/profile-form';
import { useGetUserInformation } from '../../../hooks/authentication/get-user-info.hook';
import { Profile } from '../../../model/Profile';
import { createProfile, loadProfile } from '../../../data/profile/profile.actions';
import { AccountStateOk } from '../../../data/account/account-state.type';
import { isValidProfile } from '../../../utils/validators/profile-validator';
import { LoadingAnimation } from '../../components/loading-animation/loading-animation';
import { loadAccount } from '../../../data/account/account.actions';

export const QuestionSite = () => {
    const dispatch = useDispatch();
    const profileState = useSelector((state: AppStoreState) => state.profile);
    const accountState = useSelector((state: AppStoreState) => state.account);
    const [creationState, setCreationState] = useState('');
    const {
        firstName,
        email,
    } = useGetUserInformation();
    useEffect(() => {
        if (creationState === 'created') {
            if (profileState.status === 'OK') {
                dispatch(loadProfile(profileState.profileData.id));
                dispatch(loadAccount);
            }
        }
    }, [creationState, profileState]);
    if (profileState.status === 'OK' && accountState.status === 'OK') return <Redirect to="/dashboard" />;
    if (profileState.status === 'ERROR' || accountState.status === 'ERROR') return <p><strong>error</strong></p>;
    if (profileState.status === 'PENDING'
    || profileState.status === 'LOADING'
    || profileState.status === 'UPDATING'
    || accountState.status === 'PENDING'
    || accountState.status === 'LOADING') {
        return <LoadingAnimation />;
    }

    const profile: Partial<Profile> = profileState.status === 'NOT_CREATED_YET' ? {
        name: firstName,
        email,
        id: 0,
    } : profileState.profileData;

    const submit = (p: Partial<Profile>) => {
        dispatch(createProfile(p as Profile, (accountState as AccountStateOk).accountData.id));
        setCreationState('created');
    };
    return (
        <div className="QuestionSite">
            <ProfileForm initialProfile={profile} onSubmit={submit} checkValidity={isValidProfile} />
        </div>
    );
};
