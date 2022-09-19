import React, { ChangeEvent } from 'react';
import { Identifiable } from '../../data/generic/Identifiable';
import '../../styles/component-styles/questions/dropDown.scss';

interface dropdownProps<T extends Identifiable> {
    options: T[];
    text: string;
    onChange: (selected : T) => void;
}

export const Dropdown = <T extends Identifiable>({ options, text, onChange }: dropdownProps<T>) => {
    const optionSelected = (event : React.ChangeEvent<HTMLSelectElement>) => {
        const result = options.find((e) => e.id === +event.target.value);
        if (result) onChange(result);
    };

    return (
        <div className="questionSelection">
            <label htmlFor={text}>
                {text}
                <select name={text} id={text} defaultValue="" onChange={optionSelected}>
                    <option disabled value="">&nbsp;</option>
                    {options.map((item) => <option key={item.id} value={item.id}>{item.descriptor}</option>)}
                </select>
            </label>
        </div>
    );
};
