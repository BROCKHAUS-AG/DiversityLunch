import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { TileIcon } from '../tile-icon/tile-icon';
import iconProfile from '../../../resources/icons/icon-profil.svg';
import iconCalendar from '../../../resources/icons/icon-termin-auswählen.svg';
import iconMeeting from '../../../resources/icons/icon-anstehende-termine.svg';
import iconInfo from '../../../resources/icons/icon-info.svg';

// ROUTING

// DATA FILE
import { NavbarData } from './NavbarData';

// STYLES
import './navbar.scss';
import { UserVoucherIcon } from '../user-voucher-icon/user-voucher-icon';

export const Navbar = () => {
    const [sidebar, setSidebar] = useState(false);

    const showSidebar = () => setSidebar(!sidebar);

    return (
        <div>
            <div className="navbar-mobile">
                <div className="navbar">
                    <Link to="#" className="menu-bars">
                        <p onClick={showSidebar}>MOBILE</p>
                    </Link>
                </div>
                <nav className={sidebar ? 'nav-menu active' : 'nav-menu'}>
                    <ul className="nav-menu-items" onClick={showSidebar}>
                        <li className="navbar-toggle">
                            <Link to="#" className="menu-bars">
                                X
                            </Link>
                        </li>

                        {NavbarData.map((item, index) => (
                            <li key={index} className={item.cName}>
                                <Link to={item.path}>
                                    <img alt="TileIconLink Icon" className="Tile-icon" src={item.icon} />
                                    <span>{item.title}</span>
                                </Link>
                            </li>
                        ))}
                    </ul>
                </nav>
            </div>

            <div className="navbar-l">
                <nav className="nav-menu active">
                    <ul className="nav-menu-items" onClick={showSidebar}>
                        <div className="header">
                            <li className="navbar-toggle">
                                <UserVoucherIcon />
                            </li>
                        </div>
                        {NavbarData.map((item, index) => (
                            <li key={index} className={item.cName}>
                                <Link to={item.path}>
                                    <TileIcon title="" icon={item.icon} />
                                    <span>{item.title}</span>
                                </Link>
                            </li>
                        ))}
                    </ul>
                </nav>
            </div>
        </div>
    );
};
