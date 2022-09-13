import React from 'react';

import '../../../styles/component-styles/general/headerTemplate/adminPanelIconContainer.scss';
import { Link } from 'react-router-dom';
import iconAdmin from '../../../resources/icons/icon-admin.svg';

export const AdminPanelIconContainer = () => (
    <div className="IconHeader">
        <Link to="/admin-panel">
            <img alt="admin icon" className="IconHeader-admin-icon" src={iconAdmin} />
        </Link>
    </div>
);
