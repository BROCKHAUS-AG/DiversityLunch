import { render } from '@testing-library/react';
import { Provider } from 'react-redux';
import { ShowMeeting } from '../ShowMeeting';
import { Meeting } from '../../../types/Meeting';
import { APP_STORE } from '../../../store/Store';

describe('Show Meeting', () => {
    let defaultMeeting: Meeting;
    let providerElement: JSX.Element;

    beforeEach(() => {
        defaultMeeting = {
            id: '1',
            fromDateTime: new Date(2022, 1, 25, 12, 30, 0, 0),
            partnerName: '',

        };

        providerElement = (
            <Provider store={APP_STORE}>
                <ShowMeeting meeting={defaultMeeting} />
            </Provider>
        );
    });

    it('renders without crashing', () => {
        const { container } = render(providerElement);

        expect(container.firstChild)
            .toHaveClass('ShowMeeting');
    });

    it('shows the date and timespan', () => {
        const renderResult = render(providerElement);

        const date = renderResult.container.getElementsByClassName('ShowMeeting-text').item(0)?.innerHTML;
        const time = renderResult.container.getElementsByClassName('ShowMeeting-text').item(1)?.innerHTML;

        expect(date).toEqual('25.2.2022');
        expect(time).toEqual('12:30 - 13:00');
    });
});
