import React, { ChangeEvent, useEffect, useState } from 'react';
import { Identifiable } from '../../../data/generic/Identifiable';

interface AdminPanelListItemProp<T extends Identifiable> {
  item: T,
  onEditClicked: (_: T)=>void,
  onRemoveClicked: (_: T)=>void
}

export const EditFormField = <T extends Identifiable>({ item, onEditClicked, onRemoveClicked }: AdminPanelListItemProp<T>) => {
    const [input, setInput] = useState(item.descriptor);
    const [isButtonActive, setIsButtonActive] = useState(false);
    const [isDefaultValue, setIsDefaultValue] = useState(false);

    useEffect(() => {
        // TODO: "keine Angabe" zu z.B. item.default ändern, warten auf BE - fabio 21.12.2022
        if (item.descriptor.toLowerCase() === 'keine angabe') {
            setIsDefaultValue(true);
        }
        setIsButtonActive(false);
        setInput(item.descriptor);
    }, [item.descriptor]);

    const inputChangedHandler = (e : ChangeEvent<HTMLInputElement>) => {
        setIsButtonActive(e.target.value !== item.descriptor);
        setInput(e.target.value);
    };

    const updateClickHandler = () => {
        onEditClicked({ id: item.id, descriptor: input } as T);
    };

    return (
        <article>
            {
                isDefaultValue
                    ? (
                        <input type="text" value={input} />
                    )
                    : (
                        <>
                            <input type="text" value={input} onChange={inputChangedHandler} data-testid={`${item.id}`} />
                            {isButtonActive && (
                                <button type="button" onClick={updateClickHandler}>Speichern</button>
                            )}

                            <button type="button" onClick={() => onRemoveClicked(item)}>Löschen</button>
                        </>
                    )
            }
        </article>
    );
};
