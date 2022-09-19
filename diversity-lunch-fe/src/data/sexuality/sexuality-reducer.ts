import { GenericSlice } from '../generic/GenericSlice';
import { Sexuality } from '../../model/Sexuality';

export const sexualitySlice = new GenericSlice<Sexuality>('sexualityReducer');

export const {
    add, update, remove, initFetch,
} = sexualitySlice.actions;
export default sexualitySlice.reducer;
