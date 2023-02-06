import React from 'react';
import { useHistory } from 'react-router-dom';
import '../information/information.scss';
import { Button } from '../../components/button/button';

export const MeetingAlreadyBooked = () => {
    const history = useHistory();

    const terminBuchen = () => {
        history.push('/add+meetings/choose+date');
    };

    return (
        <div className="Information">
            <h3>INFORMATION</h3>
            <p className="Information-info-text">
                Diesen Termin hast du bereits gebucht.
            </p>
            <Button label="Zurück zur Terminauswahl" onClick={terminBuchen} />
        </div>
    );
};
