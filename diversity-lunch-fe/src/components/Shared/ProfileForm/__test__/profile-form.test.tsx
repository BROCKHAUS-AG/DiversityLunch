import { render, screen } from '@testing-library/react';
import { Provider } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import { APP_STORE } from '../../../../store/Store';
import { ProfileForm } from '../profile-form';
import {
    countryData, dietData, educationData,
    genderData, hobbyData,
    languageData,
    profileData,
    projectData,
    religionData,
    workExperienceData,
} from '../../../../__global_test_data__/data';
import * as fetcher from '../../../../utils/fetch.utils';
import { mockedFetchGet } from '../../../../__global_test_data__/fetch';
import { Profile } from '../../../../model/Profile';

describe('Profile form', () => {
    const renderContainer = (component : JSX.Element) => render(
        <BrowserRouter>
            <Provider store={APP_STORE}>
                {component}
            </Provider>
        </BrowserRouter>,
    ).container;

    beforeEach(() => {

    });

    it('should disable the button if checkValidity returns false', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGet);
        renderContainer(<ProfileForm checkValidity={() => false} onSubmit={() => {}} />);

        const result = await screen.findByText('Speichern');

        expect(result).toBeDisabled();
    });

    it('should enable the button if checkValidity returns true', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGet);
        renderContainer(<ProfileForm checkValidity={() => true} onSubmit={() => {}} />);

        const result = await screen.findByText('Speichern');

        expect(result).toBeEnabled();
    });

    it('should call onSubmit after button was clicked', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGet);
        renderContainer(<ProfileForm checkValidity={() => true} onSubmit={(p) => expect(p).toBeTruthy()} />);

        const button = await screen.findByText('Speichern');
        button.click();
    });

    it('should call onSubmit with the data inserted into the input elements, after button was clicked', async () => {
        const INSERTED_BIRTH_YEAR = 1997;
        const INSERTED_PROJECT_DESCRIPTOR = projectData[0].descriptor;
        const INSERTED_GENDER_DESCRIPTOR = genderData[0].descriptor;
        const INSERTED_COUNTRY_DESCRIPTOR = countryData[0].descriptor;
        const INSERTED_LANGUAGE_DESCRIPTOR = languageData[0].descriptor;
        const INSERTED_RELIGION_DESCRIPTOR = religionData[0].descriptor;
        const INSERTED_WORK_EXPERIENCE_DESCRIPTOR = workExperienceData[0].descriptor;
        const INSERTED_HOBBY_DESCRIPTOR = hobbyData[0].descriptor;
        const INSERTED_EDUCATION_DESCRIPTOR = educationData[0].descriptor;
        const INSERTED_DIET_DESCRIPTOR = dietData[0].descriptor;

        const expectInputData = (p: Partial<Profile>) => {
            expect(p.birthYear).toEqual(INSERTED_BIRTH_YEAR);
            expect(p.project?.descriptor).toEqual(INSERTED_PROJECT_DESCRIPTOR);
            expect(p.gender?.descriptor).toEqual(INSERTED_GENDER_DESCRIPTOR);
            expect(p.originCountry?.descriptor).toEqual(INSERTED_COUNTRY_DESCRIPTOR);
            expect(p.motherTongue?.descriptor).toEqual(INSERTED_LANGUAGE_DESCRIPTOR);
            expect(p.religion?.descriptor).toEqual(INSERTED_RELIGION_DESCRIPTOR);
            expect(p.workExperience?.descriptor).toEqual(INSERTED_WORK_EXPERIENCE_DESCRIPTOR);
            expect(p.hobby?.descriptor).toEqual(INSERTED_HOBBY_DESCRIPTOR);
            expect(p.education?.descriptor).toEqual(INSERTED_EDUCATION_DESCRIPTOR);
            expect(p.diet?.descriptor).toEqual(INSERTED_DIET_DESCRIPTOR);
        };
        jest.spyOn(fetcher, 'authenticatedFetchGet').mockImplementation(mockedFetchGet);
        renderContainer(<ProfileForm checkValidity={() => true} onSubmit={expectInputData} />);

        const birthYearElement = await screen.findByLabelText('Geburtsjahr') as HTMLInputElement | null;
        expect(birthYearElement).toBeVisible();
        if (birthYearElement) birthYearElement.value = `${INSERTED_BIRTH_YEAR}`;

        const projectDropdown = await screen.findByLabelText('Projekt') as HTMLInputElement | null;
        expect(projectDropdown).toBeVisible();
        if (projectDropdown) projectDropdown.value = INSERTED_PROJECT_DESCRIPTOR;

        const genderDropdown = await screen.findByLabelText('Geschlecht') as HTMLInputElement | null;
        expect(genderDropdown).toBeVisible();
        if (genderDropdown) genderDropdown.value = INSERTED_GENDER_DESCRIPTOR;

        const originCountryDropdown = await screen.findByLabelText('Herkunftsland') as HTMLInputElement | null;
        expect(originCountryDropdown).toBeVisible();
        if (originCountryDropdown) originCountryDropdown.value = INSERTED_COUNTRY_DESCRIPTOR;

        const motherTongueDropdown = await screen.findByLabelText('Muttersprache') as HTMLInputElement | null;
        expect(motherTongueDropdown).toBeVisible();
        if (motherTongueDropdown) motherTongueDropdown.value = INSERTED_LANGUAGE_DESCRIPTOR;

        const religionDropdown = await screen.findByLabelText('Religion') as HTMLInputElement | null;
        expect(religionDropdown).toBeVisible();
        if (religionDropdown) religionDropdown.value = INSERTED_RELIGION_DESCRIPTOR;

        const workExperienceDropdown = await screen.findByLabelText('Berufserfahrung') as HTMLInputElement | null;
        expect(workExperienceDropdown).toBeVisible();
        if (workExperienceDropdown) workExperienceDropdown.value = INSERTED_WORK_EXPERIENCE_DESCRIPTOR;

        const hobbyDropdown = await screen.findByLabelText('Hobby') as HTMLInputElement | null;
        expect(hobbyDropdown).toBeVisible();
        if (hobbyDropdown) hobbyDropdown.value = INSERTED_HOBBY_DESCRIPTOR;

        const educationDropdown = await screen.findByLabelText('Bildungsweg') as HTMLInputElement | null;
        expect(educationDropdown).toBeVisible();
        if (educationDropdown) educationDropdown.value = INSERTED_EDUCATION_DESCRIPTOR;

        const dietDropdown = await screen.findByLabelText('Ernährung') as HTMLInputElement | null;
        expect(dietDropdown).toBeVisible();
        if (dietDropdown) dietDropdown.value = INSERTED_DIET_DESCRIPTOR;

        const button = await screen.findByText('Speichern');
        setTimeout(() => button.click(), 0);
    });

    it('should  fill in the initialProfile props values as input default values', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet').mockImplementation(mockedFetchGet);
        renderContainer(<ProfileForm initialProfile={profileData[0]} checkValidity={() => true} onSubmit={() => {}} />);

        const birthYearElement = await screen.findByLabelText('Geburtsjahr') as HTMLInputElement | null;
        expect(birthYearElement).toHaveValue(profileData[0].birthYear);

        const projectDropdown = await screen.findByLabelText('Projekt') as HTMLInputElement | null;
        expect(projectDropdown).toHaveValue(profileData[0].project.descriptor);

        const genderDropdown = await screen.findByLabelText('Geschlecht') as HTMLInputElement | null;
        expect(genderDropdown).toHaveValue(profileData[0].gender.descriptor);

        const originCountryDropdown = await screen.findByLabelText('Herkunftsland') as HTMLInputElement | null;
        expect(originCountryDropdown).toHaveValue(profileData[0].originCountry.descriptor);

        const motherTongueDropdown = await screen.findByLabelText('Muttersprache') as HTMLInputElement | null;
        expect(motherTongueDropdown).toHaveValue(profileData[0].motherTongue.descriptor);

        const religionDropdown = await screen.findByLabelText('Religion') as HTMLInputElement | null;
        expect(religionDropdown).toHaveValue(profileData[0].religion.descriptor);

        const workExperienceDropdown = await screen.findByLabelText('Berufserfahrung') as HTMLInputElement | null;
        expect(workExperienceDropdown).toHaveValue(profileData[0].workExperience.descriptor);

        const hobbyDropdown = await screen.findByLabelText('Hobby') as HTMLInputElement | null;
        expect(hobbyDropdown).toHaveValue(profileData[0].hobby.descriptor);

        const educationDropdown = await screen.findByLabelText('Bildungsweg') as HTMLInputElement | null;
        expect(educationDropdown).toHaveValue(profileData[0].education.descriptor);

        const dietDropdown = await screen.findByLabelText('Ernährung') as HTMLInputElement | null;
        expect(dietDropdown).toHaveValue(profileData[0].diet.descriptor);
    });

    it('should propagate initial id, name and email values when onSubmit is called', async () => {
        const expectInputData = (p: Partial<Profile>) => {
            expect(p.id).toEqual(profileData[0].id);
            expect(p.email).toEqual(profileData[0].email);
            expect(p.name).toEqual(profileData[0].name);
        };
        jest.spyOn(fetcher, 'authenticatedFetchGet').mockImplementation(mockedFetchGet);
        renderContainer(<ProfileForm initialProfile={profileData[0]} checkValidity={() => true} onSubmit={expectInputData} />);

        const button = await screen.findByText('Speichern');
        setTimeout(() => button.click(), 0);
    });
});
