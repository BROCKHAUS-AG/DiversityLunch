import { Provider, useDispatch, useSelector } from 'react-redux';
import React, { useEffect } from 'react';
import { render, screen } from '@testing-library/react';
import * as fetcher from '../../../utils/fetch.utils';
import { Country } from '../../../model/Country';
import { countryFetch } from '../../country/fetch-country';
import { APP_STORE, AppStoreState } from '../../../store/Store';

describe('GenericFetch Integrationtests', () => {
    const data = [{
        id: 1,
        descriptor: 'Deutschland',
    }];
    const getCountryResponse = async () => new Response(JSON.stringify(data));

    const TestComponent = () => {
        const countries = useSelector((store: AppStoreState) => store.country);
        const dispatch = useDispatch();

        useEffect(() => {
            dispatch(countryFetch.getAll());
        }, []);

        return (
            <section>
                {countries.items.map((elem: Country) => <p key={elem.id}>{elem.descriptor}</p>)}
            </section>
        );
    };

    it('Renders fetched data from GenericFetch', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet').mockReturnValue(getCountryResponse());
        render(
            <Provider store={APP_STORE}>
                <TestComponent />
            </Provider>,
        );

        const result = await screen.findByText('Deutschland');

        expect(result).toBeInTheDocument();
    });
});
