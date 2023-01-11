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
import editIcon from '../../../resources/icons/Edit.svg';
import appointmentIcon from '../../../resources/icons/Appointment.svg';
import matchIcon from '../../../resources/icons/Match.svg';

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
                                    <img alt="TileIconLink Icon" className="Tile-icon" src={editIcon} />
                                </div>
                            </div>
                            <div className="body">
                                <h2>1.</h2>
                                <h4>Profil ausfüllen und unseren Algorythmus füttern.</h4>
                                <button>Jetzt füttern</button>
                            </div>
                        </div>
                    </div>
                    <div className="column">
                        <div className="card">
                            <div className="image">
                                <div className="icon">
                                    <img alt="TileIconLink Icon" className="Tile-icon" src={appointmentIcon} />
                                </div>
                            </div>
                            <div className="body">
                                <h2>2.</h2>
                                <h4>Möglichen Termin angeben</h4>
                                <button>Termine finden</button>
                            </div>
                        </div>
                    </div>
                    <div className="column">
                        <div className="card">
                            <div className="image">
                                <div className="icon">
                                    <img alt="TileIconLink Icon" className="Tile-icon" src={matchIcon} />
                                </div>
                            </div>
                            <div className="body">
                                <h2>3.</h2>
                                <h4>Viel Spaß bei deinem Diversity-Match!</h4>
                                <button>Matches anzeigen</button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <Information />
        </div>
    );
};
