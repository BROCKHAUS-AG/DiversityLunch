import { GenericSlice } from '../generic/GenericSlice';
import { SocialBackgroundDiscrimination } from '../../model/SocialBackgroundDiscrimination';

export const socialBackgroundDiscriminationSlice = new GenericSlice<SocialBackgroundDiscrimination>('socialBackgroundDiscrimination');

export const {
    add, update, remove, initFetch,
} = socialBackgroundDiscriminationSlice.actions;
export default socialBackgroundDiscriminationSlice.reducer;
