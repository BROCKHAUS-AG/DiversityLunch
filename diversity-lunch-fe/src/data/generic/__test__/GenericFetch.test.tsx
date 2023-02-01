import { Provider, useDispatch, useSelector } from 'react-redux';
import React, { useEffect } from 'react';
import {
    cleanup, render, screen, waitFor,
} from '@testing-library/react';
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

interface FetchingTestComponentProps {
  actionThunk: (dispatch: Dispatch<any>) => Promise<void>,
}

const FetchingTestComponent = ({ actionThunk }: FetchingTestComponentProps) => {
    const countries = useSelector((store: ReturnType<Reducer>) => store.country);
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
    default: false,
};

const initialData = {
    id: 1,
    descriptor: 'Frankreich',
    default: false,
};

const countryResponseThunk = async () => (new Response(JSON.stringify(data)));
const initialDataResponseThunk = async () => (new Response(JSON.stringify(initialData)));
const dataAsArrayResponseThunk = async () => (new Response(JSON.stringify([initialData, data])));
const notFoundResponseThunk = async () => (new Response(null, {
    status: 404,
}));
const nonJsonResponseThunk = async () => (new Response('I am no JSON'));
const networkingErrorThunk = async () => (Promise.reject);

describe('GenericFetch Integrationtests', () => {
    let appStore: any;
    let countryFetchTest: GenericFetch<Country>;
    let rootReducer: Reducer;

    beforeEach(() => {
        const countrySlice = new GenericSlice<Country>('countryReducerTest', [initialData]);
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

    afterEach(cleanup);

    it('should render fetched data', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet').mockReturnValue(dataAsArrayResponseThunk());
        render(
            <Provider store={appStore}>
                <FetchingTestComponent actionThunk={countryFetchTest.getAll({
                    onNetworkError: () => fail('Shouldn\'t call network error callback'),
                    statusCodeHandlers: { 404: () => fail('Shouldn\'t call 404 callback') },
                })}
                />
            </Provider>,
        );
        const result = await screen.findByText(data.descriptor);

        expect(result)
            .toBeInTheDocument();
    });

    it('should render posted data', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchPost')
            .mockReturnValue(countryResponseThunk());
        render(
            <Provider store={appStore}>
                <FetchingTestComponent actionThunk={countryFetchTest.post(data, {
                    onNetworkError: () => fail('Shouldn\'t call network error callback'),
                    statusCodeHandlers: { 404: () => fail('Shouldn\'t call 404 callback') },
                })}
                />
            </Provider>,
        );

        const result = await screen.findByText(data.descriptor);

        expect(result)
            .toBeInTheDocument();
    });

    it('should render updated data', async () => {
        const updatedData = {
            id: 1,
            descriptor: 'Ungarn',
            default: false,
        };
        const updateCountryResponse = async () => new Response(JSON.stringify(updatedData));
        jest.spyOn(fetcher, 'authenticatedFetchPut').mockReturnValue(updateCountryResponse());

        render(
            <Provider store={appStore}>
                <FetchingTestComponent actionThunk={countryFetchTest.put(updatedData, {
                    onNetworkError: () => fail('Shouldn\'t call network error callback'),
                    statusCodeHandlers: { 404: () => fail('Shouldn\'t call 404 callback') },
                })}
                />
            </Provider>,
        );

        const result = await screen.findByText(updatedData.descriptor);

        expect(result).toBeInTheDocument();
    });

    it('should not render the deleted data', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchDelete').mockReturnValue(initialDataResponseThunk());

        render(
            <Provider store={appStore}>
                <FetchingTestComponent actionThunk={countryFetchTest.removeById(initialData.id, {
                    onNetworkError: () => fail('Shouldn\'t call network error callback'),
                    statusCodeHandlers: { 404: () => fail('Shouldn\'t call 404 callback') },
                })}
                />
            </Provider>,
        );

        const result = await screen.findByText(initialData.descriptor);

        expect(result).not.toBeInTheDocument();
    });

    it('should call given status code callbacks', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchDelete').mockReturnValue(notFoundResponseThunk());
        const notFoundCallback = jest.fn();

        render(
            <Provider store={appStore}>
                <FetchingTestComponent actionThunk={countryFetchTest.removeById(initialData.id, {
                    onNetworkError: () => fail('Shouldn\'t call network error callback'),
                    statusCodeHandlers: { 404: notFoundCallback },
                })}
                />
            </Provider>,
        );

        await waitFor(() => expect(notFoundCallback).toHaveBeenCalledTimes(1));
    });

    it('should call given network error callbacks', async () => {
        // @ts-ignore It ain't stupid if it works ¯\_(ツ)_/¯
        jest.spyOn(fetcher, 'authenticatedFetchDelete').mockReturnValue(networkingErrorThunk());
        const networkErrorCallback = jest.fn();

        render(
            <Provider store={appStore}>
                <FetchingTestComponent actionThunk={countryFetchTest.removeById(initialData.id, {
                    onNetworkError: networkErrorCallback,
                    statusCodeHandlers: {},
                })}
                />
            </Provider>,
        );

        await waitFor(() => expect(networkErrorCallback)
            .toHaveBeenCalledTimes(1));
    });

    it('should call network error when json parse errors happen', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet').mockReturnValue(nonJsonResponseThunk());
        const networkErrorCallback = jest.fn();

        render(
            <Provider store={appStore}>
                <FetchingTestComponent actionThunk={countryFetchTest.getAll({
                    onNetworkError: networkErrorCallback,
                    statusCodeHandlers: {},
                })}
                />
            </Provider>,
        );

        await waitFor(() => expect(networkErrorCallback).toHaveBeenCalledTimes(1));
    });
});
