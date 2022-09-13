import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { IdentifiableState } from '../../data/generic/GenericSlice';
import { Identifiable } from '../../data/generic/Identifiable';
import { GenericFetch } from '../../data/generic/GenericFetch';

interface dropdownProps<T extends Identifiable> {
    reducer: IdentifiableState<T>;
    fetch: GenericFetch<T>;
    text: string;
}

export const Dropdown = <T extends Identifiable>({ reducer, fetch, text }: dropdownProps<T>) => {
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(fetch.getAll());
    }, []);
    return (
        <div className="question">
            <div className="questionSelection">
                {text}
                <select>
                    {reducer.items.map((item: any) => <option key={item.id}>{item.descriptor}</option>)}
                </select>
            </div>
        </div>
    );
};
