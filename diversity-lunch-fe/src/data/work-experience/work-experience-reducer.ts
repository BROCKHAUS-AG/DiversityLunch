import { GenericSlice } from '../generic/GenericSlice';
import { Sexuality } from '../../model/Sexuality';

export const workExperienceSlice = new GenericSlice<Sexuality>('workExperienceReducer');

export const {
    add, update, remove, initFetch,
} = workExperienceSlice.actions;
export default workExperienceSlice.reducer;
