import { GenericSlice } from '../generic/GenericSlice';
import { Project } from '../../model/Project';

export const projectSlice = new GenericSlice<Project>('projectReducer');

export const {
    add, update, remove, initFetch,
} = projectSlice.actions;
export default projectSlice.reducer;
