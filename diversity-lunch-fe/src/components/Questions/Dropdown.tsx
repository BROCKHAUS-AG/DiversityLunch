import React, { ChangeEvent } from 'react';
import TextField from '@material-ui/core/TextField';
import Autocomplete, { AutocompleteRenderInputParams } from '@material-ui/lab/Autocomplete';
import { Identifiable } from '../../data/generic/Identifiable';
import '../../styles/component-styles/questions/dropDown.scss';

interface dropdownProps<T extends Identifiable> {
    options: T[];
    label: string;
    onChange: (selected : T) => void;
    placeholder: string;
    // eslint-disable-next-line react/require-default-props
    currentValue?: T;
}

export const Dropdown = <T extends Identifiable>({
    options, label, onChange, placeholder, currentValue,
}: dropdownProps<T>) => {
    const callOnChange = (_ : ChangeEvent<{}>, selected : T | null) => {
        if (!selected) return;
        onChange(selected);
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
};
