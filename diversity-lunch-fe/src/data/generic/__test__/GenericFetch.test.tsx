import { Provider, useDispatch, useSelector } from 'react-redux';
import React, { useEffect } from 'react';
import { render, screen } from '@testing-library/react';
import {
    applyMiddleware, combineReducers, createStore, Dispatch,
} from 'redux';
import thunk from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';
import { Reducer } from '@reduxjs/toolkit';
import * as fetcher from '../../../utils/fetch.utils';
import { Country } from '../../../model/Country';
import { GenericSlice } from '../GenericSlice';
import { GenericFetch } from '../GenericFetch';

let rootReducer : Reducer;

interface FetchingTestComponentProps {
    actionThunk: (dispatch: Dispatch<any>) => Promise<void>,
}
const FetchingTestComponent = ({ actionThunk }: FetchingTestComponentProps) => {
    const countries = useSelector((store: ReturnType<typeof rootReducer>) => store.country);
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(actionThunk);
    }, []);

    return (
        <section>
            {countries.items.map((elem: Country) => <p key={elem.id}>{elem.descriptor}</p>)}
        </section>
    );
};

const data = {
    id: 2,
    descriptor: 'Deutschland',
};

const initialData = {
    id: 1,
    descriptor: 'Frankreich',
};

const fetchCountryResponse = async () => new Response(JSON.stringify(data));
const fetchInitialData = async () => new Response(JSON.stringify(initialData));

describe('GenericFetch Integrationtests', () => {
    let appStore : any;
    let countryFetchTest : GenericFetch<Country>;

    beforeEach(() => {
        const countrySlice = new GenericSlice<Country>('countryReducerTest', [initialData]);

        const {
            add, update, remove, initFetch,
        } = countrySlice.actions;
        const { reducer } = countrySlice;

        countryFetchTest = new GenericFetch(countrySlice, 'country');

        const reducers = {
            country: reducer,
        };

        rootReducer = combineReducers(reducers);

        const withMiddleWare = applyMiddleware(thunk);
        const withDevTools = composeWithDevTools(withMiddleWare);
        appStore = createStore(rootReducer, withDevTools);
    });

    it('should render fetched data', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet').mockReturnValue(fetchInitialData());
        render(
            <Provider store={appStore}>
                <FetchingTestComponent actionThunk={countryFetchTest.getAll()} />
            </Provider>,
        );

        const result = await screen.findByText(initialData.descriptor);

        expect(result).toBeInTheDocument();
    });

    it('should render posted data', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchPost').mockReturnValue(fetchCountryResponse());
        render(
            <Provider store={appStore}>
                <FetchingTestComponent actionThunk={countryFetchTest.post(data)} />
            </Provider>,
        );

        const result = await screen.findByText(data.descriptor);

        expect(result).toBeInTheDocument();
    });

    it('should render updated data', async () => {
        const updatedData = {
            id: 1,
            descriptor: 'Ungarn',
        };
        const updateCountryResponse = async () => new Response(JSON.stringify(updatedData));

        jest.spyOn(fetcher, 'authenticatedFetchPut').mockReturnValue(updateCountryResponse());

        render(
            <Provider store={appStore}>
                <FetchingTestComponent actionThunk={countryFetchTest.put(updatedData)} />
            </Provider>,
        );

        const result = await screen.findByText(updatedData.descriptor);

        expect(result).toBeInTheDocument();
    });

    it('should render the deleted data', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchDelete').mockReturnValue(fetchInitialData());

        render(
            <Provider store={appStore}>
                <FetchingTestComponent actionThunk={countryFetchTest.removeById(initialData.id)} />
            </Provider>,
        );

        const result = await screen.findByText(initialData.descriptor);

        expect(result).toBeInTheDocument();
    });
});
