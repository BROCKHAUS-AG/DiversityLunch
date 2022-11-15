import { fireEvent, render, screen } from '@testing-library/react';
import { act } from 'react-dom/test-utils';
import React, { FC, useEffect } from 'react';

import { BrowserRouter } from 'react-router-dom';
import { Provider, useDispatch } from 'react-redux';
import * as fetcher from '../../../utils/fetch.utils';
import { APP_STORE } from '../../../store/Store';
import { loadAccount } from '../../../data/account/account.actions';
import { mockedFetchGetUsableAccount } from '../../../__global_test_data__/fetch';
import { VoucherUpload } from '../VoucherUpload';

describe('VoucherUpload', () => {
    const AccountLoader : FC = () => {
        const dispatch = useDispatch();
        useEffect(() => { dispatch(loadAccount); }, []);
        return (
            <VoucherUpload />
        );
    };
    const renderContainer = () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGetUsableAccount());

        render(
            <BrowserRouter>
                <Provider store={APP_STORE}>
                    <AccountLoader />
                </Provider>
            </BrowserRouter>,
        );
    };
    beforeEach(() => {
        renderContainer();
    });
    afterEach(() => {
        jest.clearAllMocks();
    });

    it('Should upload the CSV File', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchPostCsv')
            .mockImplementation(async () => new Response('', { status: 200, statusText: 'OK' }));
        const accessCodeButton = await screen.findByText('Upload');
        act(() => {
            fireEvent.click(accessCodeButton);
        });
        const voucherCode = await screen.findByText('Der Upload war erfolgreich!');
        expect(voucherCode).toBeInTheDocument();
    });
});
