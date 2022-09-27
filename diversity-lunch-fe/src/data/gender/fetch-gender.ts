import { GenericFetch } from '../generic/GenericFetch';
import { genderSlice } from './gender-reducer';

export const GENDER_ENDPOINT = 'gender';
export const genderFetch = new GenericFetch(genderSlice, GENDER_ENDPOINT);
