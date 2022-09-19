import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { Dropdown } from '../Dropdown';

describe('DropDown', () => {
    let counter : number;
    let container : HTMLElement;

    beforeEach(() => {
        counter = 0;
        const mockData = [{
            id: 1,
            descriptor: 'blub',
        }, {
            id: 2,
            descriptor: 'Jop',
        }];

        ({ container } = render(<Dropdown options={mockData} text="Genau das" onChange={() => counter++} />));
    });

    it('renders without crashing', () => {
        expect(screen.getByLabelText('Genau das')).toBeInTheDocument();
    });

    it('renders the correct amount of options', () => {
        expect(screen.getAllByRole('option').length).toBe(3);
    });

    it('should use an empty option element as default', () => {
        expect(container.querySelectorAll('option')[0]!.selected).toBeTruthy();
        expect(container.querySelectorAll('option')[0].value).toBe('');
    });

    it('should execute the given onChange function correctly', () => {
        userEvent.selectOptions(
            screen.getByRole('combobox'),
            screen.getByText('blub'),
        );
        expect(container.querySelectorAll('option')[1].selected).toBe(true);
        expect(counter).toBe(1);
    });

    it('is visible', () => {
        expect(screen.getByText('Genau das')).toBeVisible();
    });
});
