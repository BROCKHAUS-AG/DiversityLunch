import React from 'react';
import { useHistory } from 'react-router-dom';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import '../../styles/component-styles/information/information.scss';
import { Button } from '../General/Button/Button';

export const MeetingAlreadyBooked = () => {
    const history = useHistory();

    const terminBuchen = () => {
        history.push('/add+meetings/choose+date');
    };

    return (
      <div className="Information">
          <CloseSiteContainer />
          <DiversityIconContainer title="INFORMATION" />
          <p className="Information-info-text">
              Diesen Termin hast du bereits gebucht.
            </p>
          <Button label="Termin buchen" onClick={terminBuchen} />
        </div>
    );
};
