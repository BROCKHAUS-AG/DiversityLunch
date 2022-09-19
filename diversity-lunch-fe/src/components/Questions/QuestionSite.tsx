import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Redirect } from 'react-router-dom';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { LoadingAnimation } from '../Shared/LoadingAnimation';

import { AppStoreState } from '../../store/Store';
import '../../styles/component-styles/questions/questionSite.scss';
import '../../styles/component-styles/questions/dropdownQuestion.scss';
import { ProfileForm } from '../Shared/ProfileForm/profile-form';
import { useGetUserInformation } from '../../hooks/authentication/get-userInfo.hook';
import { Profile } from '../../model/Profile';
import { createProfile } from '../../data/profile/profile.actions';
import { AccountStateOk } from '../../data/account/account-state.type';

export const QuestionSite = () => {
    const dispatch = useDispatch();
    const profileState = useSelector((state: AppStoreState) => state.profile);
    const accountState = useSelector((state: AppStoreState) => state.account);
    const {
        firstName,
        email,
    } = useGetUserInformation();

    if (profileState.status === 'ERROR' || accountState.status === 'ERROR') return <p><strong>error</strong></p>;
    if (profileState.status === 'PENDING'
        || profileState.status === 'LOADING'
        || profileState.status === 'UPDATING'
        || accountState.status === 'PENDING'
        || accountState.status === 'LOADING') return <p><strong>loading</strong></p>;

    const profile: Partial<Profile> = profileState.status === 'OK' ? profileState.profileData : {
        name: firstName,
        email,
    };

    const submit = (profile: Profile) => {
        dispatch(createProfile(profile, (accountState as AccountStateOk).accountData.id));
    };

    const isValid = React.useCallback(
        () => REQUIRED_FIELDS
            .every(
                (key) => !!currentFormState[key],
            ),
        [currentFormState],
    );

    return (
        <div className="QuestionSite">
            <DiversityIconContainer />
            {
                profileStatus === 'LOADING' && <LoadingAnimation size="block-app" />
            }
            {
                profileStatus === 'OK' && accountState.status === 'OK' && <Redirect to="/dashboard" />
            }
            {
                profileStatus === 'OK' && accountState.status !== 'OK' && (
                    <>
                        <h4>
                            Hallo
                            {' '}
                            {firstName}
                        </h4>

                        <ProfileForm profile={profile} />
                    </>
                )
            }

        </div>
    );
};
