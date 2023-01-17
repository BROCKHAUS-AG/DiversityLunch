import { applyMiddleware, combineReducers, createStore } from 'redux';
import thunk from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';
import { authenticationReducer } from './authentication/authentication.reducer';
import { meetingsReducer } from './meeting/meetings.reducer';
import { profileReducer } from './profile/profile.reducer';
import { accountReducer } from './account/account.reducer';
import { accountsReducer } from './accounts/accounts-reducer';
import experienceLevelReducer from './experienceLevel/experience-level.reducer';
import countryReducer from './country/country-reducer';
import dietReducer from './diet/diet-reducer';
import activityReducer from './activity/activity-reducer';
import categoryReducer from './category/category-reducer';
import cultureReducer from './culture/culture-reducer';
import educationReducer from './education/education-reducer';
import genderReducer from './gender/gender-reducer';
import hobbyReducer from './hobby/hobby-reducer';
import industryReducer from './industry/industry-reducer';
import languageReducer from './language/language-reducer';
import projectReducer from './project/project-reducer';
import regionReducer from './region/region-reducer';
import religionReducer from './religion/religion-reducer';
import sexualOrientationReducer from './sexual-orientation/sexual-orientation-reducer';
import socialBackgroundReducer from './social-background/social-background-reducer';
import workExperienceReducer from './work-experience/work-experience-reducer';
import { profilesReducer } from './profiles/profiles-reducer';
import socialBackgroundDiscriminationReducer
    from './social-background-discrimination/social-background-discrimination-reducer';
import wasChangedByAdminGenericReducer from './was-changed-by-admin/was-changed-by-admin-reducer';

const reducers = {
    account: accountReducer,
    accounts: accountsReducer,
    activity: activityReducer,
    authentication: authenticationReducer,
    category: categoryReducer,
    country: countryReducer,
    culture: cultureReducer,
    diet: dietReducer,
    education: educationReducer,
    experienceLevel: experienceLevelReducer,
    gender: genderReducer,
    hobby: hobbyReducer,
    industry: industryReducer,
    language: languageReducer,
    meetings: meetingsReducer,
    profile: profileReducer,
    profiles: profilesReducer,
    project: projectReducer,
    region: regionReducer,
    religion: religionReducer,
    sexualOrientation: sexualOrientationReducer,
    socialBackground: socialBackgroundReducer,
    workExperience: workExperienceReducer,
    socialBackgroundDiscrimination: socialBackgroundDiscriminationReducer,
    wasChangedByAdmin: wasChangedByAdminGenericReducer,
};

const rootReducer = combineReducers(reducers);

const withMiddleWare = applyMiddleware(thunk);
const withDevTools = composeWithDevTools(withMiddleWare);
export const APP_STORE = createStore(rootReducer, withDevTools);

export type AppStoreState = ReturnType<typeof rootReducer>;
