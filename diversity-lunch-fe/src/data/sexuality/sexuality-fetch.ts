import { GenericFetch } from '../generic/GenericFetch';
import { sexualitySlice } from './sexuality-reducer';

export const sexualityFetch = new GenericFetch(sexualitySlice, 'sexuality');
