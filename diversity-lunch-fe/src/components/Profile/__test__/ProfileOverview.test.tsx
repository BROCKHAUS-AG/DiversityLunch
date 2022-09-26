import { render, screen } from '@testing-library/react';

import { Provider } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import * as redux from 'react-redux';
import { CombinedState } from '@reduxjs/toolkit/dist/query/core/apiState';
import { ProfileOverview } from '../ProfileOverview';
import { APP_STORE, AppStoreState } from '../../../store/Store';
import { Profile } from '../../../model/Profile';
import * as fetcher from '../../../utils/fetch.utils';

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

const fetchProfileData = async () => new Response(JSON.stringify(data));

describe('Profile Overview', () => {
    let profileData: Profile;
    let preFilledProfileDataValues: string[];
    let providerElement: JSX.Element;
    let formFields: string[];
    let container: HTMLElement;
    let col: HTMLCollection | never[];

    beforeEach(() => {
        // jest.spyOn(fetcher, 'authenticatedFetchGet').mockReturnValue(fetchProfileData());
        // jest.spyOn(redux, 'useSelector').mockReturnValue({ status: 'OK', profileData: data });
        jest.spyOn(redux, 'useSelector').mockImplementation((store: any) => {
            const profile = (state: AppStoreState) => state.profile;
            if (store === profile) {
                return { status: 'OK', profileData: data };
            }
            return null;
        });

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
