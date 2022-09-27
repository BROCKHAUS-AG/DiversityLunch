import { GenericFetch } from '../generic/GenericFetch';
import { countrySlice } from './country-reducer';

export const COUNTRY_ENDPOINT = 'country';
export const countryFetch = new GenericFetch(countrySlice, COUNTRY_ENDPOINT);
