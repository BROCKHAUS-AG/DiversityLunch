import { GenericSlice } from '../generic/GenericSlice';
import { Region } from '../../model/Region';

export const religionSlice = new GenericSlice<Region>('religionReducer');

export const {
    add, update, remove, initFetch,
} = religionSlice.actions;
export default religionSlice.reducer;
