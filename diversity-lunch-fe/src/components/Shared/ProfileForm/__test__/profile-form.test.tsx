import { render, screen } from '@testing-library/react';
import { Provider } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import { APP_STORE } from '../../../../store/Store';
import { ProfileForm } from '../profile-form';
import { profileData } from '../../../../__global_test_data__/data';
import { isUpdatedProfile, isValidProfile } from '../../../../utils/validators/profile-validator';
import { Profile } from '../../../../model/Profile';
import * as fetcher from '../../../../utils/fetch.utils';
import { mockedFetchGet } from '../../../../__global_test_data__/fetch';

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
});
