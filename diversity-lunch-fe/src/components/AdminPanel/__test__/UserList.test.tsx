import React, { FC } from 'react';
import { fireEvent, render, screen } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import { act } from 'react-dom/test-utils';
import { UserList } from '../user-list';

import {
    mockedAssignAdminRole,
    mockedFetchGetAccounts,
    mockedFetchGetProfiles, mockedRevokeAdminRole,
} from '../../../__global_test_data__/fetch';
import { APP_STORE } from '../../../store/Store';
import * as accounts from '../../../data/accounts/accounts-fetch';
import * as profiles from '../../../data/profiles/profiles-fetch';

const WrapperComponent: FC = () => (
    <UserList />
);
describe('UserList', () => {
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

    it('Does let an Admin promote a STANDARD_ROLE to ADMIN_Role', async () => {
        const promoteButton = await screen.findByText('+');
        act(() => {
            fireEvent.click(promoteButton);
        });
        const standardUser = await screen.findAllByText('ADMIN');
        expect(standardUser.length === 2);
    });

    it('Does let an Admin demote an ADMIN_ROLE to STANDARD_Role', async () => {
        const demoteButtons = await screen.findAllByText('-');
        act(() => {
            fireEvent.click(demoteButtons[0]);
        });
        const standardUser = await screen.findAllByText('STANDARD');
        expect(standardUser.length === 1);
    });
});
