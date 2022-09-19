import { GenericSlice } from '../generic/GenericSlice';
import { Religion } from '../../model/Religion';

export const religionSlice = new GenericSlice<Religion>('religionReducer');

export const {
    add, update, remove, initFetch,
} = religionSlice.actions;
export default religionSlice.reducer;
