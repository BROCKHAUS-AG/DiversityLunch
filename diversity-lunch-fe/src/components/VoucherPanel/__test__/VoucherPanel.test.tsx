import { fireEvent, render, screen } from '@testing-library/react';
import { act } from 'react-dom/test-utils';
import React, {FC, useEffect} from 'react';

import * as fetcher from '../../../utils/fetch.utils';
import { VoucherPanel } from '../VoucherPanel';
import {BrowserRouter} from "react-router-dom";
import {Provider, useDispatch} from "react-redux";
import {APP_STORE} from "../../../store/Store";

describe('VoucherPanel', () => {
    const accountLoader : FC = () => {
        const dispatch = useDispatch();
        useEffect(() => {
            dispatch(loadAccount)
        })
    }
    beforeEach(() => {
        const setState = jest.fn();
        const mockState : any = (initState : any) => [initState, setState];
        jest.spyOn(React, 'useState')
            .mockImplementation(mockState);

        render(
            <BrowserRouter>
                <Provider store={APP_STORE}>
                    <VoucherPanel />
                </Provider>
            </BrowserRouter>,
        );
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
        const voucherCode = await screen.findAllByText('Bei der Abfrage ist ein Fehler aufgetreten');
        expect(voucherCode.length === 1);
    });
/*
    it('Should display voucher code when rightfully claimed', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchPut')
            .mockImplementation(async () => new Response('irgendwann mal code'));
        const demoteButtons = await screen.findAllByText('-');
        act(() => {
            fireEvent.click(demoteButtons[0]);
        });
        const standardUser = await screen.findAllByText('STANDARD');
        expect(standardUser.length === 1);
    });

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
