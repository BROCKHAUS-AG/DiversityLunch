import { GenericFetch } from '../generic/GenericFetch';
import { sexualOrientationSlice } from './sexual-orientation-reducer';

export const sexualOrientationFetch = new GenericFetch(sexualOrientationSlice, 'sexuality');
