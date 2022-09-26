import { render, screen } from '@testing-library/react';
import { Provider } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import { APP_STORE } from '../../../../store/Store';
import { ProfileForm } from '../profile-form';
import {
    countryData,
    genderData,
    languageData,
    profileData,
    projectData,
    religionData,
    workExperienceData
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
        renderContainer(<ProfileForm initialProfile={profileData[0]} checkValidity={() => false} onSubmit={() => {}} />);

        const result = await screen.findByText('Speichern');

        expect(result).toBeDisabled();
    });

    it('should enable the button if checkValidity returns true', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGet);
        renderContainer(<ProfileForm initialProfile={profileData[0]} checkValidity={() => true} onSubmit={() => {}} />);

        const result = await screen.findByText('Speichern');

        expect(result).toBeEnabled();
    });

    it('should call onSubmit after button was clicked', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGet);
        renderContainer(<ProfileForm initialProfile={profileData[0]} checkValidity={() => true} onSubmit={(p) => expect(p).toBeTruthy()} />);

        const button = await screen.findByText('Speichern');
        button.click();
    });

    it('should call onSubmit with the data inserted into the input elements, after button was clicked', async () => {
        const INSERTED_BIRTHYEAR = 1997;
        const INSERTED_PROJECT_DESCRIPTOR = projectData[0].descriptor;
        const INSERTED_GENDER_DESCRIPTOR = genderData[0].descriptor;
        const INSERTED_COUNTRY_DESCRIPTOR = countryData[0].descriptor;
        const INSERTED_LANGUAGE_DESCRIPTOR = languageData[0].descriptor;
        const INSERTED_RELIGION_DESCRIPTOR = religionData[0].descriptor;
        const INSERTED_WORK_EXPERIENCE_DESCRIPTOR = workExperienceData[0].descriptor;
        const exptectInputData = (p: Partial<Profile>) => {
            expect(p.birthYear).toEqual(INSERTED_BIRTHYEAR);
            expect(p.project?.descriptor).toEqual(INSERTED_PROJECT_DESCRIPTOR);
        };
        jest.spyOn(fetcher, 'authenticatedFetchGet').mockImplementation(mockedFetchGet);
        renderContainer(<ProfileForm checkValidity={() => true} onSubmit={exptectInputData} />);

        const birthYearElement = await screen.findByLabelText('Geburtsjahr') as HTMLInputElement | null;
        expect(birthYearElement).toBeVisible();
        if (birthYearElement) birthYearElement.value = `${INSERTED_BIRTHYEAR}`;

        const projectDropdown = await screen.findByLabelText('Projekt') as HTMLInputElement | null;
        expect(projectDropdown).toBeVisible();
        if (projectDropdown) projectDropdown.value = INSERTED_PROJECT_DESCRIPTOR;

        const button = await screen.findByText('Speichern');
        setTimeout(() => button.click(), 0);
    });
});
