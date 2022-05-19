import React, { useCallback, useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import '../../styles/component-styles/addMeeting/dateOverview.scss';
import iconFood from '../../resources/icons/icon-hamburger.svg';
import { CreateMeeting } from '../../types/Meeting';
import { Button } from '../General/Button/Button';
import { dateToString, getMeetingEndTime, substringLocalTime } from '../../utils/date.utils';

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
      <div className="DateOverview-logo-container">
        <CloseSiteContainer />
        <DiversityIconContainer />
      </div>
      <div className="DateOverview-info-text-container">
        <p className="DateOverview-info-text">
          Vielen Dank! Die Informationen wurden erfolgreich an uns übermittelt und wir arbeiten
          daran, dir
          einen Partner für dein Mittagessen zuzuordnen.
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
