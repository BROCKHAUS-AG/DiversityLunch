import { Provider, useDispatch, useSelector } from 'react-redux';
import React, { useEffect } from 'react';
import { render, screen } from '@testing-library/react';
import { Dispatch } from 'redux';
import * as fetcher from '../../../utils/fetch.utils';
import { Country } from '../../../model/Country';
import { countryFetch } from '../../country/fetch-country';
import { APP_STORE, AppStoreState } from '../../../store/Store';

interface FetchingTestComponentProps {
    actionThunk: (dispatch: Dispatch<any>) => Promise<void>,
}
const FetchingTestComponent = ({ actionThunk }: FetchingTestComponentProps) => {
    const countries = useSelector((store: AppStoreState) => store.country);
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

const data = [{
    id: 1,
    descriptor: 'Deutschland',
}];
const fetchMultipleCountriesResponse = async () => new Response(JSON.stringify(data));
const fetchCountryResponse = async () => new Response(JSON.stringify(data[0]));

describe('GenericFetch Integrationtests', () => {
    it('should render fetched data', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchGet').mockReturnValue(fetchMultipleCountriesResponse());
        render(
            <Provider store={APP_STORE}>
                <FetchingTestComponent actionThunk={countryFetch.getAll()} />
            </Provider>,
        );

        const result = await screen.findByText(data[0].descriptor);

        expect(result).toBeInTheDocument();
    });

    it('should render posted data', async () => {
        jest.spyOn(fetcher, 'authenticatedFetchPost').mockReturnValue(fetchCountryResponse());
        render(
            <Provider store={APP_STORE /*TODO: DO NOT REUSE STORE BETWEEN MULTIPLE TESTS OR TEST ALL FUNCTION IN ONE INTEGRATION TEST*/}>
                <FetchingTestComponent actionThunk={countryFetch.post(data[0])} />
            </Provider>,
        );

        const result = await screen.findByText(data[0].descriptor);

        expect(result).toBeInTheDocument();
    });
});
