import { render } from '@testing-library/react';
import { MeetingTimeButton } from '../MeetingTimeButton';
import { MeetingTimespan } from '../../../AddMeetings/MeetingTimespan';

describe('MeetingTimeButton', () => {
    let buttonActive: JSX.Element;
    let buttonInactive: JSX.Element;

    beforeEach(() => {
        const meetingTimespan: MeetingTimespan = {
            id: 1,
            fromHour: 12,
            fromMinute: 30,
            toHour: 13,
            toMinute: 0,

        };
        buttonActive = (
            <MeetingTimeButton
                onTimeChange={jest.fn()}
                setCurrentTimeLabel={jest.fn()}
                isActive
                timeSpan={meetingTimespan}
            />
        );

        buttonInactive = (
            <MeetingTimeButton
                onTimeChange={jest.fn()}
                setCurrentTimeLabel={jest.fn()}
                isActive={false}
                timeSpan={meetingTimespan}
            />
        );
    });

    it('shows correct meetingtime on activ Button', () => {
        const renderResult = render(buttonActive);
        expect(renderResult.container)
            .toHaveTextContent('12:30 - 13:00');
    });

    it('renders with active state', () => {
        const renderResult = render(buttonActive);
        expect(renderResult.container.firstChild)
            .toHaveClass('MeetingTimeButton-is-active');
    });

    it('shows correct meetingtime on inactiv Button', () => {
        const renderResult = render(buttonInactive);
        expect(renderResult.container)
            .toHaveTextContent('12:30 - 13:00');
    });

    it('renders with inactiv state', () => {
        const renderResult = render(buttonInactive);
        expect(renderResult.container.firstChild)
            .toHaveClass('MeetingTimeButton');
    });
});
