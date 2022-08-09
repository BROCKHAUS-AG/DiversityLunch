import { FC } from 'react';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { GenericList } from '../Shared/GenericList/GenericList';
import { Project } from './Project';

interface AdminPanelListItemProp {
  item: Project
}

const AdminPanelListItem: FC<AdminPanelListItemProp>
  = ({ item: project }: AdminPanelListItemProp) => <p>{project.descriptor}</p>;

const data: Project[] = [{
  id: 0,
  descriptor: 'hello',
}, {
  id: 2,
  descriptor: 'world',
}];

export const AdminPanel: FC = () => (
  <section>
    <header className="Profile-logo-container">
      <CloseSiteContainer />
      <DiversityIconContainer title="ADMIN PANEL" />
    </header>
    <main>
      <GenericList
        data={data}
        getKeyFunction={(item) => item.id}
        itemComponent={AdminPanelListItem}
      />
    </main>
  </section>
);
