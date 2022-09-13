import { GenericFetch } from '../generic/GenericFetch';
import { dietSlice } from './diet-reducer';

export const countryFetch = new GenericFetch(dietSlice, 'country');
