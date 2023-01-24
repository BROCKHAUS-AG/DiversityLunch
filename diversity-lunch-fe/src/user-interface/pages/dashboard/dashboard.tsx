import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';
import './dashboard.scss';
import profileIcon from '../../../resources/icons/icon-profil.svg';
import upComMeeting from '../../../resources/icons/icon-anstehende-termine.svg';
import { Account } from '../../../model/Account';
import { AppStoreState } from '../../../data/app-store';
import { LoadingAnimation } from '../../components/loading-animation/loading-animation';
import { PopUp } from '../../components/pop-up/pop-up';
import { Profile } from '../../../model/Profile';
import { ProfileStateOk } from '../../../data/profile/profile-state.type';
import { authenticatedFetchPut } from '../../../utils/fetch.utils';
import { Information } from '../information/information';
import chooseDateIcon from '../../../resources/icons/icon-termin-auswählen.svg';

export const Dashboard = () => {
    const accountState = useSelector((state: AppStoreState) => state.account);
    let account: Account;
    const profileState = useSelector((state: AppStoreState) => state.profile);
    const [isChange, setIsChange] = useState(false);
    const profile: Profile = (profileState as ProfileStateOk).profileData;

    const createPopUpIfWasChangedByAdmin = () => {
        if (profile?.wasChangedByAdmin) {
            setIsChange(true);
        }
    };

    useEffect(() => {
        createPopUpIfWasChangedByAdmin();
    }, [profile?.wasChangedByAdmin]);

    if (accountState?.status === 'OK') {
        account = accountState.accountData;
    } else {
        return <LoadingAnimation />;
    }

    const wasChangeByAdminToFalse = async () => {
        const { profileId } = account;
        await authenticatedFetchPut(`/api/profiles/${profileId}/profilechangeAccepted`, '');
    };

    return (
        <div className="Dashboard">
            <h1>Willkommen!</h1>
            <div className="Dashboard-container">
                <div className="row">
                    <div className="column">
                        <div className="card">
                            <div className="image">
                                <div className="icon">
                                    <img alt="TileIconLink Icon" className="Tile-icon" src={profileIcon} />
                                </div>
                            </div>
                            <div className="body">
                                <h2>1.</h2>
                                <h4>Profil ausfüllen und unseren Algorithmus füttern.</h4>
                                <div>
                                    <Link className="link-container" to="/profile">
                                        <button>Jetzt füttern</button>
                                    </Link>
                                </div>
                            </div>
                        </div>
                        <div>
                            <Link className="link-container" to="/profile">
                                <button className="btnMobileBag">Jetzt füttern</button>
                            </Link>
                        </div>
                    </div>
                    <div className="column">
                        <div className="card">
                            <div className="image">
                                <div className="icon">
                                    <img alt="TileIconLink Icon" className="Tile-icon" src={chooseDateIcon} />
                                </div>
                            </div>
                            <div className="body">
                                <h2>2.</h2>
                                <h4>Möglichen Termin angeben</h4>
                                <div>
                                    <Link className="link-container" to="/add+meetings/choose+date">
                                        <button>Termine finden</button>
                                    </Link>
                                </div>
                            </div>
                        </div>
                        <div>
                            <Link className="link-container" to="/add+meetings">
                                <button className="btnMobileBag">Termine finden</button>
                            </Link>
                        </div>
                    </div>
                    <div className="column">
                        <div className="card">
                            <div className="image">
                                <div className="icon">
                                    <img alt="TileIconLink Icon" className="Tile-icon" src={upComMeeting} />
                                </div>
                            </div>
                            <div className="body">
                                <h2>3.</h2>
                                <h4>Viel Spaß bei deinem Diversity-Match!</h4>
                                <div>
                                    <Link className="link-container" to="/upcoming+meetings">
                                        <button className="btnMobileBag">Matches anzeigen</button>
                                    </Link>
                                </div>
                            </div>
                        </div>
                        <div>
                            <Link className="link-container" to="/upcoming+meetings">
                                <button className="btnMobileBag">Matches anzeigen</button>
                            </Link>
                        </div>
                    </div>

                </div>
            </div>
            <Information />
            {
                isChange && (
                    <PopUp
                        onButtonClick={() => {
                            setIsChange(false);
                            wasChangeByAdminToFalse();
                        }}
                        message="Deine Profilangaben haben sich geändert, bitte kontrolliere diese auf richtigkeit!"
                        buttonText="Okay"
                    />
                )
            }
        </div>
    );
};
