import React from 'react';
import './button.scss';

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

        <div className="bagBtn">
            <div className="CustomButton-container">
                {/* eslint-disable-next-line react/button-has-type */}
                <button className="CustomButton" type={type} onClick={handleClick} disabled={disabled}>{label}</button>
            </div>
        </div>
    );
};

Button.defaultProps = {
    disabled: false,
    onClick: () => {
    },
    type: 'button',
};
