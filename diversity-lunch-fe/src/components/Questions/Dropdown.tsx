import React, { ChangeEvent } from 'react';
import { Identifiable } from '../../data/generic/Identifiable';
import '../../styles/component-styles/questions/dropDown.scss';
import Autocomplete, { AutocompleteRenderInputParams } from '@material-ui/lab/Autocomplete';
import TextField from '@material-ui/core/TextField';
import { DropdownOption } from '../../types/dropdownOptions/dropdown-options.type';

interface dropdownProps<T extends Identifiable> {
    options: T[];
    label: string;
    onChange: (selected : T) => void;
    placeholder: string;
    currentValue?: T;
}

// function dropdownOptionToIdentifiable<T>(option : DropdownOption<T>, options : T[]) {
//     const result = options.find((e) => e.id === option.value);
//     return {
//         id: option.value,
//         descriptor: option.label,
//     };
// }

export const Dropdown = <T extends Identifiable>({
    options, label, onChange, placeholder, currentValue,
}: dropdownProps<T>) => {
    const optionSelected = (event : ChangeEvent<HTMLSelectElement>) => {
        const result = options.find((e) => e.id === +event.target.value);
        if (result) onChange(result);
    };

    const dropDownOptionToIdentifiable = (option : DropdownOption<T>) => {
        const result = options.find((e) => e.id === +option.value);
        if (result) return result;
        return null;
    };

    const checkIfPresent = (option: DropdownOption<T> | undefined) => {
        const result = options.find((e) => e.id === +option.value);
        if (result) return result;
        return undefined;
    };

    const callOnChange = (_ : ChangeEvent<{}>, value : T | null) => {
        if (!value) return;
        onChange(value);
    };

    const currentViewValue = options.find((o) => o.id === currentValue?.id);

    return (
        <div className="DropdownQuestion">
            <p className="DropdownQuestion-question">{label}</p>
            <Autocomplete
                options={options}
                getOptionLabel={(o) => o.descriptor}
                renderInput={
                    // eslint-disable-next-line react/jsx-props-no-spreading
                    (params: AutocompleteRenderInputParams) => <TextField {...params} variant="outlined" label={placeholder} />
                }
                onChange={callOnChange}
                value={currentViewValue}
            />
        </div>
    );

    /* <div className="questionSelection">
        <label htmlFor={label}>
            {label}
            <select name={label} id={label} defaultValue="" onChange={optionSelected}>
                <option disabled value="">&nbsp;</option>
                {options.map((item) => <option key={item.id} value={item.id}>{item.descriptor}</option>)}
            </select>
        </label>
    </div> */
};
