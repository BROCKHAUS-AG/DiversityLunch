import React, { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { useSelector } from 'react-redux';

// ROUTING
// DATA FILE
import { NavbarData } from './NavbarData';

// STYLES
import './navbar.scss';
import { UserVoucherIcon } from '../user-voucher-icon/user-voucher-icon';
import { CloseSite } from '../close-site/close-site';
import { AdminPanelIcon } from '../admin-panel-icon/admin-panel-icon';
import { TileIcon } from '../tile-icon/tile-icon';
import { Role } from '../../../model/Role';

import { AppStoreState } from '../../../data/app-store';
import { Account } from '../../../model/Account';
import { LoadingAnimation } from '../loading-animation/loading-animation';
import iconMenu from '../../../resources/icons/icon-menu.svg';

export const Navbar = () => {
    const [sidebar, setSidebar] = useState(false);
    const accountState = useSelector((state: AppStoreState) => state.account);
    let account: Account;

    const location = useLocation();

    const showSidebar = () => setSidebar(!sidebar);

    if (accountState?.status === 'OK') {
        account = accountState.accountData;
    } else {
        return <LoadingAnimation />;
    }

    const isAdmin: boolean = account.role === Role.ADMIN || account.role === Role.AZURE_ADMIN;

    return (
        <div>

            {location.pathname !== '/questions'
                && (
                    <div>
                        <div className="navbar-mobile">
                            <div className="navbar">
                                <div className="hamburg">
                                    <Link to="/#" className="menu-bars" onClick={showSidebar}>
                                        <img alt="icon menu" className="icon-menu" src={iconMenu} />
                                    </Link>
                                </div>
                                <CloseSite />
                            </div>
                            <nav className={sidebar ? 'nav-menu active' : 'nav-menu'}>
                                <ul className="nav-menu-items">
                                    <li className="navbar-toggle">
                                        <Link to="/#" className="menu-bars" onClick={showSidebar}>
                                            X
                                        </Link>
                                    </li>

                                    {NavbarData.map((item) => (
                                        <li key={item.id} className={item.cName}>
                                            <Link to={item.path} onClick={showSidebar}>
                                                <TileIcon title="" icon={item.icon} />
                                                <span className="nav-title">{item.title}</span>
                                            </Link>
                                        </li>
                                    ))}
                                </ul>
                            </nav>
                        </div>

                        <div className="navbar-l">
                            <nav className="nav-menu active">
                                <ul className="nav-menu-items">
                                    <div className="header">
                                        <div className="flex-container">
                                            <li className="navbar-toggle">
                                                {isAdmin && (
                                                    <AdminPanelIcon />
                                                )}
                                            </li>
                                            <li className="navbar-toggle">
                                                <UserVoucherIcon />
                                            </li>
                                        </div>
                                    </div>
                                    {NavbarData.map((item) => (
                                        <li key={item.id} className={item.cName}>
                                            <Link to={item.path}>
                                                <TileIcon title="" icon={item.icon} />
                                                <span className="nav-title">{item.title}</span>
                                            </Link>
                                        </li>
                                    ))}
                                </ul>
                            </nav>
                        </div>
                    </div>
                )}

        </div>
    );
};
