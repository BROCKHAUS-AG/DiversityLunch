import React, { ChangeEvent, FC, useState } from 'react';
import { IconButton } from '../Shared/Controlls/IconButton';
import iconMeeting from '../../resources/icons/icon-anstehende-termine.svg';
import { Project } from './Project';

interface AdminPanelListItemProp {
  item: Project,
  onEditClicked: (_: Project)=>void,
  onRemoveClicked: (_: Project)=>void
}

export const AdminPanelListItem: FC<AdminPanelListItemProp> = ({ item: project, onEditClicked, onRemoveClicked }: AdminPanelListItemProp) => {
    const [input, setInput] = useState(project.descriptor);
    const [saveButtonActive, setSaveButtonActive] = useState(false);

    const inputChangedHandler = (e : ChangeEvent<HTMLInputElement>) => {
        setSaveButtonActive(e.target.value !== project.descriptor);
        setInput(e.target.value);
    };

    return (
        <article>
            <input type="text" value={input} onChange={inputChangedHandler} />
            {saveButtonActive && (
                <button type="button" onClick={() => onEditClicked({ id: project.id, descriptor: `${input}sus` })}>Speichern</button>
            )}

            <button type="button" onClick={() => onRemoveClicked(project)}>Löschen</button>
        </article>
    );
};
