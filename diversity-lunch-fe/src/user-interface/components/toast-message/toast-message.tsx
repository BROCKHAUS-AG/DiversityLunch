import React from 'react';
import './toast-message.scss';

type ToastMessageProps = {
    message: string;
}

export const ToastMessage:React.FC<ToastMessageProps> = (props:ToastMessageProps) => {
    const { message } = props;

    return (
        <div className="ToastMessage">
            <h1>{message}</h1>
        </div>
    );
};
