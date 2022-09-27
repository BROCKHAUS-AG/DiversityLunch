import { GenericFetch } from '../generic/GenericFetch';
import { projectSlice } from './project-reducer';

export const PROJECT_ENDPOINT = 'project';
export const projectFetch = new GenericFetch(projectSlice, PROJECT_ENDPOINT);
