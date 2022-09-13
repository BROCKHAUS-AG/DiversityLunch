import { GenericFetch } from '../generic/GenericFetch';
import { countrySlice } from './country-reducer';

export const countryFetch = new GenericFetch(countrySlice, 'country');
