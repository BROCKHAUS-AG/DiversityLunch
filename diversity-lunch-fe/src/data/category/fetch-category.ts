import { GenericFetch } from '../generic/GenericFetch';
import { categorySlice } from './category-reducer';

export const categoryFetch = new GenericFetch(categorySlice, 'category');
