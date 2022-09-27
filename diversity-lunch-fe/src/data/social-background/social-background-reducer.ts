import { GenericSlice } from '../generic/GenericSlice';
import { SocialBackground } from '../../model/SocialBackground';

export const socialBackgroundSlice = new GenericSlice<SocialBackground>('socialBackgroundReducer');

export const {
    add, update, remove, initFetch,
} = socialBackgroundSlice.actions;
export default socialBackgroundSlice.reducer;
