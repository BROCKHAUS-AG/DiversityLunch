import React from 'react';
import {
    BrowserRouter, Redirect, Route, Switch,
} from 'react-router-dom';
import { Dashboard } from '../Dashboard/Dashboard';
import { UpcomingMeetings } from '../UpcommingMeetings/UpcomingMeetings';
import { QuestionSite } from '../Questions/QuestionSite';
import '../../styles/core/App.scss';
import { AddMeetings } from '../AddMeetings/AddMeetings';
import { Information } from '../Information/Information';
import { GenericErrorPage } from '../Shared/GenericErrorPage';
import { HasProfileCheck } from './HasProfileCheck';
import { ProfileOverviewLoader } from '../Profile/ProfileOverviewLoader';
import { MeetingAlreadyBooked } from '../MeetingAlreadyBooked/MeetingAlreadyBooked';
import { BookingError } from '../BookingError/BookingError';
import { AdminPanel } from '../AdminPanel/AdminPanel';
import { VoucherPanel } from '../VoucherPanel/VoucherPanel';
import { UserVoucherList } from '../UserVoucherList/UserVoucherList';

export const App = () => (
    <div className="App">
        <div className="Screen">
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

                    <Route path="/voucherClaim">
                        <VoucherPanel />
                    </Route>

                    <Route path="/myVouchers">
                        <UserVoucherList />
                    </Route>

                    <Route path="/" render={() => <GenericErrorPage errorMessage="ERROR 404 - THIS SITE DOES NOT EXIST" />} />
                </Switch>
            </BrowserRouter>
        </div>
    </div>
);

export default App;
