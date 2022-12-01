import React from 'react';
import './diversity-icon.scss';
import iconDiversity from '../../../resources/icons/icon-diversity-logo-mit-rahmen.svg';

type DiversityIconContainerProps = {
  title?: string;
  subtitle?: string;
  poweredBy?: boolean;
}

export const DiversityIcon = (props: DiversityIconContainerProps) => {
    const {
        title,
        subtitle,
        poweredBy,
    } = props;

    return (
        <div className="IconHeader-diversity-logo-container">
            <img alt="diversity icon" className="IconHeader-diversity-icon" src={iconDiversity} />
            <div className="IconHeader-title-container">
                <h4 className="IconHeader-title">{title}</h4>
                {
                    poweredBy
          && (
              <div className="IconHeader-title-powered-by-container">
                  <p className="IconHeader-title-powered-by">powered by</p>
                  <p className="IconHeader-title-brockhaus-ag">
                      {'\u00A0'}
                      BROCKHAUS AG
                  </p>
              </div>
          )
                }

            </div>

            <p className="IconHeader-subtitle">{subtitle}</p>
        </div>
    );
};

DiversityIcon.defaultProps = {
    title: '',
    subtitle: '',
    poweredBy: '',
};
