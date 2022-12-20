import React, { FC, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { CloseSite } from '../../components/close-site/close-site';
import { DiversityIcon } from '../../components/diversity-icon/diversity-icon';
import { AppStoreState } from '../../../data/app-store';
import { Role } from '../../../model/Role';
import { projectFetch } from '../../../data/project/project-fetch';
import { IdentifiableOptionsList } from '../../components/identifiable-options-list/identifiable-options-list';
import { hobbyFetch } from '../../../data/hobby/fetch-hobby';
import { UserList } from '../../components/user-list/user-list';
import { VoucherUpload } from '../../components/voucher-upload/voucher-upload';
import { authenticatedFetchPost } from '../../../utils/fetch.utils';
import { PopUp } from '../../components/pop-up/pop-up';
import './admin-panel.scss';

export const AdminPanel: FC = () => {
    const accountState = useSelector((store: AppStoreState) => store.account);
    const projectState = useSelector((store: AppStoreState) => store.project);
    const [emailSuccess, setEmailSuccess] = useState(false);
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(projectFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        // TODO: Handle network and http errors properly tgohlisch 17.11.2022
        dispatch(hobbyFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        // TODO: Handle network and http errors properly tgohlisch 17.11.2022
    }, []);

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

            <div className="adminPanelContainer">
                <div className="header">
                    <CloseSite />
                    <DiversityIcon title="ADMIN PANEL" />
                </div>

                <div className="bottom">
                    <UserList />

                    <div className="optionsListContainer">
                        <div>
                            <details>
                                <summary className="editListTitle">
                                    Profilangaben
                                </summary>
                                <section>
                                    <IdentifiableOptionsList
                                        state={projectState}
                                        fetch={projectFetch}
                                        title="Projektliste anpassen"
                                        header="Frage: In welchem Projekt arbeitest du derzeit?"
                                        addButtonLabel="Projekt hinzufügen"
                                    />
                                </section>
                            </details>
                        </div>
                    </div>

                    <VoucherUpload />

                    <div className="testMailContainer">
                        <button className="testmailButton" onClick={sendTestmail}>Testmail verschicken</button>
                    </div>
                    {emailSuccess && <PopUp onButtonClick={() => { setEmailSuccess(false); }} message="Testmail gesendet" buttonText="Okay" />}
                </div>
            </div>
        </section>

    );
};
