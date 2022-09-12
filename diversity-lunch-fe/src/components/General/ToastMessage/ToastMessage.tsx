import React from 'react';
import '../../../styles/component-styles/general/toastMessage/toastMessage.scss';

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
