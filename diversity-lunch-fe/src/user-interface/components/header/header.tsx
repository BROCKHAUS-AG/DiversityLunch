import React from 'react';
import { DiversityIcon } from '../diversity-icon/diversity-icon';
import './header.scss';

export const AppHeader = () => (
    <div className="AppHeader">
        <div className="AppHeader-Wrapper">
            <div className="flexBox">
                <DiversityIcon title="DIVERSITY LUNCH" isPoweredBy />
            </div>
        </div>
    </div>
);
