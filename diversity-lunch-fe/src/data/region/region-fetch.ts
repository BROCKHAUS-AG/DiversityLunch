import { GenericFetch } from '../generic/GenericFetch';
import { regionSlice } from './region-reducer';

export const regionFetch = new GenericFetch(regionSlice, 'region');
