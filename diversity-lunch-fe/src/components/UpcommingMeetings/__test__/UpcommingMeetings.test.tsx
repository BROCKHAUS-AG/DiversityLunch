import { render } from '@testing-library/react';
import * as redux from 'react-redux';
import { BrowserRouter, Router } from 'react-router-dom';
import { createMemoryHistory } from 'history';
import userEvent from '@testing-library/user-event';
import { UpcomingMeetings } from '../UpcomingMeetings';
import { APP_STORE } from '../../../store/Store';
import { Meeting } from '../../../types/Meeting';

let meetingMatched: Meeting;
let meetingNotMatched: Meeting;

describe('Show upcoming Meeting', () => {
    let providerElement: JSX.Element;

    beforeEach(() => {
        providerElement = <redux.Provider store={APP_STORE}><UpcomingMeetings /></redux.Provider>;

        meetingMatched = {
            fromDateTime: new Date(2022, 2, 3, 12),
            id: '01',
            partnerName: 'person2',
        };

        meetingNotMatched = {
            ...meetingMatched,
            partnerName: '',
        };
    });

    it('renders without crashing', () => {
        const { container } = render(<BrowserRouter>{providerElement}</BrowserRouter>);
        expect(container.firstChild)
            .toHaveClass('UpcomingMeetings');
    });

    it('renders upcoming meetings with partner', () => {
        jest.spyOn(redux, 'useSelector')
            .mockReturnValue({
                meetings: [meetingMatched],
                status: 'OK',
            });

        const { container } = render(<BrowserRouter>{providerElement}</BrowserRouter>);
        const showMeeting = container.getElementsByClassName('UpcomingMeetings-subheader')
            .item(0)?.nextSibling;

        expect(showMeeting)
            .toHaveClass('ShowMeeting');
    });

    it('renders upcoming meetings without partner', () => {
        jest.spyOn(redux, 'useSelector')
            .mockReturnValue({
                meetings: [meetingNotMatched],
                status: 'OK',
            });

        const { container } = render(<BrowserRouter>{providerElement}</BrowserRouter>);
        const showMeeting = container.getElementsByClassName('UpcomingMeetings-subheader')
            .item(1)?.nextSibling;

        expect(showMeeting)
            .toHaveClass('ShowMeeting');
    });

    it('alters history if button clicked', () => {
        const history = createMemoryHistory();
        jest.spyOn(redux, 'useSelector')
            .mockReturnValue({
                meetings: [meetingMatched],
                status: 'OK',
            });

        const renderResult = render(<Router history={history}>{providerElement}</Router>);
        const addMeetingButton = renderResult.container.getElementsByTagName('Button').item(0)!;
        userEvent.click(addMeetingButton);

        expect(history.location.pathname).toEqual('/add+meetings/choose+date');
    });
});
