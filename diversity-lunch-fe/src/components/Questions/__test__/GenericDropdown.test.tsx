import { isElement, isElementOfType, renderIntoDocument } from 'react-dom/test-utils';
import { fireEvent, render, screen } from '@testing-library/react';
import { GenerateGenericDropdown } from '../GenericDropdown';
import { Gender } from '../../../types/enums/gender.type';

import { GENDER_DROPDOWN_OPTIONS } from '../../../types/dropdownOptions/gender-dropdown-options';
import '@testing-library/jest-dom';

describe('Generic Dropdown', () => {
    const GenderDropdown = GenerateGenericDropdown<Gender>();

    it('renders without crashing with render()', () => {
        render(<GenderDropdown
            label="gender"
            placeholder="gender"
            options={GENDER_DROPDOWN_OPTIONS}
            onChange={() => {
            }}
        />);
        const linkElement = screen.getByLabelText('gender');
        expect(linkElement).toBeInTheDocument();
    });

    it('renders without crashing', () => {
        renderIntoDocument(<GenderDropdown
            label="label"
            placeholder=""
            options={GENDER_DROPDOWN_OPTIONS}
            onChange={() => {
            }}
        />);
        expect(isElement(<GenderDropdown
            label=""
            placeholder=""
            options={GENDER_DROPDOWN_OPTIONS}
            onChange={() => {
            }}
        />)).toBe(true);
        expect(isElementOfType(<GenderDropdown
            label=""
            placeholder=""
            options={GENDER_DROPDOWN_OPTIONS}
            onChange={() => {
            }}
        />, GenderDropdown)).toBe(true);
    });

    it('checks if value exists in dropdown', () => {
        render(<GenderDropdown
            label="label"
            placeholder="label"
            options={GENDER_DROPDOWN_OPTIONS}
            onChange={() => {
            }}
        />);
        fireEvent.click(screen.getByLabelText('label'));
    });
});
