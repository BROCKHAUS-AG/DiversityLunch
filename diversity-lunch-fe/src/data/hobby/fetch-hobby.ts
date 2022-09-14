import { GenericFetch } from '../generic/GenericFetch';
import { hobbySlice } from './hobby-reducer';

export const hobbyFetch = new GenericFetch(hobbySlice, 'hobby');
