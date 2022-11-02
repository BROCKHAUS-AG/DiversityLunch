import React, { useEffect, useState } from 'react';

interface PopUpProps {
    onOkay: () => void;
    message: string;
    buttonText: string;
    millisecondsToAutoClose: number|undefined;
    visible: boolean;
}

export const PopUp = ({
    onOkay,
    message,
    buttonText,
    millisecondsToAutoClose,
    visible,
}: PopUpProps) => {
    const [isVisible, setIsVisible] = useState(visible);
    useEffect(() => {
        if (millisecondsToAutoClose === undefined) {
            setInterval(() => { setIsVisible(false); }, millisecondsToAutoClose);
        }
    }, []);

    return (
        <div className="UploadSuccessPopUp">
            <div className="UploadSuccessPopUp-container">
                <h6 className="UploadSuccessPopUp-text">
                    Der Upload war erfolgreich!
                </h6>
                <div className="UploadSuccessPopUp-button-container">
                    <button
                        type="button"
                        className="UploadSuccessPopUp-okay-button"
                        onClick={() => onOkay()}
                    >
                        Okay
                    </button>
                </div>
            </div>
        </div>
    );
};
