import React from 'react';
import { useSelector } from 'react-redux';
import { TileIconLink } from '../../components/tile-icon-link/tile-icon-link';
import { DiversityIconContainer } from '../../General/HeaderTemplate/DiversityIconContainer';
import '../../../styles/component-styles/dashboard/dashboard.scss';
import iconInfo from '../../../resources/icons/icon-info.svg';
import iconProfile from '../../../resources/icons/icon-profil.svg';
import iconMeeting from '../../../resources/icons/icon-anstehende-termine.svg';
import iconCalendar from '../../../resources/icons/icon-termin-auswählen.svg';
import { AdminPanelIconContainer } from '../../General/HeaderTemplate/AdminPanelIconContainer';
import { Account } from '../../../model/Account';
import { AppStoreState } from '../../../data/app-store';
import { Role } from '../../../model/Role';
import { LoadingAnimation } from '../../Shared/LoadingAnimation';
import { UserVoucherIconContainer } from '../../General/HeaderTemplate/UserVoucherIconContainer';

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
                {isAdmin && <AdminPanelIconContainer />}
                <UserVoucherIconContainer />
            </div>

            <DiversityIconContainer title="DIVERSITY LUNCH" poweredBy />

            <div className="Dashboard-tiles-container">
                <TileIconLink title="DEIN PROFIL" icon={iconProfile} link="profile" />
                <TileIconLink title="TERMIN WÄHLEN" icon={iconCalendar} link="/add+meetings/choose+date" />
                <TileIconLink title="ANSTEHENDE MEETINGS" icon={iconMeeting} link="upcoming+meetings" />
                <TileIconLink title="INFORMATIONEN" icon={iconInfo} link="information" />
            </div>

        </div>
    );
};
