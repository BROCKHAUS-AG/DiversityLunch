import React, { useCallback, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useHistory } from 'react-router-dom';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { ShowMeeting } from './ShowMeeting';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import '../../styles/component-styles/upcomming-meetings/upcommingMeetings.scss';
import { Meeting } from '../../types/Meeting';
import { AppStoreState } from '../../store/Store';
import { loadMeetings } from '../../data/meeting/meetings.actions';
import { LoadingAnimation } from '../Shared/LoadingAnimation';
import { MeetingsState } from '../../data/meeting/meetings-state.type';
import { useGetAccountInformation } from '../../hooks/account/account.hook';
import { Button } from '../General/Button/Button';

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
            <CloseSiteContainer />
            <DiversityIconContainer title="Anstehende Meetings" />
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
