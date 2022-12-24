import React from 'react';

import './home.scss';
import {
    BrowserRouter, Redirect, Route, Switch,
} from 'react-router-dom';

import { AppHeader } from '../../components/header/header';
import { HasProfileCheck } from '../../components/has-profile-check/has-profile-check';
import { ProfileOverviewLoader } from '../profile-overview/profile-overview-loader';
import { AddMeetings } from '../add-meetings/add-meetings';
import { UpcomingMeetings } from '../upcoming-meetings/upcoming-meetings';
import { QuestionSite } from '../questions/question-site';
import { Information } from '../information/information';
import { MeetingAlreadyBooked } from '../meeting-already-booked/meeting-already-booked';
import { BookingError } from '../booking-error/booking-error';
import { VoucherPanel } from '../voucher-panel/voucher-panel';
import { GenericErrorPage } from '../generic-error-page/generic-error-page';
import { Dashboard } from '../dashboard/dashboard';

export const Home = () => (
    <div className="Welcome">
        <AppHeader />
        <Dashboard />
        <BrowserRouter>
            <Switch>
                <Route exact path="/">
                    <Redirect to="/profile-check" />
                </Route>

                <Route path="/profile-check">
                    <HasProfileCheck />
                </Route>

                <Route path="/profile">
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

                <Route
                    path="/"
                    render={() => <GenericErrorPage errorMessage="ERROR 404 - THIS SITE DOES NOT EXIST" />}
                />
            </Switch>
        </BrowserRouter>

    </div>
);
