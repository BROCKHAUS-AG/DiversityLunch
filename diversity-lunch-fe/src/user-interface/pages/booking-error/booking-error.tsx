import React from 'react';
import { useHistory } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { DiversityIcon } from '../../components/diversity-icon/diversity-icon';
import { CloseSite } from '../../components/close-site/close-site';
import '../information/information.scss';
import { Button } from '../../components/button/button';
import { MeetingsStateAction } from '../../../data/meeting/meetings-state-action.type';

export const BookingError = () => {
    const history = useHistory();

    const dispatch = useDispatch();

    const terminBuchen = () => {
        dispatch({ type: 'MEETINGS_PENDING' } as MeetingsStateAction);
        history.push('/add+meetings/choose+date');
    };

    return (
        <div className="Information">
            <CloseSite />
            <DiversityIcon title="INFORMATION" />
            <p className="Information-info-text">
                Die Informationen wurden leider nicht erfolgreich an uns übermittelt.
                Bitte versuche es erneut.
            </p>
            <Button label="Termin buchen" onClick={terminBuchen} />
        </div>
    );
};
