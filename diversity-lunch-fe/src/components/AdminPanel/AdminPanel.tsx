import React from 'react';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { IconList } from '../Shared/IconList/IconList';
import { Project } from './Project';

interface AdminPanelListItemProp {
  project: Project
}

const AdminPanelListItem = ({ project }: AdminPanelListItemProp) => <p>{project.descriptor}</p>;

export const AdminPanel: React.FC = () => (
  <section>
    <header className="Profile-logo-container">
      <CloseSiteContainer />
      <DiversityIconContainer title="ADMIN PANEL" />
    </header>
    <main>
      <IconList
        data={[{
          id: 0,
          descriptor: 'hello',
        }, {
          id: 2,
          descriptor: 'world',
        }]}
        getKeyFunction={(item) => item.id}
        itemComponent={AdminPanelListItem}
      />
    </main>
  </section>
);
