import React, { FC, useEffect } from 'react';
import { fireEvent, render, screen } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import { Provider, useDispatch } from 'react-redux';
import { UserList } from '../user-list';

import {
    mockedFetchGetAccounts,
    mockedFetchGetProfiles,
} from '../../../__global_test_data__/fetch';
import { APP_STORE } from '../../../store/Store';
import { loadAccount } from '../../../data/account/account.actions';
import * as accounts from '../../../data/accounts/accounts-fetch';
import * as profiles from '../../../data/profiles/profiles-fetch';

const WrapperComponent: FC = () => {
    const dispatch = useDispatch();
    useEffect(() => { dispatch(loadAccount); }, []);
    return (
        <UserList />
    );
};
describe('UserList', () => {
    let container : HTMLElement;

    beforeEach(() => {
        jest.spyOn(accounts, 'getAllAccounts')
            .mockImplementation(mockedFetchGetAccounts);
        jest.spyOn(profiles, 'getAllProfiles')
            .mockImplementation(mockedFetchGetProfiles);

        ({ container } = render(
            <BrowserRouter>
                <Provider store={APP_STORE}>
                    <WrapperComponent />
                </Provider>
            </BrowserRouter>,
        ));
    });

    it('Does let an Admin promote a STANDARD_ROLE to ADMIN_Role', async () => {
        const promoteButton = await screen.findByText('+');
        fireEvent.click(promoteButton);
        const standardUser = await screen.getByDisplayValue('STANDARD');
        expect(standardUser).not.toBeInTheDocument();
    });

    it('Does let an Admin promote an ADMIN_ROLE to ADMIN_Role', async () => {
        const promoteButton = await screen.findAllByText('+');
        fireEvent.click(promoteButton[1]);
        const standardUser = await screen.getByDisplayValue('STANDARD');
        expect(standardUser).not.toBeInTheDocument();
    });

    it('Does let an Admin demote an ADMIN_ROLE to STANDARD_Role', async () => {
        const demoteButton = await screen.findAllByText('-');
        fireEvent.click(demoteButton[1]);
        const standardUser = await screen.getByDisplayValue('');
        expect(standardUser).not.toBeInTheDocument();
    });

    it('Does not let an Admin promote an AZURE_ADMIN_ROLE to ADMIN_Role', async () => {
        const promoteButton = await screen.findAllByText('+');
        expect(promoteButton[2]).not.toBeInTheDocument();
    });

    it('Does not let an Admin demote an AZURE_ADMIN_ROLE to STANDARD_Role', async () => {
        const demoteButton = await screen.findAllByText('-');
        expect(demoteButton[2]).not.toBeInTheDocument();
    });
});
