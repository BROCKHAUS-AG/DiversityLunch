import { GenericFetch } from '../generic/GenericFetch';
import { workExperienceSlice } from './work-experience-reducer';

export const workExperienceFetch = new GenericFetch(workExperienceSlice, 'work-experience');
