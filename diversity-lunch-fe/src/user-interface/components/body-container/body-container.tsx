import {
    Redirect, Route, Switch, useLocation,
} from 'react-router-dom';
import React from 'react';
import { AdminPanel } from '../../pages/admin-panel/admin-panel';
import { HasProfileCheck } from '../has-profile-check/has-profile-check';
import { Dashboard } from '../../pages/dashboard/dashboard';
import { ProfileOverviewLoader } from '../../pages/profile-overview/profile-overview-loader';
import { AddMeetings } from '../../pages/add-meetings/add-meetings';
import { UpcomingMeetings } from '../../pages/upcoming-meetings/upcoming-meetings';
import { QuestionSite } from '../../pages/questions/question-site';
import { Information } from '../../pages/information/information';
import { MeetingAlreadyBooked } from '../../pages/meeting-already-booked/meeting-already-booked';
import { BookingError } from '../../pages/booking-error/booking-error';
import { VoucherPanel } from '../../pages/voucher-panel/voucher-panel';
import { UserVoucherList } from '../../pages/user-voucher-list/user-voucher-list';
import './body-container.scss';

interface BodyContainerParams {
    render: () => JSX.Element;
}

export const BodyContainer = (props: BodyContainerParams) => {
    const {
        render,
    } = props;
    const location = useLocation();
    return (
        <div className={location.pathname === '/questions' ? 'bodyContainer questions' : 'bodyContainer'}>
            <Switch>
                <Route exact path="/">
                    <Redirect to="/profile-check" />
                </Route>

                <Route path="/admin-panel">
                    <AdminPanel />
                </Route>

                <Route path="/profile-check">
                    <HasProfileCheck />
                </Route>

                <Route path="/dashboard">
                    <Dashboard />
                </Route>

                <Route exact path="/profile">
                    <ProfileOverviewLoader />
                </Route>

                <Route path="/add+meetings">
                    <AddMeetings />
                </Route>

                <Route path="/upcoming+meetings">
                    <UpcomingMeetings />
                </Route>

                <Route path="/questions">
                    <QuestionSite />
                </Route>

                <Route path="/information">
                    <Information />
                </Route>

                <Route path="/meetingAlreadyBooked">
                    <MeetingAlreadyBooked />
                </Route>

                <Route path="/bookingError">
                    <BookingError />
                </Route>

                <Route path="/voucherClaim/:meetingId">
                    <VoucherPanel />
                </Route>

                <Route path="/myVouchers">
                    <UserVoucherList />
                </Route>

                <Route
                    path="/"
                    render={render}
                />
            </Switch>
        </div>
    );
};
