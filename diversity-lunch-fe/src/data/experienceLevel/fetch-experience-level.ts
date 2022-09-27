import { GenericFetch } from '../generic/GenericFetch';
import { experienceLevelSlice } from './experience-level.reducer';

export const experienceLevelFetch = new GenericFetch(experienceLevelSlice, 'experience-level');
