import React from 'react';
import { useSelector } from 'react-redux';
import './dashboard.scss';
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
                <h1>Frohe Feiertage!</h1>
            </div>

        </div>
    );
};
