import React, { FC } from 'react';
import { IconButton } from '../Shared/Controlls/IconButton';
import iconMeeting from '../../resources/icons/icon-anstehende-termine.svg';
import { Project } from './Project';

interface AdminPanelListItemProp {
  item: Project,
  onEditClicked: (_: Project)=>void,
  onRemoveClicked: (_: Project)=>void
}

export const AdminPanelListItem: FC<AdminPanelListItemProp> = ({ item: project, onEditClicked, onRemoveClicked }: AdminPanelListItemProp) => (
    <article>
        <span>{project.descriptor}</span>
        <IconButton iconPath={iconMeeting} altText="Projekt bearbeiten" onClick={() => onEditClicked(project)} text="bearbeiten" />
        <IconButton iconPath={iconMeeting} altText="Projekt löschen" onClick={() => onRemoveClicked(project)} text="löschen" />
    </article>
);
