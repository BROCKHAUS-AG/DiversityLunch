import { GenericSlice } from '../generic/GenericSlice';
import { Category } from '../../model/Category';

export const categorySlice = new GenericSlice<Category>('categoryReducer');

export const {
    add, update, remove, initFetch,
} = categorySlice.actions;
export default categorySlice.reducer;
