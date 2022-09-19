import { GenericSlice } from '../generic/GenericSlice';
import { Sexuality } from '../../model/Sexuality';

export const socialBackgroundSlice = new GenericSlice<Sexuality>('socialBackgroundReducer');

export const {
    add, update, remove, initFetch,
} = socialBackgroundSlice.actions;
export default socialBackgroundSlice.reducer;
