import { createStore, combineReducers, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';
import { authenticationReducer } from '../data/authentication/authentication.reducer';
import { meetingsReducer } from '../data/meeting/meetings.reducer';
import { profileReducer } from '../data/profile/profile.reducer';
import { accountReducer } from '../data/account/account.reducer';
import { accountsReducer } from '../data/accounts/accounts-reducer';
import experienceLevelReducer from '../data/experienceLevel/experience-level.reducer';
import countryReducer from '../data/country/country-reducer';
import dietReducer from '../data/diet/diet-reducer';
import activityReducer from '../data/activity/activity-reducer';
import categoryReducer from '../data/category/category-reducer';
import cultureReducer from '../data/culture/culture-reducer';
import educationReducer from '../data/education/education-reducer';
import genderReducer from '../data/gender/gender-reducer';
import hobbyReducer from '../data/hobby/hobby-reducer';
import industryReducer from '../data/industry/industry-reducer';
import languageReducer from '../data/language/language-reducer';
import projectReducer from '../data/project/project-reducer';
import regionReducer from '../data/region/region-reducer';
import religionReducer from '../data/religion/religion-reducer';
import sexualOrientationReducer from '../data/sexual-orientation/sexual-orientation-reducer';
import socialBackgroundReducer from '../data/social-background/social-background-reducer';
import workExperienceReducer from '../data/work-experience/work-experience-reducer';
import { profilesReducer } from '../data/profiles/profiles-reducer';
import discriminationReducer from '../data/discrimination/discrimination-reducer';

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
    discrimination: discriminationReducer,
};

const rootReducer = combineReducers(reducers);

const withMiddleWare = applyMiddleware(thunk);
const withDevTools = composeWithDevTools(withMiddleWare);
export const APP_STORE = createStore(rootReducer, withDevTools);

export type AppStoreState = ReturnType<typeof rootReducer>;
