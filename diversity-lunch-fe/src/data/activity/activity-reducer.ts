import { GenericSlice } from '../generic/GenericSlice';
import { Activity } from '../../model/Activity';

export const activitySlice = new GenericSlice<Activity>('activityReducer');

export const {
    add, update, remove, initFetch,
} = activitySlice.actions;
export default activitySlice.reducer;
