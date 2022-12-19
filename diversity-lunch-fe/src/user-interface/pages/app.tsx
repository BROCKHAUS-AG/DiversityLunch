import React from 'react';
import {
    BrowserRouter, Redirect, Route, Switch,
} from 'react-router-dom';
import { Dashboard } from './dashboard/dashboard';
import { UpcomingMeetings } from './upcoming-meetings/upcoming-meetings';
import { QuestionSite } from './questions/question-site';
import './App.scss';
import { AddMeetings } from './add-meetings/add-meetings';
import { Information } from './information/information';
import { GenericErrorPage } from './generic-error-page/generic-error-page';
import { HasProfileCheck } from '../components/has-profile-check/has-profile-check';
import { ProfileOverviewLoader } from './profile-overview/profile-overview-loader';
import { MeetingAlreadyBooked } from './meeting-already-booked/meeting-already-booked';
import { BookingError } from './booking-error/booking-error';
import { AdminPanel } from './admin-panel/admin-panel';
import { VoucherPanel } from './voucher-panel/voucher-panel';
import { UserVoucherList } from './user-voucher-list/user-voucher-list';
import { AppHeader } from '../components/header/header';

export const App = () => (
    <div className="App">
        <div className="Screen">
            <AppHeader />
            <BrowserRouter>
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

                    <Route path="/myVouchers">
                        <UserVoucherList />
                    </Route>

                    <Route
                        path="/"
                        render={() => <GenericErrorPage errorMessage="ERROR 404 - THIS SITE DOES NOT EXIST" />}
                    />
                </Switch>
            </BrowserRouter>
        </div>
    </div>
);

export default App;
