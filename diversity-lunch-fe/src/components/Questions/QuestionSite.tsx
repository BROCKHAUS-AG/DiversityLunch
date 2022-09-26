import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Redirect } from 'react-router-dom';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';

import { AppStoreState } from '../../store/Store';
import '../../styles/component-styles/questions/questionSite.scss';
import '../../styles/component-styles/questions/dropdownQuestion.scss';
import { ProfileForm } from '../Shared/ProfileForm/profile-form';
import { useGetUserInformation } from '../../hooks/authentication/get-userInfo.hook';
import { Profile } from '../../model/Profile';
import { createProfile } from '../../data/profile/profile.actions';
import { AccountStateOk } from '../../data/account/account-state.type';
import { isValidProfile } from '../../utils/validators/profile-validator';
import { LoadingAnimation } from '../Shared/LoadingAnimation';

export const QuestionSite = () => {
    const dispatch = useDispatch();
    const profileState = useSelector((state: AppStoreState) => state.profile);
    const accountState = useSelector((state: AppStoreState) => state.account);
    const {
        firstName,
        email,
    } = useGetUserInformation();

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
    };
    return (
        <div className="QuestionSite">
            <DiversityIconContainer />
            <h4>{`Hallo ${firstName}`}</h4>
            <ProfileForm initialProfile={profile} onSubmit={submit} checkValidity={isValidProfile} />
        </div>
    );
};
