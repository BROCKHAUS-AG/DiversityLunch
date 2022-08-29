import React from 'react';

import '../../../styles/component-styles/general/headerTemplate/closeSiteContainer.scss';
import { Link } from 'react-router-dom';
import iconClose from '../../../resources/icons/icon-close.svg';

export const CloseSiteContainer = () => (
  <div className="IconHeader">
    <Link to="/dashboard">
      <img alt="logout icon" className="IconHeader-leave-icon" src={iconClose} />
    </Link>
  </div>
);
