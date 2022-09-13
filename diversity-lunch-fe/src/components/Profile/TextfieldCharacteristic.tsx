import React, { useState } from 'react';
import '../../styles/component-styles/profile/characteristic.scss';
import { TextField } from '@material-ui/core';
import iconEdit from '../../resources/icons/icon-edit.svg';
import iconCheck from '../../resources/icons/icon-check.svg';
import { Profile } from '../../types/Profile';

type CharacteristicProps = {
  defaultStartValue: string;
  // eslint-disable-next-line no-unused-vars
  changeCurrentFormState<KEY extends keyof Profile>(key: KEY, value: Profile[KEY]): void;
  attribute: keyof Profile;
  showingAttribute: string | number;
}

// eslint-disable-next-line max-len
export const TextfieldCharacteristic: React.FC<CharacteristicProps> = (props: CharacteristicProps) => {
    const {
        showingAttribute,
        defaultStartValue,
        changeCurrentFormState,
        attribute,
    } = props;

    const [currentValue, setCurrentValue] = useState(defaultStartValue);
    const [chosen, setChosen] = useState(false);

    const onChangeCurrentValue = (newValue: string) => {
        setCurrentValue(newValue);
        changeCurrentFormState(attribute, newValue);
    };

    const changeIcon = (): void => {
        setChosen(!chosen);
    };

    return (
        <div className="Characteristic">
            <div className="Characteristic-left-container">
                <h1 className="Characteristic-attribute">{showingAttribute}</h1>
                {

                    chosen
                        ? (
                            <TextField
                                defaultValue={defaultStartValue}
                                onChange={(event) => onChangeCurrentValue(event.target.value)}
                            />
                        )
                        : <h1 className="Characteristic-value">{currentValue}</h1>

                }
            </div>
            <div
                className="Characteristic-right-container"
                onClick={changeIcon}
                role="button"
            >
                {
                    chosen
                        ? (
                            <img
                                alt="icon_check"
                                className="Characteristic-icons"
                                src={iconCheck}
                            />
                        )
                        : (
                            <img
                                alt="icon_edit"
                                className="Characteristic-icons"
                                src={iconEdit}
                            />
                        )
                }
            </div>
        </div>
    );
};
