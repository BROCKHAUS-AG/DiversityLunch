import { Provider, useDispatch, useSelector } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import {
    fireEvent, getByDisplayValue, render, screen,
} from '@testing-library/react';
import React, { ChangeEvent, FC } from 'react';
import { APP_STORE, AppStoreState } from '../../../store/Store';
import { projectFetch } from '../../../data/project/project-fetch';
import { OptionsList } from '../OptionsList';
import * as fetcher from '../../../utils/fetch.utils';
import { mockedFetchGetProfile } from '../../../__global_test_data__/fetch';

const addButtonLabel: string = 'Projekt hinzufügen';

const WrapperComponent: FC = () => {
    const projectState = useSelector((store: AppStoreState) => store.project);
    const dispatch = useDispatch();
    dispatch(projectFetch.getAll());
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
    let container : HTMLElement;

    beforeEach(() => {
        jest.spyOn(fetcher, 'authenticatedFetchGet')
            .mockImplementation(mockedFetchGetProfile);
        jest.spyOn(fetcher, 'authenticatedFetchDelete')
            .mockImplementation(mockAuthenticatedFetchDeleteImpl);
        ({ container } = render(
            <BrowserRouter>
                <Provider store={APP_STORE}>
                    <WrapperComponent />
                </Provider>
            </BrowserRouter>,
        ));
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

    it('should add new input elements for added element when the add button was clicked', async () => {
        const newDescriptor = 'Irgendein neuer Descriptor';
        const mockAuthenticatedFetchPostImpl = async () => new Response(JSON.stringify({ id: 9, descriptor: newDescriptor }));
        jest.spyOn(fetcher, 'authenticatedFetchPost')
            .mockImplementation(mockAuthenticatedFetchPostImpl);

        const addButton = await screen.queryByText(addButtonLabel);
        const addDescriptorTextField = (await screen.queryByText('Bezeichner:'))?.parentElement!.querySelector('input');
        expect(addButton).not.toBeNull();
        expect(addDescriptorTextField).not.toBeNull();

        addDescriptorTextField!.onchange = () => {
            addButton!.click();
            event.target!.value = '';
        };
        fireEvent.change(addDescriptorTextField!, { target: { value: newDescriptor } });

        const addedDescriptorTextField = await screen.getByDisplayValue(newDescriptor);
        expect(addedDescriptorTextField.parentElement!.tagName).toEqual('article'); // see admin-panel-list-item.tsx
        expect(addedDescriptorTextField).toBeInTheDocument();
    });
});
