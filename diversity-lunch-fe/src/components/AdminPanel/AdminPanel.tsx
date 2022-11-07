import React, {
    FC, useEffect, useState,
} from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { AppStoreState } from '../../store/Store';
import { Role } from '../../model/Role';
import { projectFetch } from '../../data/project/project-fetch';
import { OptionsList } from './OptionsList';
import { hobbyFetch } from '../../data/hobby/fetch-hobby';
import { UserList } from './user-list';
import { authenticatedFetchPost } from '../../utils/fetch.utils';

export const AdminPanel: FC = () => {
    const accountState = useSelector((store: AppStoreState) => store.account);
    const projectState = useSelector((store: AppStoreState) => store.project);
    const hobbyState = useSelector((store: AppStoreState) => store.hobby);
    const [emailState, setEmailSuccess] = useState(false);
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(projectFetch.getAll());
        dispatch(hobbyFetch.getAll());
    }, []);
    useEffect(() => {
        if (emailState) {
            window.alert('Erfolgreich gesendet');
        }
    }, [emailState]);

    if (accountState.status === 'OK') {
        if (accountState.accountData.role !== Role.ADMIN && accountState.accountData.role !== Role.AZURE_ADMIN) {
            return (
                <section>
                    <p>Du bist kein Admin</p>
                    <Link to="/dashboard">Zurück zum Dashboard</Link>
                </section>
            );
        }
    } else {
        return (
            <section>
                <p>Dein Account konnte nicht abgerufen werden</p>
                <Link to="/dashboard">Zurück zum Dashboard</Link>
            </section>
        );
    }
    const sendTestmail = async () => {
        const result: Response = await authenticatedFetchPost(`/api/mailing/sendTestMailToUser/${accountState.accountData.profileId}`, '');
        setEmailSuccess(result.status === 200);
    };
    return (
        <section className="view">
            <CloseSiteContainer />
            <DiversityIconContainer title="ADMIN PANEL" />
            <UserList />
            <OptionsList state={projectState} fetch={projectFetch} title="Projektliste anpassen" addButtonLabel="Projekt hinzufügen" />

            <OptionsList state={hobbyState} fetch={hobbyFetch} title="Hobbyliste anpassen" addButtonLabel="Hobby hinzufügen" />
            <button onClick={sendTestmail}>Testmail verschicken</button>
        </section>

    );
};
