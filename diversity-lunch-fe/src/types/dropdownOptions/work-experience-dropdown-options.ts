import { DropdownOptions } from './dropdown-options.type';
import { WorkExperience } from '../enums/workexperience.type';

export const WORK_EXPERIENCE_DROPDOWN_OPTIONS: DropdownOptions<WorkExperience> = [
    {
        value: 'LOW_EXPERIENCE',
        label: '0-3 Jahre',
    },
    {
        value: 'MID_EXPERIENCE',
        label: '4-10 Jahre',
    },
    {
        value: 'HIGH_EXPERIENCE',
        label: 'über 10 Jahre',
    },
];
