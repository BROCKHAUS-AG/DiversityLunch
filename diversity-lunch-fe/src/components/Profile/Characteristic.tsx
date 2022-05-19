import React, { useState } from 'react';

import '../../styles/component-styles/profile/characteristic.scss';
import iconEdit from '../../resources/icons/icon-edit.svg';
import iconCheck from '../../resources/icons/icon-check.svg';
import { Profile } from '../../types/Profile';
import { DropdownField } from './DropdownField';
import { GenerateGenericDropdown } from '../Questions/GenericDropdown';
import { Gender } from '../../types/enums/gender.type';
import { GENDER_DROPDOWN_OPTIONS } from '../../types/dropdownOptions/gender-dropdown-options';
import { GenericCharacteristic } from './GenericCharacteristic';

type CharacteristicProps = {
  defaultOption: string;
  options: string[];
  // eslint-disable-next-line no-unused-vars
  changeCurrentFormState<KEY extends keyof Profile>(key: KEY, value: Profile[KEY]): void;
  attribute: keyof Profile;
  showingAttribute: string | number;
}

export const Characteristic: React.FC<CharacteristicProps> = (props: CharacteristicProps) => {
  const {
    attribute,
    changeCurrentFormState,
    showingAttribute,
    defaultOption,
    options,
  } = props;

  const [currentValue, setCurrentValue] = useState(defaultOption);
  const [chosen, setChosen] = useState(false);
  GenerateGenericDropdown<Gender>();
  const GenderDropdownV2 = GenericCharacteristic<Gender>();

  const onChangeCurrentValue = (newValue: string) => {
    setCurrentValue(newValue);
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
              <DropdownField
                defaultOption={defaultOption}
                options={options}
                changeCurrentFormState={changeCurrentFormState}
                attribute={attribute}
                changeCurrentValue={onChangeCurrentValue}
              />
            )
            : <h1 className="Characteristic-value">{currentValue}</h1>

        }

        {
          chosen
            ? (
              <GenderDropdownV2
                showingAttribute="Geschlecht"
                options={GENDER_DROPDOWN_OPTIONS}
                currentValue={currentValue as Gender}
                changeCurrentFormState={changeCurrentFormState}
                attribute={attribute}
              />
            )
            : (
              <h1 className="Characteristic-value">
                {GENDER_DROPDOWN_OPTIONS.find((o) => o.value === currentValue)?.label}
              </h1>
            )
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
