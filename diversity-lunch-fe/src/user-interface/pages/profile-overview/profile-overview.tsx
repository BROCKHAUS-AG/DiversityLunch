import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import './profile.scss';
import { Profile } from '../../../model/Profile';
import { isValidProfile, isUpdatedProfile } from '../../../utils/validators/profile-validator';
import { ProfileForm } from '../../components/profile-form/profile-form';
import { AppStoreState } from '../../../data/app-store';
import { updateProfile } from '../../../data/profile/profile.actions';
import { ProfileStateOk } from '../../../data/profile/profile-state.type';
import { LoadingAnimation } from '../../components/loading-animation/loading-animation';

export const ProfileOverview = () => {
    const profileState = useSelector((state: AppStoreState) => state.profile);
    const dispatch = useDispatch();

    if (profileState.status !== 'OK') {
        return <LoadingAnimation />;
    }

    const profile: Profile = (profileState as ProfileStateOk).profileData;

    const submit = (p: Partial<Profile>) => {
        dispatch(updateProfile(p as Profile));
    };

    const isUpdated = (updatedProfile: Partial<Profile>) => isValidProfile(updatedProfile) && isUpdatedProfile(updatedProfile as Profile, profile);

    return (
        <section className="view">
            <div className="profile-overview-wrapper">
                <div className="Profile-name-container">
                    <h3>{`Hallo ${profile.name}`}</h3>
                </div>
                {profile.name !== ''
                    && <ProfileForm initialProfile={profile} onSubmit={submit} checkValidity={isUpdated} />}
            </div>

        </section>
    );
};
