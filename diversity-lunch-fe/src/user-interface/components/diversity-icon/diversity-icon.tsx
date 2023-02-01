import React from 'react';
import './diversity-icon.scss';
import { useLocation } from 'react-router-dom';
import iconDiversity from '../../../resources/icons/icon-diversity-logo-mit-rahmen.svg';

type DiversityIconContainerProps = {
    title?: string;
    subtitle?: string;
    isPoweredBy?: boolean;
}

export const DiversityIcon = (props: DiversityIconContainerProps) => {
    const {
        title,
        subtitle,
        isPoweredBy,
    } = props;

    const location = useLocation();

    return (
        <div className="diversitylunch-logo">
            {/* eslint-disable-next-line max-len */}
            <div className={location.pathname === '/questions' ? 'IconHeader-diversity-logo-container questions' : 'IconHeader-diversity-logo-container'}>
                <div className="diversitylunch-logo-wrapper">
                    <img alt="diversity icon" className="IconHeader-diversity-icon" src={iconDiversity} />
                    <div className="IconHeader-title-container">
                        <h4 className="IconHeader-title">{title}</h4>
                        {isPoweredBy && (
                            <div className="IconHeader-title-powered-by-container">
                                <p className="IconHeader-title-powered-by">powered by</p>
                                <p className="IconHeader-title-brockhaus-ag">
                                    {'\u00A0'}
                                    BROCKHAUS AG
                                </p>
                            </div>
                        )}
                    </div>
                    <p className="IconHeader-subtitle">{subtitle}</p>
                </div>
            </div>
        </div>
    );
};

DiversityIcon.defaultProps = {
    title: '',
    subtitle: '',
    isPoweredBy: '',
};
