import { DropdownOptions } from './dropdown-options.type';
import { Diet } from '../enums/diet.type';

export const DIET_DROPDOWN_OPTIONS: DropdownOptions<Diet> = [
    {
        value: 'MEAT',
        label: 'Fleischesser',
    },
    {
        value: 'VEGETARIAN',
        label: 'Vegetarier',
    },
    {
        value: 'VEGAN',
        label: 'Veganer',
    },
    {
        value: 'OTHER',
        label: 'Sonstige',
    },
];
