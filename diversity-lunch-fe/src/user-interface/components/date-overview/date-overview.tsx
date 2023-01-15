import React, { useCallback, useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { CloseSite } from '../close-site/close-site';
import { DiversityIcon } from '../diversity-icon/diversity-icon';
import './date-overview.scss';
import iconFood from '../../../resources/icons/icon-hamburger.svg';
import { CreateMeeting } from '../../../model/Meeting';
import { Button } from '../button/button';
import { dateToString, getMeetingEndTime, substringLocalTime } from '../../../utils/date.utils';

type DateOverviewProps = {
  meeting: CreateMeeting;
}

export const DateOverview = (props: DateOverviewProps) => {
    const history = useHistory();
    const { meeting } = props;
    const meetingDate = meeting.fromDateTime;

    const [meetingLabel, setMeetingLabel] = useState<string>('');

    const createDateLabel = useCallback(() => dateToString(meetingDate), [meetingDate]);

    useEffect(() => {
        setMeetingLabel(createDateLabel);
    }, [createDateLabel]);

    return (
        <div className="DateOverview">
            <div className="DateOverview-info-text-container">
                <p className="DateOverview-info-text">
                    Vielen Dank! Die Informationen wurden erfolgreich an uns übermittelt und wir arbeiten daran,
                    dir eine*n Partner*in für dein Mittagessen zuzuordnen.
                </p>
            </div>

            <div className="DateOverview-meeting-container">
                <div className="DateOverview-meeting">
                    <div>
                        <h5 className="DateOverview-meeting-text">Dein Termin</h5>
                        <p className="DateOverview-meeting-text">{meetingLabel}</p>
                        <p className="DateOverview-meeting-text">
                            {
                                substringLocalTime(meeting.fromDateTime)
                            }
                            &nbsp;
                            bis
                            {' '}
                            {getMeetingEndTime(meeting.fromDateTime)}
                        </p>
                    </div>
                    <img alt="icon_food" className="DateOverview-meeting-icon" src={iconFood} />
                </div>
            </div>

            <div className="DateOverview-button-container">
                <Button
                    label="Weiteren Termin buchen"
                    onClick={() => history.push('/add+meetings/choose+date')}
                />
            </div>
        </div>
    );
};

export default DateOverview;
