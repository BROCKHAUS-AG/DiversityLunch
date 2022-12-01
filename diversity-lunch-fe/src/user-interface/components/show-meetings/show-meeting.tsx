import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { Meeting } from '../../../model/Meeting';
import './show-meeting.scss';
import closeIcon from '../../../resources/icons/icon-close.svg';
import hamburgerIcon from '../../../resources/icons/icon-hamburger.svg';
import { deleteMeetingProposal } from '../../../data/meeting/meetings.actions';
import { DeleteMeetingPopUp } from '../delete-meeting-pop-up/delete-meeting-pop-up';
import { getMeetingEndTime, substringLocalTime } from '../../../utils/date.utils';
import { useGetAccountInformation } from '../../../hooks/account/account.hook';

interface MeetingContainerProps {
    meeting: Meeting;
}

export const ShowMeeting = ({ meeting }: MeetingContainerProps) => {
    const dispatch = useDispatch();
    const [isDeletingOpen, setIsDeletingOpen] = useState(false);
    const [isDeletingUpcoming, setIsDeletingUpcoming] = useState(false);
    const { profileId } = useGetAccountInformation();

    const deleteOpenMeetingCallback = () => {
        dispatch(deleteMeetingProposal(meeting, profileId));
        setIsDeletingOpen(false);
    };

    const deleteUpcomingMeetingCallback = () => {
        // Delete Upcoming Meeting here / Backend Interface
        setIsDeletingUpcoming(false);
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
                    meeting.partnerName
                        ? (
                            <div
                                className="ShowMeeting-closeIcon"
                                role="none"
                                onClick={() => {
                                    setIsDeletingUpcoming(true);
                                }}
                            >
                                <img
                                    alt="icon_close"
                                    src={closeIcon}
                                />
                            </div>
                        )
                        : (
                            <div
                                className="ShowMeeting-closeIcon"
                                role="none"
                                onClick={() => {
                                    setIsDeletingOpen(true);
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
                isDeletingOpen
                && (
                    <DeleteMeetingPopUp
                        meeting={meeting}
                        onDelete={deleteOpenMeetingCallback}
                        onCancel={() => setIsDeletingOpen(false)}
                    />
                )
            }
            {
                isDeletingUpcoming
                && (
                    <DeleteMeetingPopUp
                        meeting={meeting}
                        onDelete={deleteUpcomingMeetingCallback}
                        onCancel={() => setIsDeletingUpcoming(false)}
                    />
                )
            }
        </div>
    );
};
