import { GenericFetch } from '../generic/GenericFetch';
import { socialBackgroundDiscriminationSlice } from './social-background-discrimination-reducer';

export const socialBackgroundDiscriminationFetch = new GenericFetch(socialBackgroundDiscriminationSlice, 'social-background-discrimination');
