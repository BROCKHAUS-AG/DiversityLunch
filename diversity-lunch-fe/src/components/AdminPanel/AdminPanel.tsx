import React, { FC, useState } from 'react';
import { useSelector } from 'react-redux';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { GenericList } from '../Shared/GenericList/GenericList';
import { Project } from './Project';
import { AdminPanelListItem } from './admin-panel-list-item';
import { AppStoreState } from '../../store/Store';
import { Role } from '../../model/Role';

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
    const accountState = useSelector((store: AppStoreState) => store.account);
    const [projects, setProjects] = useState(data);

    if (accountState.status === 'OK') {
        if (accountState.accountData.role !== Role.ADMIN && accountState.accountData.role !== Role.AZURE_ADMIN) {
            return (<p>You are not an admin!</p>);
        }
    } else {
        return (<p>Error</p>);
    }

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
            <GenericList
                data={projects}
                getKeyFunction={(item) => item.id}
                itemComponent={AdminPanelListItem}
                itemComponentProps={{ onEditClicked: (p: Project) => updateProject(p), onRemoveClicked: (p: Project) => removeProject(p) }}
            />
        </section>
    );
};
