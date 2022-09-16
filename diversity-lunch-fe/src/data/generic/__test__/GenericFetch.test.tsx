import { Provider, useDispatch, useSelector } from 'react-redux';
import { configureStore } from '@reduxjs/toolkit';
import React, { useEffect } from 'react';
import { applyMiddleware, combineReducers, createStore } from 'redux';
import thunk from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';
import { render } from '@testing-library/react';
import { screen } from '@testing-library/dom';
import { authenticatedFetchGet } from '../../../utils/fetch.utils';
import * as fetcher from '../../../utils/fetch.utils';
import * as generics from '../GenericFetch';
import { GenericSlice } from '../GenericSlice';
import { Country } from '../../../model/Country';
import Yee from './Yee';
import countryReducer from '../../country/country-reducer';
import { countryFetch } from '../../country/fetch-country';
import { meetingsReducer } from '../../meeting/meetings.reducer';
import { authenticationReducer } from '../../authentication/authentication.reducer';
import { profileReducer } from '../../profile/profile.reducer';
import { accountReducer } from '../../account/account.reducer';
import experienceLevelReducer from '../../experienceLevel/experience-level.reducer';
import dietReducer from '../../diet/diet-reducer';
import activityReducer from '../../activity/activity-reducer';
import categoryReducer from '../../category/category-reducer';
import cultureReducer from '../../culture/culture-reducer';
import educationReducer from '../../education/education-reducer';
import genderReducer from '../../gender/gender-reducer';
import hobbyReducer from '../../hobby/hobby-reducer';
import industryReducer from '../../industry/industry-reducer';

const data = [{
    id: 1,
    descriptor: 'Deutschland',
}];

const reducers = {
    test: countryReducer,
};

const rootReducer = combineReducers(reducers);

const withMiddleWare = applyMiddleware(thunk);
const withDevTools = composeWithDevTools(withMiddleWare);
const appStore = createStore(rootReducer, withDevTools);

type storeState = ReturnType<typeof rootReducer>;

const getCountryResponse = async () => new Response(JSON.stringify(data));

const getAllResponse = async () => new Response(JSON.stringify(data));

export const TestComponent = () => {
    jest.spyOn(fetcher, 'authenticatedFetchGet').mockReturnValue(getCountryResponse());

    const countries = useSelector((store: storeState) => store.test);
    const dispatch = useDispatch();

    dispatch(countryFetch.getAll());

    return (
        <div data-testid="testja">
            {countries.items.map((elem : Country) => <p>{elem.descriptor}</p>)}
        </div>
    );
};

it('should fetch the data', async () => {
    jest.spyOn(fetcher, 'authenticatedFetchGet').mockReturnValue(getCountryResponse());

    const response = await authenticatedFetchGet('/country');

    const result = await response.json();

    expect(result).toEqual(data);
});

it('a', async () => {


    render(
        <Provider store={appStore}>
            <div>
                <TestComponent />
            </div>
        </Provider>,
    );
    // const appStore = createStore(countryReducer, { items: [], fetched: false });


    // jest.spyOn(countryFetch, 'getAll').mockReturnValue(getAllResponse());

    // appStore.dispatch(() => countryFetch.getAll());

    // expect(appStore.getState().test.items).toEqual(null);

    expect(screen.getByTestId('testja').children.length).toBe(3);

    // return (
    //     <Provider store={appStore}>
    //         <p>hehe</p>
    //     </Provider>
    // );

    // const countries = useSelector((store: AppStoreState) => store.country);
    // const dispatch = useDispatch();
    //
    // jest.spyOn(fetcher, 'authenticatedFetchGet').mockReturnValue(getCountryResponse());
    //
    // dispatch(countryFetch.getAll());
    //
    // expect(countries.items).toEqual(null);

    // const test = useSelector((store: typeof appStore) => store.test);
    // const dispatch = useDispatch();
    //
    // const test = new Yee();
    // test.doSomething();
    //
    // const mocked = Yee.mock
    //
    // jest.spyOn(global, 'getAll').mockReturnValue();
    // dispatch(countryFetch.getAll());
});
