import { GenericFetch } from '../generic/GenericFetch';
import { projectSlice } from './project-reducer';

export const projectFetch = new GenericFetch(projectSlice, 'project');
