import { GenericSlice } from '../generic/GenericSlice';
import { Industry } from '../../model/Industry';

export const industrySlice = new GenericSlice<Industry>('industryReducer');

export const {
    add, update, remove, initFetch,
} = industrySlice.actions;
export default industrySlice.reducer;
