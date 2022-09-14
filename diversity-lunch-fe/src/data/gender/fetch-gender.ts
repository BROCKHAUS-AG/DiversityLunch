import { GenericFetch } from '../generic/GenericFetch';
import { genderSlice } from './gender-reducer';

export const genderFetch = new GenericFetch(genderSlice, 'gender');
