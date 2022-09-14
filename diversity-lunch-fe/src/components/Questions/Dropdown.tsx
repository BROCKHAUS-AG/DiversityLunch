import React from 'react';
import { Identifiable } from '../../data/generic/Identifiable';
import '../../styles/component-styles/questions/dropDown.scss';

interface dropdownProps<T extends Identifiable> {
    data: T[];
    text: string;
    onChange: () => void;
}

export const Dropdown = <T extends Identifiable>({ data, text, onChange }: dropdownProps<T>) => (
    <div className="questionSelection">
        <label htmlFor={text}>
            {text}
            <select name={text} id={text} defaultValue="" onChange={onChange}>
                <option disabled value="">&nbsp;</option>
                {data.map((item) => <option key={item.id}>{item.descriptor}</option>)}
            </select>
        </label>
    </div>
);
