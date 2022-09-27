import { GenericSlice } from '../generic/GenericSlice';
import { Language } from '../../model/Language';

export const languageSlice = new GenericSlice<Language>('languageReducer');

export const {
    add, update, remove, initFetch,
} = languageSlice.actions;
export default languageSlice.reducer;
