import React, { FC, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { GenericList } from '../Shared/GenericList/GenericList';
import { Project } from './Project';
import { AdminPanelListItem } from './admin-panel-list-item';
import { AppStoreState } from '../../store/Store';
import { Role } from '../../model/Role';
import { projectFetch } from '../../data/project/project-fetch';

const data: Project[] = [{
    id: 0,
    descriptor: 'hello',
}, {
    id: 2,
    descriptor: 'world',
}];

function updateProjectDescriptor(projects: Project[], project: Project): Project[] {
    return [...projects.map((p) => (p.id === project.id ? { ...p, descriptor: project.descriptor } : p))];
}

export const AdminPanel: FC = () => {
    const accountState = useSelector((store: AppStoreState) => store.account);
    const projectState = useSelector((store: AppStoreState) => store.project);
    const dispatch = useDispatch();
    useEffect(() => { dispatch(projectFetch.getAll()); }, []);
    if (accountState.status === 'OK') {
        if (accountState.accountData.role !== Role.ADMIN && accountState.accountData.role !== Role.AZURE_ADMIN) {
            return (<p>You are not an admin!</p>);
        }
    } else {
        return (<p>Error</p>);
    }

    const removeProject = (project: Project) => {
        dispatch(projectFetch.removeById(project.id));
    };
    const updateProject = (project: Project) => {
        dispatch(projectFetch.put(project));
    };

    return (
        <section className="view">
            <CloseSiteContainer />
            <DiversityIconContainer title="ADMIN PANEL" />
            <GenericList
                data={projectState.items}
                getKeyFunction={(item) => item.id}
                itemComponent={AdminPanelListItem}
                itemComponentProps={{ onEditClicked: (p: Project) => updateProject(p), onRemoveClicked: (p: Project) => removeProject(p) }}
            />
        </section>
    );
};
