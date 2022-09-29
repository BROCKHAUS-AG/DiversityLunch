import React, { ChangeEvent, useEffect, useState } from 'react';
import { Identifiable } from '../../data/generic/Identifiable';

interface AdminPanelListItemProp<T extends Identifiable> {
  item: T,
  onEditClicked: (_: T)=>void,
  onRemoveClicked: (_: T)=>void
}

export const AdminPanelListItem = <T extends Identifiable>({ item: project, onEditClicked, onRemoveClicked }: AdminPanelListItemProp<T>) => {
    const [input, setInput] = useState(project.descriptor);
    const [saveButtonActive, setSaveButtonActive] = useState(false);

    useEffect(() => {
        setSaveButtonActive(false);
        setInput(project.descriptor);
    }, [project.descriptor]);

    const inputChangedHandler = (e : ChangeEvent<HTMLInputElement>) => {
        setSaveButtonActive(e.target.value !== project.descriptor);
        setInput(e.target.value);
    };

    const updateClickHandler = () => {
        onEditClicked({ id: project.id, descriptor: input } as T);
    };

    return (
        <article>
            <input type="text" value={input} onChange={inputChangedHandler} />
            {saveButtonActive && (
                <button type="button" onClick={updateClickHandler}>Speichern</button>
            )}

            <button type="button" onClick={() => onRemoveClicked(project)}>Löschen</button>
        </article>
    );
};
