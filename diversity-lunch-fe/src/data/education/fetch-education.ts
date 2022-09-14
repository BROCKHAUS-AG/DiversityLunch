import { GenericFetch } from '../generic/GenericFetch';
import { educationSlice } from './education-reducer';

export const educationFetch = new GenericFetch(educationSlice, 'education');
