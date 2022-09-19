import { GenericFetch } from '../generic/GenericFetch';
import { dietSlice } from './diet-reducer';

export const dietFetch = new GenericFetch(dietSlice, 'diet');
