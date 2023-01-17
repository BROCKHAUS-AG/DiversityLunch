import { GenericFetch } from '../generic/GenericFetch';
import { wasChangedByAdminGenericSlice } from './was-changed-by-admin-reducer';

export const wasChangedByAdminFetch = new GenericFetch(wasChangedByAdminGenericSlice, 'was-changed-by-admin');
