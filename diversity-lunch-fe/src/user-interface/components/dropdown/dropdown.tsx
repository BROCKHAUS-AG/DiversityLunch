import React, { ChangeEvent } from 'react';
import TextField from '@material-ui/core/TextField';
import Autocomplete, { AutocompleteRenderInputParams } from '@material-ui/lab/Autocomplete';
import { Identifiable } from '../../../data/generic/Identifiable';
import './dropdown.scss';
import './dropdownQuestions.scss';

interface dropdownProps<T extends Identifiable> {
    options: T[];
    placeholder: string;
    onChange: (selected : T) => void;
    label: string;
    // eslint-disable-next-line react/require-default-props
    currentValue?: T;
}

export const Dropdown = <T extends Identifiable>({
    options, placeholder, onChange, label, currentValue,
}: dropdownProps<T>) => {
    const callOnChange = (_ : ChangeEvent<{}>, selected : T | null) => {
        if (!selected) return;
        onChange(selected);
    };

    return (
        <div className="DropdownQuestion">
            <p className="DropdownQuestion-question">{placeholder}</p>
            <Autocomplete
                options={options}
                getOptionLabel={(o) => o.descriptor}
                renderInput={
                    // eslint-disable-next-line react/jsx-props-no-spreading
                    (params: AutocompleteRenderInputParams) => <TextField {...params} variant="outlined" label={label} />
                }
                onChange={callOnChange}
                defaultValue={currentValue}
                value={currentValue}
                getOptionSelected={(option, value) => option.id === value.id}
            />
        </div>
    );
};
