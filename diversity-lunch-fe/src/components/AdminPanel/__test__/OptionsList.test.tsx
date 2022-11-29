import { Provider, useDispatch, useSelector } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import {
    fireEvent, render, screen,
} from '@testing-library/react';
import React, { FC } from 'react';
import { APP_STORE, AppStoreState } from '../../../data/app-store';
import { projectFetch } from '../../../data/project/project-fetch';
import { OptionsList } from '../OptionsList';
import * as fetcher from '../../../utils/fetch.utils';
import { mockedFetchGetProfile } from '../../../__global_test_data__/fetch';

const addButtonLabel: string = 'Projekt hinzufügen';

const WrapperComponent: FC = () => {
    const projectState = useSelector((store: AppStoreState) => store.project);
    const dispatch = useDispatch();
    dispatch(projectFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
    // TODO: Handle network and http errors properly tgohlisch 17.11.2022
    return (
        <OptionsList
            state={projectState}
            fetch={projectFetch}
            title="Projektliste anpassen"
            addButtonLabel={addButtonLabel}
        />
    );
};

const mockAuthenticatedFetchDeleteImpl = async () => new Response(JSON.stringify({ id: 9, descriptor: 'intern' }));

describe('OptionsList', () => {
    beforeEach(() => {
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGetProfile);
        jest.spyOn(fetcher, 'authenticatedFetchDelete')
            .mockImplementation(mockAuthenticatedFetchDeleteImpl);
        render(
            <BrowserRouter>
                <Provider store={APP_STORE}>
                    <WrapperComponent />
                </Provider>
            </BrowserRouter>,
        );
    });

    it('should render the correct title', async () => {
        const result = await screen.queryByText('Projektliste anpassen');
        expect(result).toBeInTheDocument();
    });

    it('should render the correct amount of inputs', async () => {
        const result = await screen.queryAllByRole('textbox');
        expect(result.length).toBe(3);
    });

    it('should render the correct options', async () => {
        const intern = await screen.getByDisplayValue('intern');
        const extern = await screen.getByDisplayValue('extern');
        expect(intern).toBeInTheDocument();
        expect(extern).toBeInTheDocument();
    });

    it('should not render the deleted element when remove button is clicked', async () => {
        const removeButton = await screen.queryAllByText('Löschen');
        fireEvent.click(removeButton[1]);
        const intern = await screen.getByDisplayValue('extern');
        expect(intern).not.toBeInTheDocument();
    });
    // todo fix test
    /*
    it('should add new input elements for added element when the add button was clicked', async () => {
        const newDescriptor = 'Irgendein neuer Descriptor';
        const newId = 42;
        const mockAuthenticatedFetchPostImpl = async () => new Response(JSON.stringify({ id: newId, descriptor: newDescriptor }));
        jest.spyOn(fetcher, 'authenticatedFetchPost')
            .mockImplementation(mockAuthenticatedFetchPostImpl);

        const addButton = await screen.queryByText(addButtonLabel);
        const addDescriptorTextField = (screen.queryByText('Bezeichner:'))?.parentElement!.querySelector('input');
        expect(addButton).not.toBeNull();
        expect(addDescriptorTextField).not.toBeNull();

        addDescriptorTextField!.value = newDescriptor;
        act(() => {
            addButton!.click();
        });

        const addedDescriptorTextField = await screen.queryByText(addButtonLabel);
        expect(addedDescriptorTextField!.parentElement!.tagName).toEqual('ARTICLE'); // see admin-panel-list-item.tsx
        expect(addedDescriptorTextField).toBeInTheDocument();
    });

     */
});
