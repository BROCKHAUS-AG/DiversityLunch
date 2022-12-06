import { render, screen } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import { Provider, useDispatch } from 'react-redux';
import { FC, useEffect } from 'react';
import * as fetcher from '../../../utils/fetch.utils';
import { mockedFetchGetAccount } from '../../../__global_test_data__/fetch';
import { Dashboard } from './dashboard';
import { Role } from '../../../model/Role';
import { APP_STORE } from '../../../data/app-store';
import { loadAccount } from '../../../data/account/account.actions';

const AccountLoader: FC = () => {
    const dispatch = useDispatch();
    useEffect(() => { dispatch(loadAccount); }, []);
    return (
        <Dashboard />
    );
};

const renderContainer = (role: Role) => {
    jest.spyOn(fetcher, 'authenticatedFetchGet')
        .mockImplementation(mockedFetchGetAccount(role));
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
        expect(await screen.queryByAltText('admin icon')).toBeNull();
    });

    it('should show admin panel when user has a admin account', async () => {
        renderContainer(Role.ADMIN);
        expect(await screen.findByAltText('admin icon')).toBeInTheDocument();
    });

    it('should show admin panel when user has a azure admin account', async () => {
        renderContainer(Role.AZURE_ADMIN);
        expect(await screen.findByAltText('admin icon')).toBeInTheDocument();
    });
});
