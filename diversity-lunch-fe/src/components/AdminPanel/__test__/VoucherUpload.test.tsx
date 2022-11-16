import {
    cleanup, fireEvent, render, screen,
} from '@testing-library/react';
import { act } from 'react-dom/test-utils';
import React, { FC, useEffect } from 'react';

import { BrowserRouter } from 'react-router-dom';
import { Provider, useDispatch } from 'react-redux';
import userEvent from '@testing-library/user-event';
import * as fetcher from '../../../utils/fetch.utils';
import { APP_STORE } from '../../../store/Store';
import { loadAccount } from '../../../data/account/account.actions';
import { VoucherUpload } from '../VoucherUpload';
import { mockedFetchGetUsableAccount } from '../../../__global_test_data__/fetch';

describe('VoucherUpload', () => {
    const AccountLoader : FC = () => {
        const dispatch = useDispatch();
        useEffect(() => { dispatch(loadAccount); }, []);
        return (
            <VoucherUpload />
        );
    };
    const renderContainer = () => {
        render(
            <BrowserRouter>
                <Provider store={APP_STORE}>
                    <AccountLoader />
                </Provider>
            </BrowserRouter>,
        );
    };
    afterEach(() => {
        jest.clearAllMocks();
        cleanup();
    });

    it('Should upload the CSV File', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGetUsableAccount());
        jest.spyOn(fetcher, 'authenticatedFetchPostCsv')
            .mockImplementation(async () => new Response('', { status: 200, statusText: 'OK' }));
        renderContainer();
        const fakeFile = new File(['hello'], 'hello.csv', { type: '.csv' });
        const fileInput = await screen.findByPlaceholderText('Datei hier reinziehen');
        act(() => {
            userEvent.upload(fileInput, fakeFile);
        });
        const accessCodeButton = await screen.findByText('Upload');
        act(() => {
            fireEvent.click(accessCodeButton);
        });
        const voucherCode = await screen.findByText('Der Upload war erfolgreich!');
        expect(voucherCode).toBeInTheDocument();
    });
    it('Should dispatch Error', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGetUsableAccount());
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(async () => new Response('', { status: 403, statusText: 'NOT_FOUND' }));
        renderContainer();

        const uploadButton = await screen.findByText('Upload');
        act(() => {
            fireEvent.click(uploadButton);
        });
        const message = await screen.findByText('Ein Fehler ist aufgetreten');
        expect(message).toBeInTheDocument();
    });
    it('should display the correct amount of vouchers', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGetUsableAccount());
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(async () => new Response('2', { status: 200, statusText: 'ok' }));
        renderContainer();
        const message = await screen.findByText('2 Gutschein(e) vorhanden.');
        expect(message).toBeInTheDocument();
    });
});
