import { render, screen } from '@testing-library/react';
import { FC, useEffect } from 'react';
import { Provider } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import { APP_STORE } from '../../../../store/Store';
import { ProfileForm } from '../profile-form';
import { profileData } from '../../../../__global_test_data__/data.test';
import {isUpdatedProfile, isValidProfile} from '../../../../utils/validators/profile-validator';
import {Profile} from "../../../../model/Profile";

describe('Profile form', () => {
    const isUpdated = (updatedProfile: Partial<Profile>) => isValidProfile(updatedProfile) && isUpdatedProfile(updatedProfile as Profile, profile);
    const renderContainer = (component : typeof ProfileForm) =>
        render (<BrowserRouter>
            <Provider store={APP_STORE}>
                {component}
            </Provider>
        </BrowserRouter>)


    beforeEach(() => {

    });

    it('', () => {
        renderContainer(<ProfileForm initialProfile={profileData[0]} checkValidity={() => false} onSubmit={() => {}}>)
    });
});
