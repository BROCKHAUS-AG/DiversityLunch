import { GenericSlice } from '../generic/GenericSlice';
import { ExperienceLevel } from '../../model/ExperienceLevel';

export const experienceLevelSlice = new GenericSlice<ExperienceLevel>('experienceLevelReducer');

export const {
    add, update, remove, initFetch,
} = experienceLevelSlice.actions;
export default experienceLevelSlice.reducer;
