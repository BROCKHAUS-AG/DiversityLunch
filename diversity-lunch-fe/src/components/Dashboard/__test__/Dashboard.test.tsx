import { render, screen } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import { Provider, useDispatch } from 'react-redux';
import { FC, useEffect } from 'react';
import * as fetcher from '../../../utils/fetch.utils';
import { mockedFetchGetAccount, mockedFetchGetAccountByRole } from '../../../__global_test_data__/fetch';
import { Dashboard } from '../Dashboard';
import { Role } from '../../../model/Role';
import { APP_STORE } from '../../../store/Store';
import { loadProfile } from '../../../data/profile/profile.actions';
import { profileData } from '../../../__global_test_data__/data';
import { ProfileOverview } from '../../Profile/ProfileOverview';
import { loadAccount } from '../../../data/account/account.actions';
import { Account } from '../../../types/Account';

const AccountLoader: FC = () => {
    const dispatch = useDispatch();
    useEffect(() => { dispatch(loadAccount); }, []);
    return (
        <Dashboard />
    );
};

const renderContainer = (role: Role) => {
    jest.spyOn(fetcher, 'authenticatedFetchGet')
        .mockImplementation(mockedFetchGetAccount(Role.STANDARD));
    render(
        <BrowserRouter>
            <Provider store={APP_STORE}>
                <AccountLoader />
            </Provider>
        </BrowserRouter>,
    );
};
describe('Dashboard', () => {
    it('should hide admin panel when user has a standard account', async () => {
        renderContainer(Role.STANDARD);
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGetAccount(Role.STANDARD));
        const iconElement = await screen.findByAltText('admin icon');
        expect(iconElement).toBeFalsy();
    });
    it('should show admin panel when user has a admin account', () => {
        renderContainer(Role.ADMIN);
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGetAccount(Role.ADMIN));
        const linkElement = screen.getByAltText('admin icon');
        expect(linkElement).toBeInTheDocument();
    });
});
