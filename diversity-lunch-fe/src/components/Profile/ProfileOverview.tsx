import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import '../../styles/component-styles/profile/profile.scss';
import { Profile } from '../../model/Profile';
import { isValidProfile, isUpdatedProfile } from '../../utils/validators/profile-validator';
import { ProfileForm } from '../Shared/ProfileForm/profile-form';
import { AppStoreState } from '../../store/Store';
import { updateProfile } from '../../data/profile/profile.actions';

export const ProfileOverview = () => {
    // const { fullName } = useGetUserInformation();

    // const [currentFormState, setCurrentFormState] = useState<Profile>({ ...profileData });
    // const [profileChanged, setProfileChanged] = useState<boolean>(false);
    const profileState = useSelector((state: AppStoreState) => state.profile);
    const dispatch = useDispatch();

    const profile: Partial<Profile> = profileState.status !== 'OK' ? {
        name: '',
        email: '',
        id: 0,
    } : profileState.profileData;

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

    return (
        <div>
            {profile.name !== ''
                && <ProfileForm initialProfile={profile} onSubmit={submit} isUpdated={isUpdatedProfile} checkValidity={isValidProfile} />}
        </div>
    );
};
