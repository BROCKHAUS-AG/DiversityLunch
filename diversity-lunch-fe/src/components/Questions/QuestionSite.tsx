import React, { ChangeEvent, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Redirect } from 'react-router-dom';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { Profile } from '../../model/Profile';

import { useGetUserInformation } from '../../hooks/authentication/get-userInfo.hook';
import { createProfile } from '../../data/profile/profile.actions';
import { useHasProfile } from '../../hooks/profile/has-profile.hook';
import { LoadingAnimation } from '../Shared/LoadingAnimation';
import { Button } from '../General/Button/Button';

import { AppStoreState } from '../../store/Store';
import { countryFetch } from '../../data/country/fetch-country';
import { Dropdown } from './Dropdown';
import { cultureFetch } from '../../data/culture/fetch-culture';
import { dietFetch } from '../../data/diet/fetch-diet';
import { educationFetch } from '../../data/education/fetch-education';
import { genderFetch } from '../../data/gender/fetch-gender';
import { hobbyFetch } from '../../data/hobby/fetch-hobby';
import { industryFetch } from '../../data/industry/fetch-industry';
import { languageFetch } from '../../data/language/language-fetch';
import { projectFetch } from '../../data/project/project-fetch';
import { religionFetch } from '../../data/religion/religion-fetch';
import { workExperienceFetch } from '../../data/work-experience/work-experience-fetch';
import '../../styles/component-styles/questions/questionSite.scss';
import '../../styles/component-styles/questions/dropdownQuestion.scss';

const REQUIRED_FIELDS: (keyof Profile)[] = [];

export const QuestionSite = () => {
    const dispatch = useDispatch();
    const { profileStatus } = useHasProfile();
    const {
        firstName,
        email,
    } = useGetUserInformation();
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
    // const cultures = useSelector((store: AppStoreState) => store.culture);
    const diets = useSelector((store: AppStoreState) => store.diet);
    const educations = useSelector((store: AppStoreState) => store.education);
    const genders = useSelector((store: AppStoreState) => store.gender);
    const hobbies = useSelector((store: AppStoreState) => store.hobby);
    // const industries = useSelector((store: AppStoreState) => store.industry);
    const languages = useSelector((store: AppStoreState) => store.language);
    const project = useSelector((store: AppStoreState) => store.project);
    const religions = useSelector((store: AppStoreState) => store.religion);
    const workExperience = useSelector((store: AppStoreState) => store.workExperience);

    useEffect(() => {
        dispatch(countryFetch.getAll());
        // dispatch(cultureFetch.getAll());
        dispatch(dietFetch.getAll());
        dispatch(educationFetch.getAll());
        dispatch(genderFetch.getAll());
        dispatch(hobbyFetch.getAll());
        // dispatch(industryFetch.getAll());
        dispatch(languageFetch.getAll());
        dispatch(projectFetch.getAll());
        dispatch(religionFetch.getAll());
        dispatch(workExperienceFetch.getAll());
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
                <label>
                    Wann wurdest du geboren?
                    <input
                        type="number"
                        min="1900"
                        max="2022"
                        onInput={(e: ChangeEvent<HTMLInputElement>) => updateProfileField('birthyear', e.target.valueAsNumber)}
                    />
                </label>
                <Dropdown
                    options={project.items}
                    label="In welchem Projekt arbeitest du derzeit?"
                    onChange={(value) => updateProfileField('project', value)}
                />

                <Dropdown
                    options={genders.items}
                    label="Wähle ein Geschlecht?"
                    onChange={(value) => updateProfileField('gender', value)}
                />

                <Dropdown
                    options={countries.items}
                    label="Was ist dein Herkunftsland?"
                    onChange={(value) => updateProfileField('country', value)}
                />

                <Dropdown
                    options={languages.items}
                    label="Was ist deine Muttersprache?"
                    onChange={(value) => updateProfileField('motherTongue', value)}
                />

                <Dropdown
                    options={religions.items}
                    label="An welche Religion glaubst du?"
                    onChange={(value) => updateProfileField('religion', value)}
                />

                <Dropdown
                    options={workExperience.items}
                    label="Wie viele Jahre Berufserfahrung hast du schon gesammelt?"
                    onChange={(value) => updateProfileField('workExperience', value)}
                />

                <Dropdown
                    options={hobbies.items}
                    label="Was hast du für ein Hobby?"
                    onChange={(value) => updateProfileField('hobbies', value)}
                />

                <Dropdown
                    options={educations.items}
                    label="Welchen Bildungsweg hast du bisher bestritten?"
                    onChange={(value) => updateProfileField('education', value)}
                />

                <Dropdown
                    options={diets.items}
                    label="Was ist dein Herkunftsland?"
                    onChange={(value) => updateProfileField('diet', value)}
                />

                <Button
                    disabled={!isValid()}
                    label="Profil anlegen"
                    type="submit"
                />
            </form>
        </div>
    );
};
