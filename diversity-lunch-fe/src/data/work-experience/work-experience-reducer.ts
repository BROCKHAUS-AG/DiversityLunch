import { GenericSlice } from '../generic/GenericSlice';
import { WorkExperience } from '../../model/WorkExperience';

export const workExperienceSlice = new GenericSlice<WorkExperience>('workExperienceReducer');

export const {
    add, update, remove, initFetch,
} = workExperienceSlice.actions;
export default workExperienceSlice.reducer;
