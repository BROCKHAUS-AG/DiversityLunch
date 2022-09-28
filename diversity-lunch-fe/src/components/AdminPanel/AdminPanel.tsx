import React, {
    ChangeEvent, FC, useEffect, useState,
} from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { Project } from './Project';
import { AdminPanelListItem } from './admin-panel-list-item';
import { AppStoreState } from '../../store/Store';
import { Role } from '../../model/Role';
import { projectFetch } from '../../data/project/project-fetch';

export const AdminPanel: FC = () => {
    const accountState = useSelector((store: AppStoreState) => store.account);
    const projectState = useSelector((store: AppStoreState) => store.project);

    const [projectInput, setProjectInput] = useState('');

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
        console.log(projectState.items);
    };
    const updateProject = (project: Project) => {
        dispatch(projectFetch.put(project));
    };

    const addProject = (descriptor: string) => {
        dispatch(projectFetch.post({
            id: 21,
            descriptor,
        }));
        setProjectInput('');
    };

    return (
        <section className="view">
            <CloseSiteContainer />
            <DiversityIconContainer title="ADMIN PANEL" />
            {projectState.items.map((current : Project) => (
                <AdminPanelListItem
                    item={current}
                    onEditClicked={(p: Project) => updateProject(p)}
                    onRemoveClicked={(p: Project) => removeProject(p)}
                    key={current.id}
                />
            ))}
            <input type="text" value={projectInput} onChange={(e : ChangeEvent<HTMLInputElement>) => setProjectInput(e.target.value)} />
            <button type="button" onClick={() => addProject(projectInput)}>Projekt hinzufügen</button>
        </section>

    );
};
