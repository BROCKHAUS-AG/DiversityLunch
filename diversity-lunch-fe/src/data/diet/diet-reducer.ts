import { GenericSlice } from '../generic/GenericSlice';
import { Diet } from '../../model/Diet';

export const dietSlice = new GenericSlice<Diet>('experienceLevelReducer');

export const {
    add, update, remove, initFetch,
} = dietSlice.actions;
export default dietSlice.reducer;
