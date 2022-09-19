import { ChangeEvent } from 'react';
import { Identifiable } from '../../data/generic/Identifiable';
import '../../styles/component-styles/questions/dropDown.scss';

interface dropdownProps<T extends Identifiable> {
    options: T[];
    label: string;
    onChange: (selected : T) => void;
}

export const Dropdown = <T extends Identifiable>({ options, label, onChange }: dropdownProps<T>) => {
    const optionSelected = (event : ChangeEvent<HTMLSelectElement>) => {
        const result = options.find((e) => e.id === +event.target.value);
        if (result) onChange(result);
    };

    return (
        <div className="questionSelection">
            <label htmlFor={label}>
                {label}
                <select name={label} id={label} defaultValue="" onChange={optionSelected}>
                    <option disabled value="">&nbsp;</option>
                    {options.map((item) => <option key={item.id} value={item.id}>{item.descriptor}</option>)}
                </select>
            </label>
        </div>
    );
};
