import { GenericSlice } from '../generic/GenericSlice';
import { Region } from '../../model/Region';

export const regionSlice = new GenericSlice<Region>('regionReducer');

export const {
    add, update, remove, initFetch,
} = regionSlice.actions;
export default regionSlice.reducer;
