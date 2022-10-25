import React, { ChangeEvent, useState } from 'react';
import { useDispatch } from 'react-redux';
import { AdminPanelListItem } from './admin-panel-list-item';
import { IdentifiableState } from '../../data/generic/GenericSlice';
import { Identifiable } from '../../data/generic/Identifiable';
import { GenericFetch } from '../../data/generic/GenericFetch';

interface OptionsListProps<T extends Identifiable> {
    state: IdentifiableState<T>,
    fetch: GenericFetch<T>,
    title: string,
    addButtonLabel: string,
}

export const OptionsList = <T extends Identifiable>(
    {
        state, fetch, title, addButtonLabel,
    } : OptionsListProps<T>) => {
    const [inputText, setInputText] = useState('');
    const dispatch = useDispatch();

    const removeProject = (object: T) => {
        dispatch(fetch.removeById(object.id));
    };
    const updateProject = (object: T) => {
        dispatch(fetch.put(object));
    };

    const addProject = (descriptor: string) => {
        dispatch(fetch.post({
            id: 0,
            descriptor,
        } as T));
        setInputText('');
    };

    return (
        <div className="optionsListContainer">
            <p className="editListTitle">{title}</p>
            <div>
                <label>
                    <p>Bezeichner:</p>
                    <input type="text" value={inputText} onChange={(e : ChangeEvent<HTMLInputElement>) => setInputText(e.target.value)} />
                </label>
                <button type="button" onClick={() => addProject(inputText)}>{addButtonLabel}</button>
                {state.items.map((current : T) => (
                    <AdminPanelListItem
                        item={current}
                        onEditClicked={(p: T) => updateProject(p)}
                        onRemoveClicked={(p: T) => removeProject(p)}
                        key={current.id}
                    />
                ))}
            </div>
        </div>
    );
};
