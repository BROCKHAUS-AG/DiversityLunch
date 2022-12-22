import { GenericSlice } from '../generic/GenericSlice';
import { WasChangedByAdmin } from '../../model/WasChangedByAdmin';

export const wasChangedByAdminGenericSlice = new GenericSlice<WasChangedByAdmin>('wasChangedByAdmin');

export const {
    add, update, remove, initFetch,
} = wasChangedByAdminGenericSlice.actions;
export default wasChangedByAdminGenericSlice.reducer;
