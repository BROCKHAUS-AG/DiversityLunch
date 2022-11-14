import { fireEvent, render, screen } from '@testing-library/react';
import { act } from 'react-dom/test-utils';
import React, { FC, useEffect, useState } from 'react';

import * as fetcher from '../../../utils/fetch.utils';
import { VoucherPanel } from '../VoucherPanel';
import { BrowserRouter } from 'react-router-dom';
import { Provider, useDispatch } from 'react-redux';
import { APP_STORE } from '../../../store/Store';
import { loadAccount } from '../../../data/account/account.actions';
import { mockedFetchGetAccount } from '../../../__global_test_data__/fetch';
import { Role } from '../../../model/Role';

describe('VoucherPanel', () => {
    const AccountLoader : FC = () => {
        const dispatch = useDispatch();
        useEffect(() => { dispatch(loadAccount); }, []);
        return (
            <VoucherPanel />
        );
    };
    const renderContainer = () => {
        /*const setState = jest.fn();
        const mockState : any = (initState : any) => [initState, setState];
        jest.spyOn(React, 'useState')
            .mockImplementation(mockState);*/
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
            .mockImplementation(async () => new Response('irgendwann mal code', {status: 200, statusText: 'OK' }));
        const accessCodeButton = await screen.findByText('FREISCHALTEN');
        act(() => {
            fireEvent.click(accessCodeButton);
        });
        const voucherCode = await screen.findByText('irgendwann mal code');
        expect(voucherCode).toBeInTheDocument();
    });

    /*
    it('Should display already claimed voucher code', async () => {
        const demoteButtons = await screen.findAllByText('-');
        act(() => {
            fireEvent.click(demoteButtons[0]);
        });
        const standardUser = await screen.findAllByText('STANDARD');
        expect(standardUser.length === 1);
    });
 */
});
