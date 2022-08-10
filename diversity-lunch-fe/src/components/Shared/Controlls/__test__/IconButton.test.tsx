import {render} from '@testing-library/react';
import {IconButton, IconButtonProps} from "../IconButton";
import userEvent from "@testing-library/user-event";

describe('IconButton', () => {
    let container : HTMLElement;
    let counter = 0;
    let buttonProps : IconButtonProps;


    beforeEach(() => {
       counter = 0;

       buttonProps = {
           text : "Hallo",
           altText: "Hey",
           iconPath : "test",
           onClick : () => {counter++;}
       };
       ({container} = render(<IconButton text={buttonProps.text} altText={buttonProps.altText} iconPath={buttonProps.iconPath} onClick={buttonProps.onClick}/>))
    });

    it('should render component without crashing', () => {
        expect(container.firstChild).toBeInTheDocument();
    });

    it('should render the correct text', () => {
        expect(container.firstElementChild!.querySelector("p")!.innerHTML).toEqual(buttonProps.text);
    });

    it('should assign given altText to the img element', () => {
        expect(container.firstElementChild!.querySelector("img")!.alt).toEqual(buttonProps.altText);
    });

    it('should render the correct icon', () => {
        expect(container.firstElementChild!.querySelector("img")!.src).toEqual("http://localhost/" + buttonProps.iconPath);
    });

    it('should execute given function', () => {
        userEvent.click(container.firstElementChild!);
        expect(counter).toEqual(1);
    });
});
