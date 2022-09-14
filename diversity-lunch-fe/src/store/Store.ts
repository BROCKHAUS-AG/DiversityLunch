import { createStore, combineReducers, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';
import { authenticationReducer } from '../data/authentication/authentication.reducer';
import { meetingsReducer } from '../data/meeting/meetings.reducer';
import { profileReducer } from '../data/profile/profile.reducer';
import { accountReducer } from '../data/account/account.reducer';
import experienceLevelReducer from '../data/experienceLevel/experience-level.reducer';
import countryReducer from '../data/country/country-reducer';
import dietReducer from '../data/diet/diet-reducer';
import activityReducer from '../data/activity/activity-reducer';
import categoryReducer from '../data/category/category-reducer';
import cultureReducer from '../data/culture/culture-reducer';
import educationReducer from '../data/education/education-reducer';
import genderReducer from '../data/gender/gender-reducer';
import hobbyReducer from '../data/hobby/hobby-reducer';

const reducers = {
    meetings: meetingsReducer,
    authentication: authenticationReducer,
    profile: profileReducer,
    account: accountReducer,
    experienceLevel: experienceLevelReducer,
    country: countryReducer,
    diet: dietReducer,
    activity: activityReducer,
    category: categoryReducer,
    culture: cultureReducer,
    education: educationReducer,
    gender: genderReducer,
    hobby: hobbyReducer,
};

const rootReducer = combineReducers(reducers);

const withMiddleWare = applyMiddleware(thunk);
const withDevTools = composeWithDevTools(withMiddleWare);
export const APP_STORE = createStore(rootReducer, withDevTools);

export type AppStoreState = ReturnType<typeof rootReducer>;
