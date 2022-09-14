import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { IdentifiableState } from '../../data/generic/GenericSlice';
import { Identifiable } from '../../data/generic/Identifiable';
import { GenericFetch } from '../../data/generic/GenericFetch';
import '../../styles/component-styles/questions/dropDown.scss';

interface dropdownProps<T extends Identifiable> {
    data: IdentifiableState<T>;
    fetch: GenericFetch<T>;
    text: string;
}

export const Dropdown = <T extends Identifiable>({ data, fetch, text }: dropdownProps<T>) => {
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(fetch.getAll());
    }, []);
    return (
        <div className="questionSelection">
            <p>{text}</p>
            <select>
                <option selected disabled />
                {data.items.map((item: any) => <option key={item.id}>{item.descriptor}</option>)}
            </select>
        </div>
    );
};
