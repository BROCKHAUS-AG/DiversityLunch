import { GenericSlice } from '../generic/GenericSlice';
import { SexualOrientation } from '../../model/SexualOrientation';

export const sexualOrientationSlice = new GenericSlice<SexualOrientation>('sexualOrientationReducer');

export const {
    add, update, remove, initFetch,
} = sexualOrientationSlice.actions;
export default sexualOrientationSlice.reducer;
