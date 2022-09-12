import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';

import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { GenericCharacteristic } from './GenericCharacteristic';
import '../../styles/component-styles/profile/profile.scss';
import { Profile } from '../../types/Profile';
import { useGetUserInformation } from '../../hooks/authentication/get-userInfo.hook';
import { updateProfile } from '../../data/profile/profile.actions';
import { DIET_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/diet-dropdown-options.const';
import { Diet } from '../../types/enums/diet.type';
import { Gender } from '../../types/enums/gender.type';
import { GENDER_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/gender-dropdown-options';
import {
    BIRTH_YEAR_DROPDOWN_OPTIONS,
} from '../../types/dropdownOptions/birth-year-dropdown-options.const';
import { PROJECT_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/project-dropdown-type';
import { HOBBY_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/hobby-dropdown-options';
import { Religion } from '../../types/enums/religion.type';
import { RELIGION_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/religion-dropdown-options';
import {
    WORK_EXPERIENCE_DROPDOWN_OPTIONS,
} from '../../types/dropdownOptions/work-experience-dropdown-options';
import { Education } from '../../types/enums/education.type';
import { EDUCATION_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/education-dropdown-options';
import { Project } from '../../types/enums/project.type';
import { Hobby } from '../../types/enums/hobby.type';
import { WorkExperience } from '../../types/enums/workexperience.type';
import { shallowEquals } from '../../utils/equals.utils';
import { Button } from '../General/Button/Button';
import { MOTHER_TONGUE_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/mother-tongue-options.const';
import { ORIGIN_COUNTRY_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/origin-country-dropdown-options.type';
import { Country } from '../../types/enums/country.type';

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
    const DietCharacteristic = GenericCharacteristic<Diet>();
    const BirthYearCharacteristic = GenericCharacteristic<number>();
    const GenderCharacteristic = GenericCharacteristic<Gender>();
    const ProjectCharacteristic = GenericCharacteristic<Project>();
    const OriginCountryCharacteristic = GenericCharacteristic<Country>();
    const MotherTongueCharacteristic = GenericCharacteristic<string>();
    const HobbyCharacteristic = GenericCharacteristic<Hobby>();
    const ReligionCharacteristic = GenericCharacteristic<Religion>();
    const WorkExperienceCharacteristic = GenericCharacteristic<WorkExperience>();
    const EducationCharacteristics = GenericCharacteristic<Education>();

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

    const submit = React.useCallback((ev: React.FormEvent<HTMLFormElement>) => {
        ev.preventDefault();
        ev.stopPropagation();
        dispatch(updateProfile(currentFormState));
        history.push('/dashboard');
    }, [currentFormState, dispatch, history]);

    return (
      <section className="view">
          <div className="Profile-logo-container">
              <CloseSiteContainer />
              <DiversityIconContainer title="DEIN PROFIL" />
            </div>

          <div className="Profile-name-container">
              <h5 className="Profile-user-name">{fullName}</h5>
            </div>

          <form className="Profile-settings-container" onSubmit={submit}>

              <BirthYearCharacteristic
                  showingAttribute="GEBURTSJAHR"
                  currentValue={currentFormState.birthYear}
                  options={BIRTH_YEAR_DROPDOWN_OPTIONS}
                  attribute="birthYear"
                  changeCurrentFormState={updateProfileField}
                />

              <GenderCharacteristic
                  showingAttribute="GESCHLECHT"
                  currentValue={currentFormState.gender}
                  options={GENDER_DROPDOWN_OPTIONS}
                  attribute="gender"
                  changeCurrentFormState={updateProfileField}
                />

              <ProjectCharacteristic
                  showingAttribute="KUNDE"
                  options={PROJECT_DROPDOWN_OPTIONS}
                  attribute="project"
                  changeCurrentFormState={updateProfileField}
                  currentValue={currentFormState.project}
                />

              <OriginCountryCharacteristic
                  showingAttribute="HERKUNFTSLAND"
                  options={ORIGIN_COUNTRY_DROPDOWN_OPTIONS}
                  attribute="originCountry"
                  currentValue={currentFormState.originCountry}
                  changeCurrentFormState={updateProfileField}
                />

              <MotherTongueCharacteristic
                  showingAttribute="MUTTERSPRACHE"
                  attribute="motherTongue"
                  options={MOTHER_TONGUE_DROPDOWN_OPTIONS}
                  currentValue={currentFormState.motherTongue}
                  changeCurrentFormState={updateProfileField}
                />

              <HobbyCharacteristic
                  showingAttribute="HOBBY"
                  options={HOBBY_DROPDOWN_OPTIONS}
                  currentValue={currentFormState.hobby}
                  attribute="hobby"
                  changeCurrentFormState={updateProfileField}
                />

              <ReligionCharacteristic
                  showingAttribute="RELIGION"
                  options={RELIGION_DROPDOWN_OPTIONS}
                  currentValue={currentFormState.religion}
                  attribute="religion"
                  changeCurrentFormState={updateProfileField}
                />

              <WorkExperienceCharacteristic
                  showingAttribute="BERUFSERFAHRUNG (JAHRE)"
                  options={WORK_EXPERIENCE_DROPDOWN_OPTIONS}
                  currentValue={currentFormState.workExperience}
                  attribute="workExperience"
                  changeCurrentFormState={updateProfileField}
                />

              <EducationCharacteristics
                  showingAttribute="BILDUNGSWEG"
                  options={EDUCATION_DROPDOWN_OPTIONS}
                  currentValue={currentFormState.education}
                  attribute="education"
                  changeCurrentFormState={updateProfileField}
                />

              <DietCharacteristic
                  options={DIET_DROPDOWN_OPTIONS}
                  currentValue={currentFormState.diet}
                  changeCurrentFormState={updateProfileField}
                  attribute="diet"
                  showingAttribute="ERNÄHRUNGSWEISE"
                />

              <div className="Profile-button-container">
                  <Button type="submit" label="Speichern" disabled={!profileChanged} />
                </div>
            </form>
        </section>
    );
};
