import React, { useEffect, useState } from 'react';
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
import { PopUp } from '../../components/pop-up/pop-up';
import { Profile } from '../../../model/Profile';
import { ProfileStateOk } from '../../../data/profile/profile-state.type';
import { authenticatedFetchPut } from '../../../utils/fetch.utils';

export const Dashboard = () => {
    const accountState = useSelector((state: AppStoreState) => state.account);
    const profileState = useSelector((state: AppStoreState) => state.profile);
    const [isChange, setIsChange] = useState(false);
    let account : Account;
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

    const isAdmin : boolean = account.role === Role.ADMIN || account.role === Role.AZURE_ADMIN;

    const wasChangeByAdminToFalse = async () => {
        const { profileId } = account;
        await authenticatedFetchPut(`/api/profiles/${profileId}/profilechangeAccepted`, '');
    };

    return (
        <div className="Dashboard">
            <div className="icon-container">
                {isAdmin && <AdminPanelIcon />}
                <UserVoucherIcon />
            </div>

            <DiversityIcon title="DIVERSITY LUNCH" poweredBy />

            <div className="Dashboard-tiles-container">
                <TileIconLink title="DEIN PROFIL" icon={iconProfile} link="profile" />
                <TileIconLink title="TERMIN WÄHLEN" icon={iconCalendar} link="/add+meetings/choose+date" />
                <TileIconLink title="ANSTEHENDE MEETINGS" icon={iconMeeting} link="upcoming+meetings" />
                <TileIconLink title="INFORMATIONEN" icon={iconInfo} link="information" />
            </div>
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
