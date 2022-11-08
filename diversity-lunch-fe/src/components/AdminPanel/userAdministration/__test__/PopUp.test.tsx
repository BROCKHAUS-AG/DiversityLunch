import { render } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import React from 'react';
import { APP_STORE } from '../../../../store/Store';
import { mockedFetchGetProfile } from '../../../../__global_test_data__/fetch';
import * as fetcher from '../../../../utils/fetch.utils';
import { PopUp } from '../PopUp';

describe('Pop Up', () => {
    let popUpContainer;
    beforeEach(() => {
        popUpContainer = render(
            <BrowserRouter>
                <Provider store={APP_STORE}>
                    <PopUp />
                </Provider>
            </BrowserRouter>,
        );
    });
    it('should display a popup', async () => {
        renderContainer(Role.STANDARD);
        const errorMessage = await screen.findByText('Du bist kein Admin');
        expect(errorMessage).toBeInTheDocument();
    });

    it('should not display an error message when the user is an admin', async () => {
        renderContainer(Role.ADMIN);
        const errorMessage = await screen.queryByText('Du bist kein Admin');
        expect(errorMessage).toBeNull();
    });

    it('should not display an error message when the user is an azure admin', async () => {
        renderContainer(Role.AZURE_ADMIN);
        const errorMessage = await screen.queryByText('Du bist kein Admin');
        expect(errorMessage).toBeNull();
    });
});
