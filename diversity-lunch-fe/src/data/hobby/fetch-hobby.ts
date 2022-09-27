import { GenericFetch } from '../generic/GenericFetch';
import { hobbySlice } from './hobby-reducer';

export const HOBBY_ENDPOINT = 'hobby';
export const hobbyFetch = new GenericFetch(hobbySlice, HOBBY_ENDPOINT);
