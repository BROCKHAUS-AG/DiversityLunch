import { GenericSlice } from '../generic/GenericSlice';
import { Gender } from '../../model/Gender';

export const genderSlice = new GenericSlice<Gender>('genderReducer');

export const {
    add, update, remove, initFetch,
} = genderSlice.actions;
export default genderSlice.reducer;
