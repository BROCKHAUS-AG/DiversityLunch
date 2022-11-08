import { fireEvent, render, screen } from '@testing-library/react';
import { FC, useEffect } from 'react';
import { Provider, useDispatch } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import { loadAccount } from '../../../data/account/account.actions';
import { AdminPanel } from '../AdminPanel';
import { Role } from '../../../model/Role';
import * as fetcher from '../../../utils/fetch.utils';
import { mockedFetchGetAccount } from '../../../__global_test_data__/fetch';
import { APP_STORE } from '../../../store/Store';
import {Dispatch} from "@reduxjs/toolkit";

const AccountLoader: FC = () => {
    const dispatch = useDispatch();
    useEffect(() => { dispatch(loadAccount); }, []);
    return (
        <AdminPanel />
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

describe('Admin Panel', () => {
    it('should display an error message when the user is not an admin', async () => {
        renderContainer(Role.STANDARD);
        const errorMessage = await screen.findByText('Du bist kein Admin');
        expect(errorMessage).toBeInTheDocument();
    });

    it('should not display an error message when the user is an admin', async () => {
        renderContainer(Role.ADMIN);
        const errorMessage = await screen.queryByText('Du bist kein Admin');
        expect(errorMessage).toBeNull();
    });

    it('should not display an error message when the user is an azure admin', async () => {
        renderContainer(Role.AZURE_ADMIN);
        const errorMessage = await screen.queryByText('Du bist kein Admin');
        expect(errorMessage).toBeNull();
    });
    it('should display an message when clicking the Send Testmail button', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchPost').mockImplementation(async () => new Response(null, { status: 200, statusText: 'Erfolgreich' }));
        const button = await screen.queryAllByText('Testmail verschicken');
        fireEvent.click(button[0]);
        const errorMessage = await screen.queryByText('Erfolgreich gesendet');
        expect(errorMessage).toBeInTheDocument();
    });
});
