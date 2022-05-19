import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { Meeting } from '../../types/Meeting';
import '../../styles/component-styles/upcomming-meetings/showMeeting.scss';
import closeIcon from '../../resources/icons/icon-close.svg';
import hamburgerIcon from '../../resources/icons/icon-hamburger.svg';
import { deleteMeetingProposal } from '../../Redux/meetings/meetings.actions';
import { DeleteMeetingPopUp } from './DeleteMeetingPopUp';
import { getMeetingEndTime, substringLocalTime } from '../../utils/date.utils';
import { useGetAccountInformation } from '../../hooks/account/account.hook';

interface MeetingContainerProps {
    meeting: Meeting;
}

export const ShowMeeting = ({ meeting }: MeetingContainerProps) => {
  const dispatch = useDispatch();
  const [isDeleting, setIsDeleting] = useState(false);
  const { profileId } = useGetAccountInformation();

  const deleteMeetingCallback = () => {
    dispatch(deleteMeetingProposal(meeting, profileId));
    setIsDeleting(false);
  };

  return (
    <div className="ShowMeeting">
      <div className="ShowMeeting-text-container">
        <p className="ShowMeeting-text">{meeting.fromDateTime.toLocaleDateString('de-DE')}</p>

        <p className="ShowMeeting-text">
          {substringLocalTime(meeting.fromDateTime)}
          {' '}
          -
          {` ${getMeetingEndTime(meeting.fromDateTime)}`}
        </p>
        {
                    meeting.partnerName
                      ? <p className="ShowMeeting-text">{meeting.partnerName}</p> : null
                }
      </div>

      <div className="ShowMeeting-icon-container">
        <img
          alt="icon_hamburger"
          src={hamburgerIcon}
          className="ShowMeeting-hamburgerIcon"
        />
        {
                    !meeting.partnerName
                    && (
                    <div
                      className="ShowMeeting-closeIcon"
                      role="none"
                      onClick={() => {
                        setIsDeleting(true);
                      }}
                    >
                      <img
                        alt="icon_close"
                        src={closeIcon}
                      />
                    </div>
                    )
                }
      </div>

      {
                isDeleting
                && (
                <DeleteMeetingPopUp
                  meeting={meeting}
                  onDelete={deleteMeetingCallback}
                  onCancel={() => setIsDeleting(false)}
                />
                )
            }
    </div>
  );
};
