import { GenericSlice } from '../generic/GenericSlice';
import { Hobby } from '../../model/Hobby';

export const hobbySlice = new GenericSlice<Hobby>('hobbyReducer');

export const {
    add, update, remove, initFetch,
} = hobbySlice.actions;
export default hobbySlice.reducer;
