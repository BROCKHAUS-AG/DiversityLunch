import React from 'react';
import { useSelector } from 'react-redux';
import './dashboard.scss';
import { AdminPanelIcon } from '../../components/admin-panel-icon/admin-panel-icon';
import { Account } from '../../../model/Account';
import { AppStoreState } from '../../../data/app-store';
import { Role } from '../../../model/Role';
import { LoadingAnimation } from '../../components/loading-animation/loading-animation';
import { UserVoucherIcon } from '../../components/user-voucher-icon/user-voucher-icon';
import { Information } from '../information/information';
import profileIcon from '../../../resources/icons/icon-profil.svg';
import upComMeeting from '../../../resources/icons/icon-anstehende-termine.svg';
import chooseDateIcon from '../../../resources/icons/icon-termin-auswählen.svg';

export const Dashboard = () => {
    const accountState = useSelector((state: AppStoreState) => state.account);
    let account: Account;

    if (accountState.status === 'OK') {
        account = accountState.accountData;
    } else {
        return <LoadingAnimation />;
    }

    const isAdmin: boolean = account.role === Role.ADMIN || account.role === Role.AZURE_ADMIN;

    return (
        <div className="Dashboard">
            <div className="icon-container">
                {isAdmin && <AdminPanelIcon />}
                <UserVoucherIcon />
            </div>

            <div className="Dashboard-container">
                <h1>Willkommen!</h1>
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
                                <button>Jetzt füttern</button>
                            </div>

                        </div>
                        <button className="btnMobileBag">Jetzt füttern</button>
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
                                <button>Termine finden</button>
                            </div>
                        </div>
                        <button className="btnMobileBag">Termine finden</button>
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
                                <button>Matches anzeigen</button>
                            </div>
                        </div>
                        <button className="btnMobileBag">Matches anzeigen</button>
                    </div>

                </div>
            </div>
            <Information />
        </div>
    );
};
