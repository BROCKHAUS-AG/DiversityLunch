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
    header: string,
    addButtonLabel: string,
}

export const IdentifiableOptionsList = <T extends Identifiable>(
    {
        state, fetch, title, header, addButtonLabel,
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
        // TODO: "keine Angabe" zu z.B. item.default ändern, warten auf BE - fabio 22.12.2022
        // Default Value darf nicht öfters hinzugefügt werden
        if (descriptor.toLowerCase() !== 'keine angabe') {
            // TODO: Handle network and http errors properly tgohlisch 17.11.2022;
            dispatch(fetch.post({
                id: 0,
                descriptor,
            } as T, { onNetworkError: console.error, statusCodeHandlers: {} }));
            setInputText('');
        }
    };

    return (
        <div className="optionsListContainer">
            <div>
                <details>
                    <summary className="editListTitle">
                        {title}
                    </summary>
                    <br />
                    <section id="searchContainer">
                        <label>
                            <p className="customizeDimensionHeader">
                                {header}
                            </p>
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
