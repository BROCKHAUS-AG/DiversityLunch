import React, { FC } from 'react';
import { fireEvent, render, screen } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import { UserList } from '../user-list';
import * as fetcher from '../../../utils/fetch.utils';
import {
    mockedFetchGetAccounts,
    mockedFetchGetProfile,
    mockedFetchGetProfiles,
} from '../../../__global_test_data__/fetch';
import { APP_STORE } from '../../../store/Store';

const WrapperComponent: FC = () => (
    <UserList />
);
describe('UserList', () => {
    let container : HTMLElement;

    beforeEach(() => {
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGetProfile);
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGetAccounts);
        jest.spyOn(fetcher, 'authenticatedFetchGet')
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
        const promoteButton = await screen.findAllByText('+');
        fireEvent.click(promoteButton[1]);
        const standardUser = await screen.getByDisplayValue('STANDARD');
        expect(standardUser).not.toBeInTheDocument();
    });

    it('Does let an Admin promote an ADMIN_ROLE to ADMIN_Role', async () => {
        const promoteButton = await screen.queryAllByText('+');
        fireEvent.click(promoteButton[1]);
        const standardUser = await screen.getByDisplayValue('STANDARD');
        expect(standardUser).not.toBeInTheDocument();
    });

    it('Does let an Admin demote an ADMIN_ROLE to STANDARD_Role', async () => {
        const demoteButton = await screen.queryAllByText('-');
        fireEvent.click(demoteButton[1]);
        const standardUser = await screen.getByDisplayValue('');
        expect(standardUser).not.toBeInTheDocument();
    });

    it('Does not let an Admin promote an AZURE_ADMIN_ROLE to ADMIN_Role', async () => {
        const promoteButton = await screen.queryAllByText('+');
        expect(promoteButton[2]).not.toBeInTheDocument();
    });

    it('Does not let an Admin demote an AZURE_ADMIN_ROLE to STANDARD_Role', async () => {
        const demoteButton = await screen.queryAllByText('-');
        expect(demoteButton[2]).not.toBeInTheDocument();
    });
});
