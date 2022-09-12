import { DropdownOptions } from './dropdown-options.type';
import { Gender } from '../enums/gender.type';

export const GENDER_DROPDOWN_OPTIONS: DropdownOptions<Gender> = [
    {
        value: 'MALE',
        label: 'Männlich',
    },
    {
        value: 'FEMALE',
        label: 'Weiblich',
    },
    {
        value: 'NOT_SPECIFIED',
        label: 'Keine Angabe',
    },
];
