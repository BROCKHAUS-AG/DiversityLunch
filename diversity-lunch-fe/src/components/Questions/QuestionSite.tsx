// styles
import '../../styles/component-styles/questions/questionSite.scss';
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Redirect } from 'react-router-dom';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { Profile } from '../../model/Profile';
import '../../styles/component-styles/questions/dropdownQuestion.scss';

import { useGetUserInformation } from '../../hooks/authentication/get-userInfo.hook';
import { createProfile } from '../../data/profile/profile.actions';
import { useHasProfile } from '../../hooks/profile/has-profile.hook';
import { LoadingAnimation } from '../Shared/LoadingAnimation';
import { Button } from '../General/Button/Button';

// Type options
import { AppStoreState } from '../../store/Store';
import { countryFetch } from '../../data/country/fetch-country';
import { Dropdown } from './Dropdown';
import { cultureFetch } from '../../data/culture/fetch-culture';
import { dietFetch } from '../../data/diet/fetch-diet';
import { educationFetch } from '../../data/education/fetch-education';
import { experienceLevelFetch } from '../../data/experienceLevel/fetch-experience-level';
import { genderFetch } from '../../data/gender/fetch-gender';

const REQUIRED_FIELDS: (keyof Profile)[] = [

];

export const QuestionSite = () => {
    const dispatch = useDispatch();
    const { profileStatus } = useHasProfile();
    const { firstName, email } = useGetUserInformation();
    const accountState = useSelector((state: AppStoreState) => state.account);

    const [currentFormState, setCurrentFormState] = useState<Partial<Profile>>({});

    // eslint-disable-next-line max-len
    const updateProfileField = React.useCallback(<KEY extends keyof Profile>(key: KEY, value?: Profile[KEY]) => {
        const updatedFormState = {
            ...currentFormState,
            [key]: value,
        };
        setCurrentFormState(updatedFormState);
    }, [currentFormState, setCurrentFormState]);

    const submit = React.useCallback((ev: React.FormEvent<HTMLFormElement>) => {
        ev.preventDefault();
        ev.stopPropagation();
        const profile = currentFormState as Profile;
        profile.firstname = firstName;
        profile.email = email;
        if (accountState.status === 'OK') {
            dispatch(createProfile(profile, accountState.accountData.id));
        }
    }, [currentFormState, firstName, email, accountState, dispatch]);

    const isValid = React.useCallback(
        () => REQUIRED_FIELDS
            .every(
                (key) => !!currentFormState[key],
            ),
        [currentFormState],
    );

    const countries = useSelector((store: AppStoreState) => store.country);
    const cultures = useSelector((store: AppStoreState) => store.culture);
    const diets = useSelector((store: AppStoreState) => store.diet);
    const educations = useSelector((store: AppStoreState) => store.education);
    const genders = useSelector((store: AppStoreState) => store.gender);
    // const hobbies = useSelector((store: AppStoreState) => store.hobby);
    // const industries = useSelector((store: AppStoreState) => store.industry);

    useEffect(() => {
        dispatch(countryFetch.getAll());
        dispatch(cultureFetch.getAll());
        dispatch(dietFetch.getAll());
        dispatch(educationFetch.getAll());
        dispatch(experienceLevelFetch.getAll());
        dispatch(genderFetch.getAll());
        // dispatch(hobbyFetch.getAll());
        // dispatch(industryFetch.getAll());
    }, []);

    return (
        <div className="QuestionSite">
            <DiversityIconContainer />
            {
                profileStatus === 'LOADING' && <LoadingAnimation size="block-app" />
            }
            {
                profileStatus === 'OK' && accountState.status === 'OK' && <Redirect to="/dashboard" />
            }

            <h4>
                Hallo
                {' '}
                {firstName}
            </h4>

            <form onSubmit={submit}>
                {/* <NumberDropdown
                    label="Wann wurdest du geboren?"
                    currentValue={currentFormState.birthYear}
                    options={BIRTH_YEAR_DROPDOWN_OPTIONS}
                    onChange={(value) => updateProfileField('birthYear', value)}
                    placeholder="Geburtsjahr"
                /> */}

                <Dropdown
                    options={countries.items}
                    text="Was ist dein Herkunftsland?"
                    onChange={(value) => updateProfileField('country', value)}
                />

                <Dropdown
                    options={cultures.items}
                    text="Was ist dein Herkunftsland?"
                    onChange={(value) => updateProfileField('culture', value)}
                />

                <Dropdown
                    options={diets.items}
                    text="Was ist dein Herkunftsland?"
                    onChange={(value) => updateProfileField('diet', value)}
                />

                <Dropdown
                    options={educations.items}
                    text="Was ist dein Herkunftsland?"
                    onChange={(value) => updateProfileField('education', value)}
                />

                {/* <Dropdown
                    options={hobbies.items}
                    text="Was ist dein Herkunftsland?"
                    onChange={(value) => updateProfileField('hobbies', value)}
                /> */}

                <Dropdown
                    options={genders.items}
                    text="Was ist dein Herkunftsland?"
                    onChange={(value) => updateProfileField('gender', value)}
                />

                {/* <Dropdown
                    options={industries.items}
                    text="Was ist dein Herkunftsland?"
                    onChange={(value) => updateProfileField('industry', value)}
                /> */}

                <Button
                    disabled={!isValid()}
                    label="Profil anlegen"
                    type="submit"
                />
            </form>
        </div>
    );
};
