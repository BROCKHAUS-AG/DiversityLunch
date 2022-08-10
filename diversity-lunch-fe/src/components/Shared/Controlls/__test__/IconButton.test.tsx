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
           iconPath : "test",
           onClick : () => {counter++;}
       };
       ({container} = render(<IconButton text={buttonProps.text} iconPath={buttonProps.iconPath} onClick={buttonProps.onClick}/>))
    });

    it('should render component without crashing', () => {
        expect(container.firstChild).toBeInTheDocument();
    });

    it('should render the correct text', () => {
        expect(container.firstElementChild!.querySelectorAll("p").item(0).innerHTML).toEqual(buttonProps.text);
    });

    it('should render the correct icon', () => {
        expect(container.firstElementChild!.querySelectorAll("img").item(0).src).toEqual("http://localhost/" + buttonProps.iconPath);
    });

    it('should execute given function', () => {
        userEvent.click(container.firstElementChild!);
        expect(counter).toEqual(1);
    });
});
