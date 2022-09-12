import React from 'react';
import '../../../styles/component-styles/general/customButton/customButton.scss';

type ButtonProps = {
    label: string;
    onClick?: () => void;
    disabled?: boolean;
    type?: 'submit' | 'reset' | 'button';
};

export const Button: React.FC<ButtonProps> = (props: ButtonProps) => {
    const {
        disabled,
        label,
        onClick,
        type,
    } = props;

    const handleClick = React.useCallback(() => {
        if (onClick) {
            onClick();
        }
    }, [onClick]);

    return (

      <div className="CustomButton-container">
          {/* eslint-disable-next-line react/button-has-type */}
          <button className="CustomButton" type={type} onClick={handleClick} disabled={disabled}>{label}</button>
        </div>
    );
};

Button.defaultProps = {
    disabled: false,
    onClick: () => {},
    type: 'button',
};
