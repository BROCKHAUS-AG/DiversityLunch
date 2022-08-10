import { FC, useState } from 'react';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { GenericList } from '../Shared/GenericList/GenericList';
import { Project } from './Project';
import { IconButton } from '../Shared/Controlls/IconButton';
import iconMeeting from '../../resources/icons/icon-anstehende-termine.svg';

interface AdminPanelListItemProp {
  item: Project
}

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

  const AdminPanelListItem: FC<AdminPanelListItemProp> = ({ item: project }: AdminPanelListItemProp) => (
    <article>
      <span>{project.descriptor}</span>
      <IconButton iconPath={iconMeeting} altText="Projekt bearbeiten" onClick={() => updateProject(project)} text="bearbeiten" />
      <IconButton iconPath={iconMeeting} altText="Projekt löschen" onClick={() => removeProject(project)} text="löschen" />
    </article>
  );

  return (
    <section>
      <header className="Profile-logo-container">
        <CloseSiteContainer />
        <DiversityIconContainer title="ADMIN PANEL" />
      </header>
      <main>

        <IconButton iconPath={iconMeeting} onClick={() => console.log('halo')} altText="Alt" />
        <GenericList
          data={projects}
          getKeyFunction={(item) => item.id}
          itemComponent={AdminPanelListItem}
        />
      </main>
    </section>
  );
};
