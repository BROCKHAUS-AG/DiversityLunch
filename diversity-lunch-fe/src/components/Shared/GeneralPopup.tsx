import React from 'react';

// Styles
import '../../styles/component-styles/upcomming-meetings/deleteMeetingPopUp.scss';

interface GeneralPopupProps {
    msg :string;
}

export const GeneralPopup = (props : GeneralPopupProps) => {
    const {
        msg,
    } = props;

    return (
        <div className="DeleteMeetingPopUp">
            <div className="DeleteMeetingPopUp-container">
                <h6 className="DeleteMeetingPopUp-text">
                    {msg}
                </h6>
            </div>
        </div>
    );
};
