import { GenericSlice } from '../generic/GenericSlice';
import { SocialBackground } from '../../model/SocialBackground';

export const discriminationSlice = new GenericSlice<SocialBackground>('discrimination');

export const {
    add, update, remove, initFetch,
} = discriminationSlice.actions;
export default discriminationSlice.reducer;
