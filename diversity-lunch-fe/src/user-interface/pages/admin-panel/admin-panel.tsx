import React, { FC, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
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
import { profileFormQuestion } from '../../../globals/profile-form-question';
import { LoadingAnimation } from '../../components/loading-animation/loading-animation';

export const AdminPanel: FC = () => {
    const profileState = useSelector((state: AppStoreState) => state.profile);
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
        dispatch(countryFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(dietFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(educationFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(genderFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(hobbyFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(languageFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(projectFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(religionFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(workExperienceFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(sexualOrientationFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(socialBackgroundFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
        dispatch(socialBackgroundDiscriminationFetch.getAll({ onNetworkError: console.error, statusCodeHandlers: {} }));
    }, []);

    if (profileState.status !== 'OK') {
        return <LoadingAnimation />;
    }

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
                                        header={`Frage: ${profileFormQuestion.hobby}`}
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={projectState}
                                        fetch={projectFetch}
                                        title="Projektliste anpassen"
                                        header={`Frage: ${profileFormQuestion.project}`}
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={genderState}
                                        fetch={genderFetch}
                                        title="Geschlechtliche Identität anpassen"
                                        header={`Frage: ${profileFormQuestion.gender}`}
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={countryState}
                                        fetch={countryFetch}
                                        title="Ethnische Herkunft anpassen"
                                        header={`Frage: ${profileFormQuestion.country}`}
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={languageState}
                                        fetch={languageFetch}
                                        title="Muttersprache anpassen"
                                        header={`Frage: ${profileFormQuestion.language}`}
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={religionState}
                                        fetch={religionFetch}
                                        title="Religion anpassen"
                                        header={`Frage: ${profileFormQuestion.religion}`}
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={workExperienceState}
                                        fetch={workExperienceFetch}
                                        title="Berufserfahrung anpassen"
                                        header={`Frage: ${profileFormQuestion.workExperience}`}
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={educationState}
                                        fetch={educationFetch}
                                        title="Bildungsweg anpassen"
                                        header={`Frage: ${profileFormQuestion.education}`}
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={dietState}
                                        fetch={dietFetch}
                                        title="Ernährung anpassen"
                                        header={`Frage: ${profileFormQuestion.diet}`}
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={sexualOrientationState}
                                        fetch={sexualOrientationFetch}
                                        title="Sexuelle Orientierung anpassen"
                                        header={`Frage: ${profileFormQuestion.sexualOrientation}`}
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={socialBackgroundState}
                                        fetch={socialBackgroundFetch}
                                        title="Soziale Herkunft anpassen"
                                        header={`Frage: ${profileFormQuestion.socialBackground}`}
                                        addButtonLabel="Hinzufügen"
                                    />
                                    <IdentifiableOptionsList
                                        state={socialBackgroundDiscriminationState}
                                        fetch={socialBackgroundDiscriminationFetch}
                                        title="Ausgrenzung anpassen"
                                        header={`Frage: ${profileFormQuestion.socialBackgroundDiscrimination}`}
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
