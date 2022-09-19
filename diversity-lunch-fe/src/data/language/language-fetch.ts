import { GenericFetch } from '../generic/GenericFetch';
import { languageSlice } from './language-reducer';

export const languageFetch = new GenericFetch(languageSlice, 'language');
