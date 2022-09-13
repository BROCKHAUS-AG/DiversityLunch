import React, { useState } from 'react';
import { DropdownOption, DropdownOptions } from '../../types/dropdownOptions/dropdown-options.type';
import { Profile } from '../../types/Profile';
import iconCheck from '../../resources/icons/icon-check.svg';
import iconEdit from '../../resources/icons/icon-edit.svg';
import { GenerateGenericDropdown } from '../Questions/GenericDropdown';
import '../../styles/component-styles/profile/characteristic.scss';

type CharacterProps<T> = {
  showingAttribute: string;
  options: DropdownOptions<T>,
  currentValue: T,
  attribute: keyof Profile,
  // eslint-disable-next-line no-unused-vars
  changeCurrentFormState<KEY extends keyof Profile>(key: KEY, value: Profile[KEY]): void;
};

export function GenericCharacteristic<T>(): React.FC<CharacterProps<T>> {
    const [chosen, setChosen] = useState(false);

    const changeIcon = (): void => {
        setChosen(!chosen);
    };

    return function Character(props: CharacterProps<T>) {
        const {
            showingAttribute,
            options,
            currentValue,
            changeCurrentFormState,
            attribute,
        } = props;

        const Dropdown = GenerateGenericDropdown<T>();

        const onChangeValue = (value: T | undefined) => {
            if (value != null) {
                changeCurrentFormState(attribute, (value) as unknown as number | string);
            }
        };

        const currentOption = options.find((option) => option.value === currentValue);

        const optionToString = (option: DropdownOption<T> | undefined) => {
            if (option === undefined) {
                return '';
            }

            return option.label;
        };

        return (
            <div className="Characteristic">
                <div className="Characteristic-left-container">
                    <h6 className="Characteristic-attribute">{showingAttribute}</h6>
                    {
                        chosen
                            ? (
                                <Dropdown
                                    label=""
                                    options={options}
                                    currentValue={currentValue}
                                    onChange={onChangeValue}
                                    placeholder=""
                                />
                            )
                            : <p className="Characteristic-value">{optionToString(currentOption)}</p>
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
                            : <img alt="icon_edit" className="Characteristic-icons" src={iconEdit} />
                    }
                </div>
            </div>
        );
    };
}
