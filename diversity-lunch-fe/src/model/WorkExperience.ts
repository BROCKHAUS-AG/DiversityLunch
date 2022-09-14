import { ExperienceLevel } from './ExperienceLevel';
import { Industry } from './Industry';
import { Identifiable } from '../data/generic/Identifiable';

export interface WorkExperience extends Identifiable {
    experienceLevel: ExperienceLevel,
    industry: Industry,
}
