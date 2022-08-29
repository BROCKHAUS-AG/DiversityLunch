import React, { FC, useState } from 'react';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { GenericList } from '../Shared/GenericList/GenericList';
import { Project } from './Project';
import { IconButton } from '../Shared/Controlls/IconButton';
import iconMeeting from '../../resources/icons/icon-anstehende-termine.svg';
import { AdminPanelListItem } from './admin-panel-list-item';


const data: Project[] = [{
  id: 0,
  descriptor: 'hello',
}, {
  id: 2,
  descriptor: 'world',
}];

function updateProjectDescriptor(projects: Project[], project: Project): Project[] {
  return [...projects.map((p) => (p.id === project.id ? { ...p, descriptor: 'ja lol ey' } : p))];
}

export const AdminPanel: FC = () => {
  const [projects, setProjects] = useState(data);

  const removeProject = (project: Project) => {
    setProjects(projects.filter((p) => p.id !== project.id));
  };
  const updateProject = (project: Project) => {
    setProjects(updateProjectDescriptor(projects, project));
  };

  return (
    <section className="view">
      <CloseSiteContainer />
      <DiversityIconContainer title="ADMIN PANEL" />

      <IconButton iconPath={iconMeeting} onClick={() => console.log('halo')} altText="Alt" />
      <GenericList
        data={projects}
        getKeyFunction={(item) => item.id}
        itemComponent={AdminPanelListItem}
        itemComponentProps={{ onEditClicked: (p: Project) => updateProject(p), onRemoveClicked: (p: Project) => removeProject(p) }}
      />
    </section>
  );
};
