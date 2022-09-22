import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useHistory } from 'react-router-dom';
import '../../styles/component-styles/profile/profile.scss';

import { useGetUserInformation } from '../../hooks/authentication/get-userInfo.hook';
import { createProfile, updateProfile } from '../../data/profile/profile.actions';
import { shallowEquals } from '../../utils/equals.utils';
import { Profile } from '../../model/Profile';
import { isValidProfile } from '../../utils/validators/profile-validator';
import { ProfileForm } from '../Shared/ProfileForm/profile-form';
import { AppStoreState } from '../../store/Store';
import { AccountStateOk } from '../../data/account/account-state.type';

type ProfileOverviewProps = {
  profileData: Profile;
}

export const ProfileOverview = (props: ProfileOverviewProps) => {
    const {
        profileData,

    } = props;

    const { fullName } = useGetUserInformation();
    const dispatch = useDispatch();
    const [currentFormState, setCurrentFormState] = useState<Profile>({ ...profileData });
    const [profileChanged, setProfileChanged] = useState<boolean>(false);
    const profileState = useSelector((state: AppStoreState) => state.profile);
    const profile: Partial<Profile> = profileState.status !== 'OK' ? {
        name: '',
        email: '',
        id: 0,
    } : profileState.profileData;

    const history = useHistory();
    // eslint-disable-next-line max-len
    const updateProfileField = React.useCallback(<KEY extends keyof Profile>(key: KEY, value: Profile[KEY],
    ) => {
        const updatedFormState = {
            ...currentFormState,
            [key]: value,
        };

        setProfileChanged(!shallowEquals(profileData, updatedFormState));

        setCurrentFormState(updatedFormState);
    }, [currentFormState, profileData]);

    const submit = (p: Partial<Profile>) => {
        alert('hi');
    };
    return (
        <div>
            {profile.name !== ''
                && <ProfileForm profile={profile} onSubmit={submit} checkValidity={isValidProfile} />}
        </div>
    );
};
