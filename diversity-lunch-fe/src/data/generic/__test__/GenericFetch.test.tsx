import { Provider, useDispatch, useSelector } from 'react-redux';
import React, { useEffect } from 'react';
import { applyMiddleware, combineReducers, createStore } from 'redux';
import thunk from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';
import { render, screen } from '@testing-library/react';
import * as fetcher from '../../../utils/fetch.utils';
import { authenticatedFetchGet } from '../../../utils/fetch.utils';
import { Country } from '../../../model/Country';
import countryReducer from '../../country/country-reducer';
import { countryFetch } from '../../country/fetch-country';
import { APP_STORE, AppStoreState } from '../../../store/Store';

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

export const TestComponent = (props : any) => {
    jest.spyOn(fetcher, 'authenticatedFetchGet').mockReturnValue(getCountryResponse());

    const countries = useSelector((store: AppStoreState) => store.country);
    const dispatch = useDispatch();

    dispatch(countryFetch.getAll()).then(props.callback);

    return (
        <section data-testid="testja">
            <p>HI</p>
            {countries.items.map((elem : Country) => <p>{elem.descriptor}</p>)}
        </section>
    );
};

it('should fetch the data', async () => {
    jest.spyOn(fetcher, 'authenticatedFetchGet').mockReturnValue(getCountryResponse());

    const response = await authenticatedFetchGet('/country');

    const result = await response.json();

    expect(result).toEqual(data);
});

it('a', async () => {
    let container : HTMLElement;
    let expectCallback = () => expect(container.querySelectorAll('section')[0].textContent).toEqual('Deutschland');
    ({ container } = render(<Provider store={APP_STORE}><TestComponent callback={expectCallback} /></Provider>));
    // const appStore = createStore(countryReducer, { items: [], fetched: false });

    // jest.spyOn(countryFetch, 'getAll').mockReturnValue(getAllResponse());

    // appStore.dispatch(() => countryFetch.getAll());

    // expect(appStore.getState().test.items).toEqual(null);
    //await expect(container.querySelectorAll('section')[0].textContent).toEqual('Deutschland');
    //expect(container.querySelectorAll('div')[1].innerContent).toEqual('Deutschland');

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
