import { Provider, useDispatch, useSelector } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import { render, screen } from '@testing-library/react';
import React, { FC, useEffect } from 'react';
import { APP_STORE, AppStoreState } from '../../../store/Store';
import { projectFetch } from '../../../data/project/project-fetch';
import { OptionsList } from '../OptionsList';
import * as fetcher from '../../../utils/fetch.utils';
import { mockedFetchGetProfile } from '../../../__global_test_data__/fetch';
import { loadProfile } from '../../../data/profile/profile.actions';
import { profileData } from '../../../__global_test_data__/data';
import { ProfileOverview } from '../../Profile/ProfileOverview';

const WrapperComponent: FC = () => {
    const projectState = useSelector((store: AppStoreState) => store.project);
    const dispatch = useDispatch();
    dispatch(projectFetch.getAll());
    return (
        <OptionsList
            state={projectState}
            fetch={projectFetch}
            title="Projektliste anpassen"
            addButtonLabel="Projekt hinzufügen"
        />
    );
};

describe('OptionsList', () => {
    let container : HTMLElement;

    beforeEach(() => {
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGetProfile);
        ({ container } = render(
            <BrowserRouter>
                <Provider store={APP_STORE}>
                    <WrapperComponent />
                </Provider>
            </BrowserRouter>,
        ));
    });

    it('should render the component without crashing', async () => {
        const result = await screen.findByText('Projektliste anpassen');
        expect(result).toBeInTheDocument();
    });
});
