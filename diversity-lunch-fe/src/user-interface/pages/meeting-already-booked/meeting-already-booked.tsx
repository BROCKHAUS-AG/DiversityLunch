import React from 'react';
import { useHistory } from 'react-router-dom';
import { DiversityIcon } from '../../components/diversity-icon/diversity-icon';
import { CloseSite } from '../../components/close-site/close-site';
import '../information/information.scss';
import { Button } from '../../components/button/button';

export const MeetingAlreadyBooked = () => {
    const history = useHistory();

    const terminBuchen = () => {
        history.push('/add+meetings/choose+date');
    };

    return (
        <div className="Information">
            <CloseSite />
            <DiversityIcon title="INFORMATION" />
            <p className="Information-info-text">
                Diesen Termin hast du bereits gebucht.
            </p>
            <Button label="Termin buchen" onClick={terminBuchen} />
        </div>
    );
};
