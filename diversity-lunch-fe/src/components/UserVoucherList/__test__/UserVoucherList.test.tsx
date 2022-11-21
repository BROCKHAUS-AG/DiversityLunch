import { render, screen } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import { Provider, useDispatch } from 'react-redux';
import React, { FC, useEffect } from 'react';
import * as fetch from '../../../utils/fetch.utils';
import { UserVoucherList } from '../UserVoucherList';
import { APP_STORE } from '../../../store/Store';
import { accountStandardData } from '../../../__global_test_data__/data';
import { loadAccount } from '../../../data/account/account.actions';
import { mockedFetchGetUserVouchers } from '../../../__global_test_data__/fetch';

const WrapperComponent: FC = () => {
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(loadAccount);
    }, []);
    return <UserVoucherList />;
};

describe('UserVoucherList', () => {
    beforeEach(() => {
        jest.spyOn(fetch, 'authenticatedFetchGet')
            .mockReturnValueOnce(Promise.resolve(new Response(JSON.stringify(accountStandardData), { status: 200, statusText: 'ok' })));
        render(
            <BrowserRouter>
                <Provider store={APP_STORE}>
                    <WrapperComponent />
                </Provider>
            </BrowserRouter>,
        );
    });

    it('should render 3 vouchers', async () => {
        jest.spyOn(fetch, 'authenticatedFetchGet').mockImplementation(mockedFetchGetUserVouchers);
        const element1 = await screen.findByText('abcdef');
        const element2 = await screen.findByText('abccba');
        const element3 = await screen.findByText('1a2b3c');
        expect(element1).toBeInTheDocument();
        expect(element2).toBeInTheDocument();
        expect(element3).toBeInTheDocument();
    });
});
