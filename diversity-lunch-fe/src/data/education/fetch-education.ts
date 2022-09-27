import { GenericFetch } from '../generic/GenericFetch';
import { educationSlice } from './education-reducer';

export const EDUCATION_ENDPOINT = 'education';
export const educationFetch = new GenericFetch(educationSlice, EDUCATION_ENDPOINT);
