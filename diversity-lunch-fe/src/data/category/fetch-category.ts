import { GenericFetch } from '../generic/GenericFetch';
import { categorySlice } from './category-reducer';

export const CATEGORY_ENDPOINT = 'category';
export const categoryFetch = new GenericFetch(categorySlice, CATEGORY_ENDPOINT);
