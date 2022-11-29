import { render, screen } from '@testing-library/react';
import React from 'react';
import { act } from 'react-dom/test-utils';
import { PopUp } from './pop-up';

describe('Pop Up', () => {
    let buttonFunction: jest.Mock<any, any>;
    const buttonText = 'Okay';
    const popUpMessage = 'test';
    beforeEach(() => {
        buttonFunction = jest.fn();
        render(
            <PopUp onButtonClick={buttonFunction} buttonText={buttonText} message={popUpMessage} />,
        );
    });
    it('should display the button', () => {
        const button = screen.getByText(buttonText);
        expect(button).toBeInTheDocument();
    });

    it('should execute Function when its button is clicked', () => {
        const button = screen.getByText(buttonText);
        act(() => {
            button.click();
        });
        expect(buttonFunction).toBeCalledTimes(1);
    });

    it('should display a message', () => {
        const message = screen.getByText(popUpMessage);
        expect(message).toBeInTheDocument();
    });
});
