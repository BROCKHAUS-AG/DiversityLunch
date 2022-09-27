import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import '../../styles/component-styles/profile/profile.scss';
import { Profile } from '../../model/Profile';
import { isValidProfile, isUpdatedProfile } from '../../utils/validators/profile-validator';
import { ProfileForm } from '../Shared/ProfileForm/profile-form';
import { AppStoreState } from '../../store/Store';
import { updateProfile } from '../../data/profile/profile.actions';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { ProfileStateOk } from '../../data/profile/profile-state.type';
import { LoadingAnimation } from '../Shared/LoadingAnimation';

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
            <div className="Profile-logo-container">
                <CloseSiteContainer />
                <DiversityIconContainer title="DEIN PROFIL" />
            </div>

            <div className="Profile-name-container">
                <h5 className="Profile-user-name">{profile.name}</h5>
            </div>
            {profile.name !== ''
                && <ProfileForm initialProfile={profile} onSubmit={submit} checkValidity={isUpdated} />}
        </section>
    );
};
