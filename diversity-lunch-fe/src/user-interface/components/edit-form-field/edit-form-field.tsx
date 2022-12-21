import React, { ChangeEvent, useEffect, useState } from 'react';
import { Identifiable } from '../../../data/generic/Identifiable';

interface AdminPanelListItemProp<T extends Identifiable> {
  item: T,
  onEditClicked: (_: T)=>void,
  onRemoveClicked: (_: T)=>void
}

export const EditFormField = <T extends Identifiable>({ item, onEditClicked, onRemoveClicked }: AdminPanelListItemProp<T>) => {
    const [input, setInput] = useState(item.descriptor);
    const [saveButtonActive, setSaveButtonActive] = useState(false);
    const [defaultValue, setDefaultValue] = useState(false);

    useEffect(() => {
        if (item.descriptor === 'keine Angabe') {
            setDefaultValue(true);
        }
        setSaveButtonActive(false);
        setInput(item.descriptor);
    }, [item.descriptor]);

    const inputChangedHandler = (e : ChangeEvent<HTMLInputElement>) => {
        setSaveButtonActive(e.target.value !== item.descriptor);
        setInput(e.target.value);
    };

    const updateClickHandler = () => {
        onEditClicked({ id: item.id, descriptor: input } as T);
    };

    return (
        <article>
            {
                defaultValue
                    ? (
                        <input type="text" value={input} />
                    )
                    : (
                        <>
                            <input type="text" value={input} onChange={inputChangedHandler} data-testid={`${item.id}`} />
                            {saveButtonActive && (
                                <button type="button" onClick={updateClickHandler}>Speichern</button>
                            )}

                            <button type="button" onClick={() => onRemoveClicked(item)}>Löschen</button>
                        </>
                    )
            }
        </article>
    );
};
