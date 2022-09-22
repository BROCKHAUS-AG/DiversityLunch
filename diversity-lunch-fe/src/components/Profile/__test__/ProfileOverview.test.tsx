import { render } from '@testing-library/react';

import { Provider } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import { ProfileOverview } from '../ProfileOverview';
import { APP_STORE } from '../../../store/Store';
import { Profile } from '../../../model/Profile';

describe('Profile Overview', () => {
    let profileData: Profile;
    let preFilledProfileDataValues: string[];
    let providerElement: JSX.Element;
    let formFields: string[];
    let container: HTMLElement;
    let col: HTMLCollection | never[];

    beforeEach(() => {
        profileData = {
            id: 42,
            name: 'Martin Mustermann',
            email: 'martin@mustermann.com',
            birthYear: 1993,
            project: { id: 7, descriptor: 'sonstiges' },
            gender: { id: 8, descriptor: 'MALE' },
            originCountry: { id: 9, descriptor: 'Deutschland' },
            motherTongue: { id: 10, descriptor: 'deutsch' },
            religion: { id: 11, descriptor: 'Christentum' },
            hobby: { id: 12, descriptor: 'Gaming' },
            education: { id: 13, descriptor: 'APPRENTICESHIP' },
            workExperience: { id: 14, descriptor: 'LOW_EXPERIENCE' },
            diet: { id: 15, descriptor: 'MEAT' },
        };

        providerElement = (
            <Provider store={APP_STORE}>
                <ProfileOverview profileData={profileData} />
            </Provider>
        );
        formFields = [
            'GEBURTSJAHR',
            'GESCHLECHT',
            'KUNDE',
            'HERKUNFTSLAND',
            'MUTTERSPRACHE',
            'HOBBY',
            'RELIGION',
            'BERUFSERFAHRUNG (JAHRE)',
            'BILDUNGSWEG',
            'ERNÄHRUNGSWEISE',
        ];

        preFilledProfileDataValues = [
            profileData.birthYear.toString(),
            'Männlich',
            'Sonstiges',
            'Deutschland',
            'Deutsch',
            'Gaming',
            'Christentum',
            '0-3 Jahre',
            'Ausbildung',
            'Fleischesser',
        ];

        ({ container } = render(<BrowserRouter>{providerElement}</BrowserRouter>));
        col = container.children.item(0)?.children.item(2)?.children || [];
    });
    it('render component without crashing', () => {
        expect(container.firstChild!.firstChild).toHaveClass('Profile-logo-container');
    });
    it('profile edit form diversity logo space', () => {
        const logoElement = container.children.item(0)?.children.item(0);
        expect(logoElement).not.toBe(null);
        expect(logoElement).toHaveClass('Profile-logo-container');
    });
    it('form has needed fields', () => {
        const formFieldLabels : Array<string> = Array.from(col).map((e) => e
            .getElementsByClassName('Characteristic-attribute')
            .item(0)?.innerHTML)
            .filter((e):e is string => e !== undefined);
        expect(formFieldLabels.length).toBeGreaterThan(0);
        expect(formFieldLabels).toEqual(formFields);
    });
    it('check correct value is shown', () => {
        const formFieldValues = [];
        for (let i = 0; i < col.length; i += 1) {
            const currentValue = col[i]?.getElementsByClassName('Characteristic-value').item(0)?.innerHTML;
            if (currentValue !== undefined) {
                formFieldValues.push(currentValue);
            }
        }
        expect(formFieldValues.length).toBeGreaterThan(0);
        expect(formFieldValues).toEqual(preFilledProfileDataValues);
    });
    it('field name and email are not displayed', () => {
        const formFieldLabels: Array<string> = [];
        for (let i = 0; i < col.length; i += 1) {
            formFieldLabels.push(col[i]?.getElementsByClassName('Characteristic-attribute').item(0)?.innerHTML.toString() || '');
        }
        expect(formFieldLabels).not.toContain('NAME');
        expect(formFieldLabels).not.toContain('E-MAIL');
    });
});
