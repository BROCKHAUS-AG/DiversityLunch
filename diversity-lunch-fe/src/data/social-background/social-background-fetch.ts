import { GenericFetch } from '../generic/GenericFetch';
import { socialBackgroundSlice } from './social-background-reducer';

export const socialBackgroundFetch = new GenericFetch(socialBackgroundSlice, 'social-background');
