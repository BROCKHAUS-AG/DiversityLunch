import React from 'react';

import './close-site.scss';
import { Link } from 'react-router-dom';
import iconClose from '../../../resources/icons/icon-close.svg';

export const CloseSite = () => (
    <div className="IconHeader">
        <Link to="/dashboard">
            <img alt="logout icon" className="IconHeader-leave-icon" src={iconClose} />
        </Link>
    </div>
);
