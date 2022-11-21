import { GenericFetch } from '../generic/GenericFetch';
import { discriminationSlice } from './discrimination-reducer';

export const discriminationFetch = new GenericFetch(socialBackgroundSlice, 'discrimination');
