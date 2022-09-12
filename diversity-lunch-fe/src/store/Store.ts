import { createStore, combineReducers, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';
import { authenticationReducer } from '../data/authentication/authentication.reducer';
import { meetingsReducer } from '../data/meeting/meetings.reducer';
import { profileReducer } from '../data/profile/profile.reducer';
import { accountReducer } from '../data/account/account.reducer';
import experienceLevelReducer from '../data/experienceLevel/experience-level.reducer';

const reducers = {
    meetings: meetingsReducer,
    authentication: authenticationReducer,
    profile: profileReducer,
    account: accountReducer,
    experienceLevel: experienceLevelReducer,
};

const rootReducer = combineReducers(reducers);

const withMiddleWare = applyMiddleware(thunk);
const withDevTools = composeWithDevTools(withMiddleWare);
export const APP_STORE = createStore(rootReducer, withDevTools);

export type AppStoreState = ReturnType<typeof rootReducer>;
