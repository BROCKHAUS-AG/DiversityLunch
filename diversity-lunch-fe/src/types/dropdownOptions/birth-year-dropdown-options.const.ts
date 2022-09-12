import { DropdownOptions } from './dropdown-options.type';

// generate all years, starting 10 years in the past and going back 100 years then
const currentYear = new Date(Date.now()).getFullYear();
const tenYearsInThePast = currentYear - 10;

const arrayWithUndefinedValues = [...Array(100)];
const hundredYearsToThePast = arrayWithUndefinedValues.map((_, index) => tenYearsInThePast - index);

export const BIRTH_YEAR_DROPDOWN_OPTIONS: DropdownOptions<number> = hundredYearsToThePast
    .map((year) => ({
        value: year,
        label: `${year}`,
    }));
