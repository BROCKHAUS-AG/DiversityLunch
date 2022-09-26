import { render, screen } from '@testing-library/react';
import { FC, useEffect } from 'react';

import { Provider, useDispatch } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import { ProfileOverview } from '../ProfileOverview';
import { APP_STORE } from '../../../store/Store';
import * as fetcher from '../../../utils/fetch.utils';
import { mockedFetchGet } from '../../../__global_test_data__/fetch.test';
import { loadProfile } from '../../../data/profile/profile.actions';

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
    let ProfileLoader: FC;
    let container: HTMLElement;
    let col: HTMLCollection | never[];

    beforeEach(() => {
        jest.spyOn(fetcher, 'authenticatedFetchGet').mockImplementation(mockedFetchGet);

        ProfileLoader = function () {
            const dispatch = useDispatch();
            useEffect(() => { dispatch(loadProfile(data.id)); }, []);

            return (
                <ProfileOverview />
            );
        };

        ({ container } = render(
            <BrowserRouter>
                <Provider store={APP_STORE}>
                    <ProfileLoader />
                </Provider>
            </BrowserRouter>,
        ));
        col = container.children.item(0)?.children.item(2)?.children || [];
    });
    it('render component without crashing', async () => {
        const result = await screen.findByRole('img');
        expect(result).toBeInTheDocument();
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
