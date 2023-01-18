import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { TileIcon } from '../tile-icon/tile-icon';

// ROUTING
// DATA FILE
import { NavbarData } from './NavbarData';

// STYLES
import './navbar.scss';
import { UserVoucherIcon } from '../user-voucher-icon/user-voucher-icon';
import { CloseSite } from '../close-site/close-site';

export const Navbar = () => {
    const [sidebar, setSidebar] = useState(false);

    const showSidebar = () => setSidebar(!sidebar);

    return (
        <div>
            <div className="navbar-mobile">
                <div className="navbar">
                    <Link to="/#" className="menu-bars">
                        <button onClick={showSidebar} className="row">
                            <div className="hamburg">
                                <span className="line" />
                                <span className="line" />
                                <span className="line" />
                            </div>
                        </button>
                    </Link>
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
                                    <span>{item.title}</span>
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
                            <li className="navbar-toggle">
                                <UserVoucherIcon />
                            </li>
                        </div>
                        {NavbarData.map((item) => (
                            <li key={item.id} className={item.cName}>
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