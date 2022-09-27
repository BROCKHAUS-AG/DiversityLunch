import { GenericSlice } from '../generic/GenericSlice';
import { Culture } from '../../model/Culture';

export const cultureSlice = new GenericSlice<Culture>('cultureReducer');

export const {
    add, update, remove, initFetch,
} = cultureSlice.actions;
export default cultureSlice.reducer;
