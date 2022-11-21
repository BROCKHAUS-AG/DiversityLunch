import { render } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import React, { FC } from 'react';
import {
    mockedAssignAdminRole,
    mockedFetchGetAccounts,
    mockedFetchGetProfiles,
    mockedFetchGetUserVouchers,
    mockedRevokeAdminRole,
} from '../../../__global_test_data__/fetch';
import * as fetch from '../../../utils/fetch.utils';
import { UserVoucherList } from '../UserVoucherList';
import { APP_STORE } from '../../../store/Store';
import * as accounts from '../../../data/accounts/accounts-fetch';
import * as profiles from '../../../data/profiles/profiles-fetch';

const WrapperComponent: FC = () => (
    <UserVoucherList />
);

describe('UserVoucherList', () => {
    beforeEach(() => {
        jest.spyOn(accounts, 'getAllAccounts')
            .mockImplementation(mockedFetchGetAccounts);
        jest.spyOn(profiles, 'getAllProfiles')
            .mockImplementation(mockedFetchGetProfiles);
        jest.spyOn(accounts, 'assignAdminRole')
            .mockImplementation(mockedAssignAdminRole);
        jest.spyOn(accounts, 'revokeAdminRole')
            .mockImplementation(mockedRevokeAdminRole);
        render(
            <BrowserRouter>
                <Provider store={APP_STORE}>
                    <WrapperComponent />
                </Provider>
            </BrowserRouter>,
        );
    });

    it('should render 3 vouchers', async () => {
        jest.spyOn(fetch, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGetUserVouchers);
        const listElementCount = document.querySelector('ul')!.childElementCount;

        expect(listElementCount).toEqual(3);
    });
});
