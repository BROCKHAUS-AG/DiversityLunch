import { render, screen } from '@testing-library/react';
import { FC, useEffect } from 'react';

import { Provider, useDispatch } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import { ProfileOverview } from '../ProfileOverview';
import { APP_STORE } from '../../../store/Store';
import * as fetcher from '../../../utils/fetch.utils';
import { mockedFetchGet } from '../../../__global_test_data__/fetch.test';
import { loadProfile } from '../../../data/profile/profile.actions';
import { profileData } from '../../../__global_test_data__/data.test';

describe('Profile Overview', () => {
    let preFilledProfileDataValues: string[];
    let ProfileLoader: FC;
    let container: HTMLElement;
    let col: HTMLCollection | never[];

    beforeEach(() => {
        jest.spyOn(fetcher, 'authenticatedFetchGet').mockImplementation(mockedFetchGet);

        ProfileLoader = function () {
            const dispatch = useDispatch();
            useEffect(() => { dispatch(loadProfile(profileData[0].id)); }, []);

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
    it('render content when profile data is loaded', async () => {
        const result = await screen.findByAltText('diversity icon');
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
