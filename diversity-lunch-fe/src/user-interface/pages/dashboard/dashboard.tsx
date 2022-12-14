import React from 'react';
import { useSelector } from 'react-redux';
import { TileIconLink } from '../../components/tile-icon-link/tile-icon-link';
import { DiversityIcon } from '../../components/diversity-icon/diversity-icon';
import './dashboard.scss';
import iconInfo from '../../../resources/icons/icon-info.svg';
import iconProfile from '../../../resources/icons/icon-profil.svg';
import iconMeeting from '../../../resources/icons/icon-anstehende-termine.svg';
import iconCalendar from '../../../resources/icons/icon-termin-auswählen.svg';
import { AdminPanelIcon } from '../../components/admin-panel-icon/admin-panel-icon';
import { Account } from '../../../model/Account';
import { AppStoreState } from '../../../data/app-store';
import { Role } from '../../../model/Role';
import { LoadingAnimation } from '../../components/loading-animation/loading-animation';
import { UserVoucherIcon } from '../../components/user-voucher-icon/user-voucher-icon';

export const Dashboard = () => {
    const accountState = useSelector((state: AppStoreState) => state.account);
    let account : Account;

    if (accountState.status === 'OK') {
        account = accountState.accountData;
    } else {
        return <LoadingAnimation />;
    }
    const isAdmin : boolean = account.role === Role.ADMIN || account.role === Role.AZURE_ADMIN;

    return (
        <div className="Dashboard">
            <div className="icon-container">
                {isAdmin && <AdminPanelIcon />}
                <UserVoucherIcon />
            </div>

            <div className="Dashboard-tiles-container">
                <TileIconLink title="DEIN PROFIL" icon={iconProfile} link="profile" />
                <TileIconLink title="TERMIN WÄHLEN" icon={iconCalendar} link="/add+meetings/choose+date" />
                <TileIconLink title="ANSTEHENDE MEETINGS" icon={iconMeeting} link="upcoming+meetings" />
                <TileIconLink title="INFORMATIONEN" icon={iconInfo} link="information" />
            </div>

        </div>
    );
};
