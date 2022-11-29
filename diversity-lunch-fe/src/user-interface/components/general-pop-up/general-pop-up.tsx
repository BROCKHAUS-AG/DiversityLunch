import React from 'react';

// Styles
import './general-pop-up.scss';

interface GeneralPopupProps {
    msg :string;
}

export const GeneralPopUp = (props : GeneralPopupProps) => {
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
