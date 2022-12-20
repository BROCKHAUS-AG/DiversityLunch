import React, { FC, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { CloseSite } from '../../components/close-site/close-site';
import { DiversityIcon } from '../../components/diversity-icon/diversity-icon';
import { AppStoreState } from '../../../data/app-store';
import { Role } from '../../../model/Role';
import { IdentifiableOptionsList } from '../../components/identifiable-options-list/identifiable-options-list';
import { projectFetch } from '../../../data/project/project-fetch';
import { genderFetch } from '../../../data/gender/fetch-gender';
import { UserList } from '../../components/user-list/user-list';
import { VoucherUpload } from '../../components/voucher-upload/voucher-upload';
import { authenticatedFetchPost } from '../../../utils/fetch.utils';
import { PopUp } from '../../components/pop-up/pop-up';
import './admin-panel.scss';
import { hobbyFetch } from '../../../data/hobby/fetch-hobby';
import { socialBackgroundFetch } from '../../../data/social-background/social-background-fetch';
import { languageFetch } from '../../../data/language/language-fetch';
import { religionFetch } from '../../../data/religion/religion-fetch';
import { workExperienceFetch } from '../../../data/work-experience/work-experience-fetch';
import { educationFetch } from '../../../data/education/fetch-education';
import { dietFetch } from '../../../data/diet/fetch-diet';
import { sexualOrientationFetch } from '../../../data/sexual-orientation/sexual-orientation-fetch';
import { countryFetch } from '../../../data/country/fetch-country';
import {
    socialBackgroundDiscriminationFetch,
} from '../../../data/social-background-discrimination/social-background-discrimination-fetch';

export const AdminPanel: FC = () => {
    const accountState = useSelector((store: AppStoreState) => store.account);
    const hobbyState = useSelector((store: AppStoreState) => store.hobby);
    const projectState = useSelector((store: AppStoreState) => store.project);
    const genderState = useSelector((store: AppStoreState) => store.gender);
    const countryState = useSelector((store: AppStoreState) => store.country);
    const languageState = useSelector((store: AppStoreState) => store.language);
    const religionState = useSelector((store: AppStoreState) => store.religion);
    const workExperienceState = useSelector((store: AppStoreState) => store.workExperience);
    const educationState = useSelector((store: AppStoreState) => store.education);
    const dietState = useSelector((store: AppStoreState) => store.diet);
    const sexualOrientationState = useSelector((store: AppStoreState) => store.sexualOrientation);
    const socialBackgroundState = useSelector((store: AppStoreState) => store.socialBackground);
    const socialBackgroundDiscriminationState = useSelector((store: AppStoreState) => store.socialBackgroundDiscrimination);
    const [emailSuccess, setEmailSuccess] = useState(false);
    const dispatch = useDispatch();
    useEffect(() => {
        // TODO: Handle network and http errors properly tgohlisch 17.11.2022
        dispatch(projectFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(genderFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
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
                                        state={hobbyState}
                                        fetch={hobbyFetch}
                                        title="Hobbies anpassen"
                                        header="Frage: Was sind deine Hobbies?"
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={projectState}
                                        fetch={projectFetch}
                                        title="Projektliste anpassen"
                                        header="Frage: In welchem Projekt arbeitest du derzeit?"
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={genderState}
                                        fetch={genderFetch}
                                        title="Geschlechtliche Identität anpassen"
                                        header="Frage: Was ist deine geschlechtliche Identität?"
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={countryState}
                                        fetch={countryFetch}
                                        title="Ethnische Herkunft anpassen"
                                        header="Frage: Was ist deine ethnische Herkunft?"
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={languageState}
                                        fetch={languageFetch}
                                        title="Muttersprache anpassen"
                                        header="Frage: Was ist deine Muttersprache?"
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={religionState}
                                        fetch={religionFetch}
                                        title="Religion anpassen"
                                        header="Frage: An welche Religion glaubst du?"
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={workExperienceState}
                                        fetch={workExperienceFetch}
                                        title="Berufserfahrung anpassen"
                                        header="Frage: Wie viele Jahre Berufserfahrung hast du schon gesammelt?"
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={educationState}
                                        fetch={educationFetch}
                                        title="Bildungsweg anpassen"
                                        header="Frage: Welchen Bildungsweg hast du bisher bestritten?"
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={dietState}
                                        fetch={dietFetch}
                                        title="Ernährung anpassen"
                                        header="Frage: Wie ernährst du dich?"
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={sexualOrientationState}
                                        fetch={sexualOrientationFetch}
                                        title="Sexuelle Orientierung anpassen"
                                        header="Frage: Was ist deine sexuelle Orientierung?"
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={socialBackgroundState}
                                        fetch={socialBackgroundFetch}
                                        title="Soziale Herkunft anpassen"
                                        header="Frage: Bist du ein Akademikerkind, oder eher die erste Person in der Familie,
                                                die studiert oder ihr Abitur gemacht hat?"
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={socialBackgroundDiscriminationState}
                                        fetch={socialBackgroundDiscriminationFetch}
                                        title="Ausgrenzung anpassen"
                                        header="Frage: Wurdest du jemals aufgrund deiner sozialen Herkunft Vorurteilen ausgesetzt,
                                                herabwürdigend behandelt, benachteiligt oder ausgeschlossen?"
                                        addButtonLabel="Hinzufügen"
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
