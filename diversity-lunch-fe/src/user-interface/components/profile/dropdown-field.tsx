import React, { ChangeEvent } from 'react';
import '../../../styles/component-styles/questions/dropdownQuestion.scss';
import '../../../styles/component-styles/questions/dropdownStyle.scss';

import TextField from '@material-ui/core/TextField';
import Autocomplete, { AutocompleteRenderInputParams } from '@material-ui/lab/Autocomplete';
import { Profile } from '../../../model/Profile';

type DropdownFieldProps = {
    defaultOption: string;
    options: string[];
  // eslint-disable-next-line no-unused-vars
    changeCurrentFormState<KEY extends keyof Profile>(key: KEY, value: Profile[KEY]): void;
    attribute: keyof Profile;
  // eslint-disable-next-line no-unused-vars
    changeCurrentValue(newValue: string):void;
}

export const DropdownField = (props: DropdownFieldProps) => {
    const {
        defaultOption, options, changeCurrentFormState, attribute, changeCurrentValue,
    } = props;

    const onChangeValue = (event: React.ChangeEvent<any>, value: string | null) => {
        if (value != null) {
            changeCurrentFormState(attribute, value);
            changeCurrentValue(value);
        } else {
            changeCurrentFormState(attribute, defaultOption);
        }
    };

    return (
        <div className="DropdownQuestion">
            <Autocomplete
                id="combo-box-demo"
                options={options}
                renderInput={
                    (params: AutocompleteRenderInputParams) =>
                    // eslint-disable-next-line react/jsx-props-no-spreading,implicit-arrow-linebreak
                        <TextField {...params} variant="outlined" />
                }
                onChange={(event: ChangeEvent<{}>, value) => onChangeValue(event, value)}
                defaultValue={defaultOption}
            />
        </div>
    );
};
