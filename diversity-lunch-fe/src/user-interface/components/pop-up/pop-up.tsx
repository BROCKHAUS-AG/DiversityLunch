import React from 'react';

import './pop-up.scss';

interface PopUpProps {
    onButtonClick: () => void;
    message: string;
    buttonText: string;
}

export const PopUp = ({
    onButtonClick,
    message,
    buttonText,
}: PopUpProps) => (
    <div className="PopUp">
        <div className="PopUp-container">
            <span className="PopUp-text">
                {message}
            </span>
            <div className="PopUp-button-container">
                <button
                    type="button"
                    className="PopUp-okay-button"
                    onClick={() => onButtonClick()}
                >
                    {buttonText}
                </button>
            </div>
        </div>
    </div>
);
