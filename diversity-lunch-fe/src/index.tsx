import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import reportWebVitals from './reportWebVitals';
import './resources/fonts/GilroyBlack.otf';
import { App } from './user-interface/pages/app';

// Styles
import './global-styles/index.scss';

// redux
import { APP_STORE } from './data/app-store';
import { AuthenticationCheck } from './user-interface/components/authentication-check/authentication-check';

// SET TRUE FOR OFFLINE USE DEBUG
const isOffline = false;

export const OffMode = () => (isOffline ? <App /> : <AuthenticationCheck />);

ReactDOM.render(
    <React.StrictMode>
        <Provider store={APP_STORE}>
            <OffMode />
        </Provider>
    </React.StrictMode>,
    document.getElementById('root'),
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
