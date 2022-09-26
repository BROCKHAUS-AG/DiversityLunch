import { GenericFetch } from '../generic/GenericFetch';
import { religionSlice } from './religion-reducer';

export const RELIGION_ENDPOINT = 'religion';
export const religionFetch = new GenericFetch(religionSlice, RELIGION_ENDPOINT);
