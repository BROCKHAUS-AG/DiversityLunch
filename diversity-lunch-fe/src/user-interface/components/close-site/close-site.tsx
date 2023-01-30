import React from 'react';

import './close-site.scss';
import { Link, useLocation } from 'react-router-dom';
import iconClose from '../../../resources/icons/icon-close.svg';

export const CloseSite = () => {
    // hide CloseButton on Landingpage because obsolet.
    const location = useLocation();

    return (
        <div className="IconHeader">
            {(location.pathname !== '/dashboard' && location.pathname !== '/questions')
                && (
                    <div className="IconHeader-leave-icon">
                        <Link to="/dashboard">
                            <img alt="logout icon" src={iconClose} />
                        </Link>
                    </div>

                )}
        </div>
    );
};
