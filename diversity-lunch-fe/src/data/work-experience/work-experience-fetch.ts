import { GenericFetch } from '../generic/GenericFetch';
import { workExperienceSlice } from './work-experience-reducer';

export const WORK_EXPERIENCE_ENDPOINT = 'work-experience';
export const workExperienceFetch = new GenericFetch(workExperienceSlice, WORK_EXPERIENCE_ENDPOINT);
