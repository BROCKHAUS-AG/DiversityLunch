// styles
import '../../styles/component-styles/questions/questionSite.scss';
import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Redirect } from 'react-router-dom';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { Profile } from '../../types/Profile';
import '../../styles/component-styles/questions/dropdownQuestion.scss';

import { useGetUserInformation } from '../../hooks/authentication/get-userInfo.hook';
import { GenerateGenericDropdown } from './GenericDropdown';
import { createProfile } from '../../data/profile/profile.actions';
import { useHasProfile } from '../../hooks/profile/has-profile.hook';
import { LoadingAnimation } from '../Shared/LoadingAnimation';
import { Button } from '../General/Button/Button';

import { Diet } from '../../types/enums/diet.type';
import { Education } from '../../types/enums/education.type';
import { Gender } from '../../types/enums/gender.type';
import { Hobby } from '../../types/enums/hobby.type';
import { Project } from '../../types/enums/project.type';
import { Religion } from '../../types/enums/religion.type';

// Type options
import { DIET_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/diet-dropdown-options.const';
import {
    BIRTH_YEAR_DROPDOWN_OPTIONS,
} from '../../types/dropdownOptions/birth-year-dropdown-options.const';
import { GENDER_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/gender-dropdown-options';
import { RELIGION_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/religion-dropdown-options';
import {
    WORK_EXPERIENCE_DROPDOWN_OPTIONS,
} from '../../types/dropdownOptions/work-experience-dropdown-options';
import { PROJECT_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/project-dropdown-type';
import { EDUCATION_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/education-dropdown-options';
import { HOBBY_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/hobby-dropdown-options';
import { WorkExperience } from '../../types/enums/workexperience.type';
import { AppStoreState } from '../../store/Store';
import { ORIGIN_COUNTRY_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/origin-country-dropdown-options.type';
import { MOTHER_TONGUE_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/mother-tongue-options.const';
import { Country } from '../../types/enums/country.type';
import { Language } from '../../types/enums/language.type';
import { countryFetch } from '../../data/country/fetch-country';
import { Dropdown } from './Dropdown';

const REQUIRED_FIELDS: (keyof Profile)[] = [
    'birthYear',
    'project',
    'gender',
    'originCountry',
    'motherTongue',
    'religion',
    'hobby',
    'education',
    'workExperience',
    'diet',
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
        profile.name = firstName;
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

    const NumberDropdown = GenerateGenericDropdown<number>();
    const DietDropdown = GenerateGenericDropdown<Diet>();
    const GenderDropdown = GenerateGenericDropdown<Gender>();
    const OriginCountryDropdown = GenerateGenericDropdown<Country>();
    const MotherTongueDropdown = GenerateGenericDropdown<Language>();
    const ReligionDropdown = GenerateGenericDropdown<Religion>();
    const ProjectDropdown = GenerateGenericDropdown<Project>();
    const EducationDropdown = GenerateGenericDropdown<Education>();
    const HobbyDropdown = GenerateGenericDropdown<Hobby>();
    const WorkExperienceDropdown = GenerateGenericDropdown<WorkExperience>();

    const countries = useSelector((store: AppStoreState) => store.country);

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
                <NumberDropdown
                    label="Wann wurdest du geboren?"
                    currentValue={currentFormState.birthYear}
                    options={BIRTH_YEAR_DROPDOWN_OPTIONS}
                    onChange={(value) => updateProfileField('birthYear', value)}
                    placeholder="Geburtsjahr"
                />

                <ProjectDropdown
                    label="In welchem Projekt arbeitest du derzeit?"
                    options={PROJECT_DROPDOWN_OPTIONS}
                    onChange={((value) => updateProfileField('project', value))}
                    currentValue={currentFormState.project}
                    placeholder="Projekt"
                />

                <GenderDropdown
                    options={GENDER_DROPDOWN_OPTIONS}
                    label="Wähle ein Geschlecht"
                    currentValue={currentFormState.gender}
                    onChange={(value) => updateProfileField('gender', value)}
                    placeholder="Geschlecht"
                />

                <OriginCountryDropdown
                    options={ORIGIN_COUNTRY_DROPDOWN_OPTIONS}
                    label="Was ist dein Herkunftsland?"
                    currentValue={currentFormState.originCountry}
                    onChange={(value) => updateProfileField('originCountry', value)}
                    placeholder="Herkunftsland"
                />

                <MotherTongueDropdown
                    options={MOTHER_TONGUE_DROPDOWN_OPTIONS}
                    label="Was ist deine Muttersprache?"
                    onChange={(value) => updateProfileField('motherTongue', value)}
                    currentValue={currentFormState.motherTongue}
                    placeholder="Muttersprache"
                />

                <ReligionDropdown
                    label="An welche Religion glaubst du?"
                    options={RELIGION_DROPDOWN_OPTIONS}
                    currentValue={currentFormState.religion}
                    onChange={(value) => {
                        updateProfileField('religion', value);
                    }}
                    placeholder="Religion"
                />

                <WorkExperienceDropdown
                    label="Wie viele Jahre Berufserfahrung hast du schon gesammelt?"
                    currentValue={currentFormState.workExperience}
                    options={WORK_EXPERIENCE_DROPDOWN_OPTIONS}
                    onChange={(value) => updateProfileField('workExperience', value)}
                    placeholder="Berufserfahrung"
                />

                <HobbyDropdown
                    label="Was hast du für ein Hobby?"
                    options={HOBBY_DROPDOWN_OPTIONS}
                    onChange={(value) => updateProfileField('hobby', value)}
                    currentValue={currentFormState.hobby}
                    placeholder="Hobby"
                />

                <EducationDropdown
                    label="Welchen Bildungsweg hast du bisher bestritten?"
                    options={EDUCATION_DROPDOWN_OPTIONS}
                    onChange={((value) => {
                        updateProfileField('education', value);
                    })}
                    currentValue={currentFormState.education}
                    placeholder="Bildung"
                />

                <DietDropdown
                    options={DIET_DROPDOWN_OPTIONS}
                    label="Wie ernährst du dich?"
                    currentValue={currentFormState.diet}
                    onChange={(value) => updateProfileField('diet', value)}
                    placeholder="Ernährung"
                />

                <Dropdown reducer={countries} fetch={countryFetch} text="Was ist dein Herkunftsland?" />

                <Button
                    disabled={!isValid()}
                    label="Profil anlegen"
                    type="submit"
                />
            </form>
        </div>
    );
};
