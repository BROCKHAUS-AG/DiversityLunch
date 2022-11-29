import React from 'react';

// Component
import { Meeting } from '../../model/Meeting';

// Styles
import '../../styles/component-styles/popup/GeneralPopUp.scss';
import { Button } from '../components/button/button';
import { dateToString, getMeetingEndTime, substringLocalTime } from '../../utils/date.utils';

interface MeetingContainerProps {
  meeting: Meeting;
  onDelete: () => void;
  onCancel: () => void;
}

export const DeleteMeetingPopUp = (props: MeetingContainerProps) => {
    const {
        meeting,
        onDelete,
        onCancel,
    } = props;

    return (
        <div className="GeneralPopUp">
            <div className="GeneralPopUp-container">
                <h6 className="GeneralPopUp-text">
                    Willst du den Termin wirklich löschen?
                </h6>
                <div className="DeleteMeetingPopUp-meeting-container">
                    {dateToString(meeting.fromDateTime)}
                    <div>
                        {substringLocalTime(meeting.fromDateTime)}
                        {' '}
                        -
                        {getMeetingEndTime(meeting.fromDateTime)}
                    </div>
                </div>
                <div className="DeleteMeetingPopUp-button-container">
                    <Button label="Zeitslot löschen" onClick={onDelete} />
                    <button
                        type="button"
                        className="DeleteMeetingPopUp-delete-button"
                        onClick={() => onCancel()}
                    >
                        Abbrechen
                    </button>
                </div>
            </div>
        </div>
    );
};
