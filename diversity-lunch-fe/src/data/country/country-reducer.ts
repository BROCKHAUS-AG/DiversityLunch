import { GenericSlice } from '../generic/GenericSlice';
import { Country } from '../../model/Country';

export const countrySlice = new GenericSlice<Country>('experienceLevelReducer');

export const {
    add, update, remove, initFetch,
} = countrySlice.actions;
export default countrySlice.reducer;
