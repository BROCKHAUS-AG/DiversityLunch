import { GenericFetch } from '../generic/GenericFetch';
import { dietSlice } from './diet-reducer';

export const DIET_ENDPOINT = 'diet';
export const dietFetch = new GenericFetch(dietSlice, DIET_ENDPOINT);
