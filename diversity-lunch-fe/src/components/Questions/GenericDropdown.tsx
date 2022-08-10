import React from 'react';
import Autocomplete, { AutocompleteRenderInputParams } from '@material-ui/lab/Autocomplete';
import TextField from '@material-ui/core/TextField';
import { DropdownOption, DropdownOptions } from '../../types/dropdownOptions/dropdown-options.type';

type GenericDropdownProps<T> = {
  label: string,
  options: DropdownOptions<T>,
  // eslint-disable-next-line react/require-default-props
  currentValue?: T,
  // eslint-disable-next-line no-unused-vars
  onChange: (value: T | undefined) => void,
  placeholder: string,
};

export function GenerateGenericDropdown<T>(): React.FC<GenericDropdownProps<T>> {
  // eslint-disable-next-line react/display-name
  return function GenericDropdown(props: GenericDropdownProps<T>) {
    const {
      label,
      options,
      currentValue,
      onChange,
      placeholder,
    } = props;

    const currentViewValue = options.find((o) => o.value === currentValue);

    return (
      <div className="DropdownQuestion">
        <p className="DropdownQuestion-question">{label}</p>
        <Autocomplete
          options={options}
          getOptionLabel={(o) => o.label}
          renderInput={
            (params: AutocompleteRenderInputParams) =>
              <TextField {...params} variant="outlined" label={placeholder} />
          }
          onChange={(_, value: DropdownOption<T> | null) => onChange(value?.value)}
          value={currentViewValue}
        />
      </div>
    );
  };
}
