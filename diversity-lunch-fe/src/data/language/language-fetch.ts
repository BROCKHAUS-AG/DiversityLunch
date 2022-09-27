import { GenericFetch } from '../generic/GenericFetch';
import { languageSlice } from './language-reducer';

export const LANGUAGE_ENDPOINT = 'language';
export const languageFetch = new GenericFetch(languageSlice, LANGUAGE_ENDPOINT);
