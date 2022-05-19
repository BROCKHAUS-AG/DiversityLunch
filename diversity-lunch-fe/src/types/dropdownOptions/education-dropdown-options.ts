import { DropdownOptions } from './dropdown-options.type';
import { Education } from '../enums/education.type';

export const EDUCATION_DROPDOWN_OPTIONS: DropdownOptions<Education> = [
  {
    value: 'APPRENTICESHIP',
    label: 'Ausbildung',
  },
  {
    value: 'STUDY',
    label: 'Studium',
  },
  {
    value: 'OTHER',
    label: 'Andere Wege',
  },
];
