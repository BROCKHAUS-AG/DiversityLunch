import React, { useCallback, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useHistory } from 'react-router-dom';
import './upcoming-meetings.scss';
import { Meeting } from '../../../model/Meeting';
import { AppStoreState } from '../../../data/app-store';
import { loadMeetings } from '../../../data/meeting/meetings.actions';
import { LoadingAnimation } from '../../components/loading-animation/loading-animation';
import { MeetingsState } from '../../../data/meeting/meetings-state.type';
import { useGetAccountInformation } from '../../../hooks/account/account.hook';
import { Button } from '../../components/button/button';
import { ShowMeeting } from '../../components/show-meetings/show-meeting';

export const UpcomingMeetings = () => {
    const dispatch = useDispatch();
    const history = useHistory();

    const meetingsState: MeetingsState = useSelector((state: AppStoreState) => state.meetings);

    function sortMeetingsASCByTime() {
        return (a: Meeting, b: Meeting) => a.fromDateTime.getTime() - b.fromDateTime.getTime();
    }

    const meetings = meetingsState.status === 'OK'
        ? meetingsState.meetings
        : [];
    meetings.sort(sortMeetingsASCByTime());

    const isPending = useCallback(() => meetingsState.status === 'PENDING', [meetingsState]);
    const { profileId } = useGetAccountInformation();

    useEffect(() => {
        if (isPending()) {
            dispatch(loadMeetings(profileId));
        }
        if (meetingsState.status === 'UPDATED') {
            dispatch({ type: 'MEETINGS_LOADING_SUCCEEDED', payload: meetingsState.meetings });
        }
    }, [isPending, dispatch, profileId, meetingsState]);

    return (
        <div className="UpcomingMeetings">
            <div className="UpcomingMeetings-container">
                <h5 className="UpcomingMeetings-header">Deine Meetings</h5>
                <h6 className="UpcomingMeetings-subheader">Anstehende Verabredungen</h6>
                {
                    meetingsState.status === 'OK'
                        ? meetings
                            .filter((meeting: Meeting) => meeting.partnerName)
                            .map(
                                (meeting: Meeting) => <ShowMeeting key={meeting.id} meeting={meeting} />,
                            )
                        : <LoadingAnimation />
                }
                <h6 className="UpcomingMeetings-subheader">Offene Termine</h6>
                {
                    meetingsState.status === 'OK'
                        ? meetings
                            .filter((meeting: any) => !meeting.partnerName)
                            .map(
                                (meeting: Meeting) => <ShowMeeting key={meeting.id} meeting={meeting} />,
                            )
                        : <LoadingAnimation />
                }
            </div>
            <div className="CustomButton-container">
                <Button label="Weiteren Termin buchen" onClick={() => history.push('/add+meetings/choose+date')} />
            </div>
        </div>
    );
};
