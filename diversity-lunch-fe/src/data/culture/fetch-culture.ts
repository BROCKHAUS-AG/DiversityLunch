import { GenericFetch } from '../generic/GenericFetch';
import { cultureSlice } from './culture-reducer';

export const cultureFetch = new GenericFetch(cultureSlice, 'culture');
