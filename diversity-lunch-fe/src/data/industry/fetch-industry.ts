import { GenericFetch } from '../generic/GenericFetch';
import { industrySlice } from './industry-reducer';

export const industryFetch = new GenericFetch(industrySlice, 'industry');
