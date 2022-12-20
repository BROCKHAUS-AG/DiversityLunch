import React, { ChangeEvent, useState } from 'react';
import { useDispatch } from 'react-redux';
import { EditFormField } from '../edit-form-field/edit-form-field';
import { IdentifiableState } from '../../../data/generic/GenericSlice';
import { Identifiable } from '../../../data/generic/Identifiable';
import { GenericFetch } from '../../../data/generic/GenericFetch';

interface OptionsListProps<T extends Identifiable> {
    state: IdentifiableState<T>,
    fetch: GenericFetch<T>,
    title: string,
    addButtonLabel: string,
}

export const IdentifiableOptionsList = <T extends Identifiable>(
    {
        state, fetch, title, addButtonLabel,
    } : OptionsListProps<T>) => {
    const [inputText, setInputText] = useState('');
    const dispatch = useDispatch();

    const remove = (object: T) => {
        dispatch(fetch.removeById(object.id, { onNetworkError: console.error, statusCodeHandlers: {} }));
        // TODO: Handle network and http errors properly tgohlisch 17.11.2022
    };
    const update = (object: T) => {
        dispatch(fetch.put(object, { onNetworkError: console.error, statusCodeHandlers: {} }));
        // TODO: Handle network and http errors properly tgohlisch 17.11.2022);
    };

    const add = (descriptor: string) => {
        dispatch(fetch.post({
            id: 0,
            descriptor,
        } as T, { onNetworkError: console.error, statusCodeHandlers: {} })); // TODO: Handle network and http errors properly tgohlisch 17.11.2022;
        setInputText('');
    };

    return (
        <div className="optionsListContainer">
            <p className="editListTitle">{title}</p>
            <div>
                <details>
                    <summary className="editListTitle">
                        Projektliste anpassen
                    </summary>
                    <br />
                    <section id="searchContainer">
                        <label>
                            <p className="customizeDimensionHeader">Frage: In welchem Projekt arbeitest du derzeit?</p>
                            <input type="text" value={inputText} onChange={(e : ChangeEvent<HTMLInputElement>) => setInputText(e.target.value)} />
                        </label>
                        <button type="button" onClick={() => add(inputText)}>{addButtonLabel}</button>
                        {state.items.map((current : T) => (
                            <EditFormField
                                item={current}
                                onEditClicked={(p: T) => update(p)}
                                onRemoveClicked={(p: T) => remove(p)}
                                key={current.id}
                            />
                        ))}
                    </section>
                </details>
            </div>
        </div>
    );
};
