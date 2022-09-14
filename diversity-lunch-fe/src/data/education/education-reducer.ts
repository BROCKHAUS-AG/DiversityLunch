import { GenericSlice } from '../generic/GenericSlice';
import { Education } from '../../model/Education';

export const educationSlice = new GenericSlice<Education>('educationReducer');

export const {
    add, update, remove, initFetch,
} = educationSlice.actions;
export default educationSlice.reducer;
