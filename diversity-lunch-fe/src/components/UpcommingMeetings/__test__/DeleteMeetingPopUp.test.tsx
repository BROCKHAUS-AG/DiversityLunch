import { render } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { Meeting } from '../../../types/Meeting';
import { DeleteMeetingPopUp } from '../DeleteMeetingPopUp';
import Mock = jest.Mock;

describe('GeneralMeetingPopUp', () => {
    let defaultMeeting: Meeting;
    let popup: JSX.Element;
    let deleteFunction: Mock;
    let cancelFunction: Mock;

    beforeEach(() => {
        defaultMeeting = {
            id: '1',
            fromDateTime: new Date(2022, 1, 25, 12, 30, 0, 0),
            partnerName: 'Name',

        };

        deleteFunction = jest.fn();
        cancelFunction = jest.fn();
        popup = (
            <DeleteMeetingPopUp
                meeting={defaultMeeting}
                onDelete={deleteFunction}
                onCancel={cancelFunction}
            />
        );
    });
    it('shows DeleteMeetingPopUp', () => {
        const renderResult = render(popup);
        expect(renderResult.container.firstChild)
            .toHaveClass('GeneralPopUp');
    });
    it('calls the delete function', () => {
        const renderResult = render(popup);
        const deleteButton = renderResult.container.getElementsByClassName('CustomButton-container')
            .item(0)
            ?.firstElementChild;
        userEvent.click(deleteButton!);

        expect(deleteFunction)
            .toBeCalled();
    });
    it('calls the cancel function', () => {
        const renderResult = render(popup);
        const cancelButton = renderResult.container.getElementsByClassName('DeleteMeetingPopUp-delete-button')
            .item(0);
        userEvent.click(cancelButton!);

        expect(cancelFunction)
            .toBeCalled();
    });
});
