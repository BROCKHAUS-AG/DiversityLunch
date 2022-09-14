import { Activity } from './Activity';
import { ExperienceLevel } from './ExperienceLevel';
import { Identifiable } from '../data/generic/Identifiable';

export interface Hobby extends Identifiable{
    experience: ExperienceLevel,
    activity: Activity
}
