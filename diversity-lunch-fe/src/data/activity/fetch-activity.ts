import { GenericFetch } from '../generic/GenericFetch';
import { activitySlice } from './activity-reducer';

export const activityFetch = new GenericFetch(activitySlice, 'country');
