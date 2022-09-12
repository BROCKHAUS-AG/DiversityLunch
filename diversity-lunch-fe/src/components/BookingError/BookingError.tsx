import React from 'react';
import { useHistory } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import '../../styles/component-styles/information/information.scss';
import { Button } from '../General/Button/Button';
import { MeetingsStateAction } from '../../data/meeting/meetings-state-action.type';

export const BookingError = () => {
    const history = useHistory();

    const dispatch = useDispatch();

    const terminBuchen = () => {
        dispatch({ type: 'MEETINGS_PENDING' } as MeetingsStateAction);
        history.push('/add+meetings/choose+date');
    };

    return (
      <div className="Information">
          <CloseSiteContainer />
          <DiversityIconContainer title="INFORMATION" />
          <p className="Information-info-text">
              Die Informationen wurden leider nicht erfolgreich an uns übermittelt.
              Bitte versuche es erneut.
            </p>
          <Button label="Termin buchen" onClick={terminBuchen} />
        </div>
    );
};
