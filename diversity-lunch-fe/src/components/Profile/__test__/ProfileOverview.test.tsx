import { render, screen } from '@testing-library/react';

import { Provider } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import { ProfileOverview } from '../ProfileOverview';
import { APP_STORE } from '../../../store/Store';
import * as fetcher from '../../../utils/fetch.utils';
import { CATEGORY_ENDPOINT } from '../../../data/category/fetch-category';
import { COUNTRY_ENDPOINT } from '../../../data/country/fetch-country';
import { DIET_ENDPOINT } from '../../../data/diet/fetch-diet';
import { EDUCATION_ENDPOINT } from '../../../data/education/fetch-education';
import { GENDER_ENDPOINT } from '../../../data/gender/fetch-gender';
import { LANGUAGE_ENDPOINT } from '../../../data/language/language-fetch';
import { PROJECT_ENDPOINT } from '../../../data/project/project-fetch';
import { RELIGION_ENDPOINT } from '../../../data/religion/religion-fetch';
import { WORK_EXPERIENCE_ENDPOINT } from '../../../data/work-experience/work-experience-fetch';
import { HOBBY_ENDPOINT } from '../../../data/hobby/fetch-hobby';

const data = {
    id: 4,
    name: 'Horstus',
    email: 'jtonn@brockhaus-ag.de',
    birthYear: 1920,
    originCountry: { id: 9, descriptor: 'Bahamas' },
    diet: { id: 3, descriptor: 'Veganer' },
    education: { id: 2, descriptor: 'Studium' },
    gender: { id: 2, descriptor: 'Weiblich' },
    motherTongue: { id: 9, descriptor: 'Bhojpuri' },
    project: { id: 1, descriptor: 'Externes Projekt' },
    religion: { id: 5, descriptor: 'Buddhismus' },
    workExperience: { id: 2, descriptor: '4-10 Jahre' },
    hobby: { id: 6, descriptor: 'DIY', category: { id: 2, descriptor: 'Kreatives' } },
};

describe('Profile Overview', () => {
    let preFilledProfileDataValues: string[];
    let providerElement: JSX.Element;
    let container: HTMLElement;
    let col: HTMLCollection | never[];

    beforeEach(() => {
        jest.spyOn(fetcher, 'authenticatedFetchGet').mockImplementation(
            async (url: string) => {
                if (url.includes(CATEGORY_ENDPOINT)) {
                    return new Response(JSON.stringify([{ id: 2, descriptor: 'Kreatives' }, { id: 1337, descriptor: 'Sport' }]));
                }
                if (url.includes(COUNTRY_ENDPOINT)) {
                    return new Response(JSON.stringify([{ id: 9, descriptor: 'Bahamas' }, { id: 1337, descriptor: 'Deutschland' }]));
                }
                if (url.includes(DIET_ENDPOINT)) {
                    return new Response(JSON.stringify([{ id: 9, descriptor: 'Fleischesser' }, { id: 1337, descriptor: 'Frutarier' }]));
                }
                if (url.includes(EDUCATION_ENDPOINT)) {
                    return new Response(JSON.stringify([{ id: 9, descriptor: 'Abitur' }, { id: 1337, descriptor: 'Doktor' }]));
                }
                if (url.includes(GENDER_ENDPOINT)) {
                    return new Response(JSON.stringify([{ id: 9, descriptor: 'männlich' }, { id: 1337, descriptor: 'weiblich' }]));
                }
                if (url.includes(LANGUAGE_ENDPOINT)) {
                    return new Response(JSON.stringify([{ id: 9, descriptor: 'Deutsch' }, { id: 1337, descriptor: 'Französisch' }]));
                }
                if (url.includes(PROJECT_ENDPOINT)) {
                    return new Response(JSON.stringify([{ id: 9, descriptor: 'intern' }, { id: 1337, descriptor: 'extern' }]));
                }
                if (url.includes(RELIGION_ENDPOINT)) {
                    return new Response(JSON.stringify([{ id: 9, descriptor: 'katholisch' }, { id: 1337, descriptor: 'evangelisch' }]));
                }
                if (url.includes(WORK_EXPERIENCE_ENDPOINT)) {
                    return new Response(JSON.stringify([{ id: 9, descriptor: '0-4 Jahre' }, { id: 1337, descriptor: '4-10 Jahre' }]));
                }
                if (url.includes(HOBBY_ENDPOINT)) {
                    return new Response(JSON.stringify([{ id: 6, descriptor: 'DIY', category: { id: 2, descriptor: 'Kreatives' } },
                        { id: 7, descriptor: 'Fußball', category: { id: 1, descriptor: 'Sport' } }]));
                }
                return new Response('');
            },
        );

        providerElement = (
            <Provider store={APP_STORE}>
                <ProfileOverview />
            </Provider>
        );

        ({ container } = render(<BrowserRouter>{ providerElement }</BrowserRouter>));
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
    it('form has needed amount of fields', async () => {
        const result = await screen.findByText('Horstus');

        expect(result).toBe(true);
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
