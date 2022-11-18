import React from 'react';

// Styles
import '../../styles/component-styles/popup/GeneralPopUp.scss';

interface GeneralPopupProps {
    msg :string;
}

export const GeneralPopup = (props : GeneralPopupProps) => {
    const {
        msg,
    } = props;

    return (
        <div className="GeneralPopUp">
            <div className="GeneralPopUp-container">
                <p className="GeneralPopUp-text">
                    <b>{msg}</b>
                </p>
            </div>
        </div>
    );
};
