import { createStore, combineReducers, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import { composeWithDevTools } from 'redux-devtools-extension';
import { authenticationReducer } from '../Redux/authentication/authentication.reducer';
import { meetingsReducer } from '../Redux/meetings/meetings.reducer';
import { profileReducer } from '../Redux/profile/profile.reducer';
import { accountReducer } from '../Redux/account/account.reducer';

const reducers = {
  meetings: meetingsReducer,
  authentication: authenticationReducer,
  profile: profileReducer,
  account: accountReducer,
};

const rootReducer = combineReducers(reducers);

const withMiddleWare = applyMiddleware(thunk);
const withDevTools = composeWithDevTools(withMiddleWare);
export const APP_STORE = createStore(rootReducer, withDevTools);

export type AppStoreState = ReturnType<typeof rootReducer>;
