import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import '../../styles/component-styles/profile/profile.scss';
import { Profile } from '../../model/Profile';
import { isValidProfile, isUpdatedProfile, partialProfileToProfile } from '../../utils/validators/profile-validator';
import { ProfileForm } from '../Shared/ProfileForm/profile-form';
import { AppStoreState } from '../../store/Store';
import { updateProfile } from '../../data/profile/profile.actions';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { ProfileStateOk } from '../../data/profile/profile-state.type';

export const ProfileOverview = () => {
    // const { fullName } = useGetUserInformation();

    // const [currentFormState, setCurrentFormState] = useState<Profile>({ ...profileData });
    // const [profileChanged, setProfileChanged] = useState<boolean>(false);

    const profileState = useSelector((state: AppStoreState) => state.profile);
    const dispatch = useDispatch();

    if (profileState.status !== 'OK') {
        return (<p>loading</p>);
    }

    const profile: Profile = (profileState as ProfileStateOk).profileData;

    // const history = useHistory();
    // // eslint-disable-next-line max-len
    // const updateProfileField = React.useCallback(<KEY extends keyof Profile>(key: KEY, value: Profile[KEY],
    // ) => {
    //     const updatedFormState = {
    //         ...currentFormState,
    //         [key]: value,
    //     };
    //
    //     setProfileChanged(!shallowEquals(profileData, updatedFormState));
    //
    //     setCurrentFormState(updatedFormState);
    // }, [currentFormState, profileData]);
    //
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
