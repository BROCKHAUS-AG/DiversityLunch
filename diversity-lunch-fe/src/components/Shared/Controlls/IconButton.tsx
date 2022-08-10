import {MouseEvent} from 'react';


export interface IconButtonProps {
    iconPath: string,
    text: string,
    onClick: (event: MouseEvent<HTMLButtonElement>) => void
}

export const IconButton = ({iconPath, text, onClick}: IconButtonProps) => {


    return (
        <button onClick={onClick}>
            <p>{text}</p>
            <img src={iconPath}/>
        </button>
    )
};

