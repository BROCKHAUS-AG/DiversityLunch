import { MouseEvent } from 'react';
import './icon-button.scss';

export interface IconButtonProps {
    iconPath: string,
    text?: string,
    altText: string,
    onClick: (event: MouseEvent<HTMLButtonElement>) => void
}

export const IconButton = ({
    iconPath, text, onClick, altText,
}: IconButtonProps) => {
    const textElement = text ? <span>{text}</span> : null;
    return (
        <button type="button" onClick={onClick}>
            {textElement}
            <img src={iconPath} alt={altText} />
        </button>
    );
};
