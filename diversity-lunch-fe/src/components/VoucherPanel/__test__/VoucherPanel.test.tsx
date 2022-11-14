import { fireEvent, render, screen } from '@testing-library/react';
import { act } from 'react-dom/test-utils';
import React, { FC, useEffect } from 'react';

import { BrowserRouter } from 'react-router-dom';
import { Provider, useDispatch } from 'react-redux';
import * as fetcher from '../../../utils/fetch.utils';
import { VoucherPanel } from '../VoucherPanel';
import { APP_STORE } from '../../../store/Store';
import { loadAccount } from '../../../data/account/account.actions';
import { mockedFetchGetUsableAccount } from '../../../__global_test_data__/fetch';

describe('VoucherPanel', () => {
    const AccountLoader : FC = () => {
        const dispatch = useDispatch();
        useEffect(() => { dispatch(loadAccount); }, []);
        return (
            <VoucherPanel />
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

    it('Should throw an warning if a voucher cannot be claimed', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchPut')
            .mockImplementation(async () => new Response('', { status: 403, statusText: 'NOT OK' }));
        const accessCodeButton = await screen.findByText('FREISCHALTEN');
        act(() => {
            fireEvent.click(accessCodeButton);
        });
        const voucherCode = await screen.findByText('Bei der Abfrage ist ein Fehler aufgetreten');
        expect(voucherCode).toBeInTheDocument();
    });

    it('Should display voucher code when rightfully claimed', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchPut')
            .mockImplementation(async () => new Response('1234', { status: 200, statusText: 'OK' }));
        const accessCodeButton = await screen.findByText('FREISCHALTEN');
        act(() => {
            fireEvent.click(accessCodeButton);
        });
        const voucherCode = await screen.findByText('1234');
        expect(voucherCode).toBeInTheDocument();
    });
});
