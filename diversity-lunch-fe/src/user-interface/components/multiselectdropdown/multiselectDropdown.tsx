import React, { ChangeEvent } from 'react';
import TextField from '@material-ui/core/TextField';
import Autocomplete, { AutocompleteRenderInputParams } from '@material-ui/lab/Autocomplete';
import { Identifiable } from '../../../data/generic/Identifiable';
import './multiselectDropdownQuestions.scss';

interface dropdownProps<T extends Identifiable> {
    options: T[];
    placeholder: string;
    onChange: (selected : T) => void;
    label: string;
    // eslint-disable-next-line react/require-default-props
    currentValue?: T;
}

export const MultiselectDropdown = <T extends Identifiable>({
    options, placeholder, onChange, label, currentValue,
}: dropdownProps<T>) => {
    const callOnChange = (_ : ChangeEvent<{}>, selected : T | null) => {
        if (!selected) return;
        onChange(selected);
    };

    return (
        <div className="MultiselectDropdownQuestion">
            <p className="MultiselectDropdownQuestion-question">{placeholder}</p>
            <Autocomplete
                options={options}
                getOptionLabel={(o) => o.descriptor}
                renderInput={
                    // eslint-disable-next-line react/jsx-props-no-spreading
                    (params: AutocompleteRenderInputParams) => <TextField {...params} variant="outlined" label={label} />
                }
                onChange={callOnChange}
                defaultValue={currentValue}
                getOptionSelected={(option, value) => option.id === value.id}
            />
        </div>
    );
};
