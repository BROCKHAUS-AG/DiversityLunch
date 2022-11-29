import { render, waitFor } from '@testing-library/react';
import * as redux from 'react-redux';
import userEvent from '@testing-library/user-event';
import { Router } from 'react-router-dom';
import { createMemoryHistory } from 'history';
import { APP_STORE } from '../../../data/app-store';
import { SelectMeetingData } from '../SelectMeetingData';
import * as fetcher from '../../../utils/fetch.utils';

describe('Show alreadybooked-page', () => {
    let providerElement: JSX.Element;
    beforeEach(() => {
        providerElement = (
            <redux.Provider store={APP_STORE}>
                <SelectMeetingData onAddMeeting={jest.fn()} />
            </redux.Provider>
        );
    });
    it('changes history to meetingAlreadyBooked if meeting already exists', async () => {
        const history = createMemoryHistory();
        const { container } = render(<Router history={history}>{providerElement}</Router>);
        const spyPost = jest.spyOn(fetcher, 'authenticatedFetchPost').mockReturnValue(meetingAlreadyExistsResponse());
        jest.spyOn(fetcher, 'authenticatedFetchGet').mockReturnValue(meetingAddedSuccessResponse());
        const addMeetingButton = container.getElementsByClassName('SelectMeetingData').item(0)?.getElementsByClassName('CustomButton').item(0);

        userEvent.click(addMeetingButton!);

        expect(spyPost).toBeCalled();
        await waitFor(() => expect(history.location.pathname).toEqual('/meetingAlreadyBooked'));
    });

    it('changes history to /add+meetings/meeting+overview', async () => {
        const history = createMemoryHistory();
        const { container } = render(<Router history={history}>{providerElement}</Router>);
        const spyPost = jest.spyOn(fetcher, 'authenticatedFetchPost').mockReturnValue(meetingAddedSuccessResponse());
        jest.spyOn(fetcher, 'authenticatedFetchGet').mockReturnValue(meetingAddedSuccessResponse());
        const addMeetingButton = container.getElementsByClassName('SelectMeetingData').item(0)?.getElementsByClassName('CustomButton').item(0);
        userEvent.click(addMeetingButton!);

        expect(spyPost).toBeCalled();
        await waitFor(() => expect(history.location.pathname).toEqual('/add+meetings/meeting+overview'));
    });
});
const meetingAlreadyExistsResponse = async () => new Response(null, { status: 409 });
const meetingAddedSuccessResponse = async () => new Response(JSON.stringify([{
    id: '1',
    hasPartner: true,
    partnerName: 'Jam',
}]), { status: 200 });
