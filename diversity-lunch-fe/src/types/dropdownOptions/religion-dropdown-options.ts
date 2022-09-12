import { DropdownOptions } from './dropdown-options.type';
import { Religion } from '../enums/religion.type';

export const RELIGION_DROPDOWN_OPTIONS: DropdownOptions<Religion> = [
    {
        value: 'JUDAISM',
        label: 'Judentum',
    },
    {
        value: 'CHRISTIANITY',
        label: 'Christentum',
    },
    {
        value: 'ISLAM',
        label: 'Islam',
    },
    {
        value: 'HINDUISM',
        label: 'Hinduismus',
    },
    {
        value: 'BUDDHISM',
        label: 'Buddhismus',
    },
    {
        value: 'OTHER',
        label: 'Sonstige',
    },
    {
        value: 'NO_FAITH',
        label: 'Kein Glaube',
    },
];
