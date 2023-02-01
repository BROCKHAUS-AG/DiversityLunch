import { render, screen } from '@testing-library/react';
import { FC, useEffect } from 'react';

import { Provider, useDispatch } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import { ProfileOverview } from './profile-overview';
import { APP_STORE } from '../../../data/app-store';
import * as fetcher from '../../../utils/fetch.utils';
import { mockedFetchGetProfile } from '../../../__global_test_data__/fetch';
import { loadProfile } from '../../../data/profile/profile.actions';
import { profileData } from '../../../__global_test_data__/data';

const ProfileLoader: FC = () => {
    const dispatch = useDispatch();
    useEffect(() => { dispatch(loadProfile(profileData[0].id)); });
    return (
        <ProfileOverview />
    );
};

const Container = (
    <BrowserRouter>
        <Provider store={APP_STORE}>
            <ProfileLoader />
        </Provider>
    </BrowserRouter>
);

let container: HTMLElement;

describe('ProfileOverview', () => {
    describe('with not yet loaded data', () => {
        beforeEach(() => {
            jest.spyOn(fetcher, 'authenticatedFetchGet')
                .mockReturnValue(new Promise(() => {
                }));
            ({ container } = render(Container));
        });

        it('render LoadingAnimation if no profile is loaded yet', () => {
            const loadingAnimation = container.querySelector('.animation');
            expect(loadingAnimation).toBeVisible();
        });
    });

    describe('with loaded data', () => {
        beforeEach(() => {
            jest.spyOn(fetcher, 'authenticatedFetchGet')
                .mockImplementation(mockedFetchGetProfile);
            ({ container } = render(Container));
        });

        it('correct name is shown in the greeting on top of the page', async () => {
            const result = await screen.findByText(profileData[0].name);

            expect(result)
                .toBeInTheDocument();
        });
    });
});
