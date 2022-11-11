import { createStore, combineReducers, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';
import { accountReducer } from '../../../data/account/account.reducer';
import categoryReducer from '../../../data/category/category-reducer';
import { authenticationReducer } from '../../../data/authentication/authentication.reducer';
import experienceLevelReducer from '../../../data/experienceLevel/experience-level.reducer';
import religionReducer from '../../../data/religion/religion-reducer';
import sexualOrientationReducer from '../../../data/sexual-orientation/sexual-orientation-reducer';
import regionReducer from '../../../data/region/region-reducer';
import industryReducer from '../../../data/industry/industry-reducer';
import { meetingsReducer } from '../../../data/meeting/meetings.reducer';
import { profileReducer } from '../../../data/profile/profile.reducer';
import socialBackgroundReducer from '../../../data/social-background/social-background-reducer';
import projectReducer from '../../../data/project/project-reducer';
import workExperienceReducer from '../../../data/work-experience/work-experience-reducer';
import activityReducer from '../../../data/activity/activity-reducer';
import genderReducer from '../../../data/gender/gender-reducer';
import cultureReducer from '../../../data/culture/culture-reducer';
import dietReducer from '../../../data/diet/diet-reducer';
import hobbyReducer from '../../../data/hobby/hobby-reducer';
import educationReducer from '../../../data/education/education-reducer';
import languageReducer from '../../../data/language/language-reducer';
import countryReducer from '../../../data/country/country-reducer';

const reducers = {
    account: accountReducer,
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
    project: projectReducer,
    region: regionReducer,
    religion: religionReducer,
    sexualOrientation: sexualOrientationReducer,
    socialBackground: socialBackgroundReducer,
    workExperience: workExperienceReducer,
};

const rootReducer = combineReducers(reducers);

const withMiddleWare = applyMiddleware(thunk);
const withDevTools = composeWithDevTools(withMiddleWare);
export const TEST_APP_STORE = createStore(rootReducer, withDevTools);

export type TestAppStoreState = ReturnType<typeof rootReducer>;
