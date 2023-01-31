import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import './App.scss';
import { GenericErrorPage } from './generic-error-page/generic-error-page';
import { AppHeader } from '../components/header/header';
import { Navbar } from '../components/navbar/Navbar';
import { CloseSite } from '../components/close-site/close-site';
import { BodyContainer } from '../components/body-container/body-container';

export const App = () => (
    <div className="App">
        <div className="Screen">
            <BrowserRouter>
                <AppHeader />
                <Navbar />
                <div className="toggleStyle">
                    <CloseSite />
                </div>
                <BodyContainer
                    render={() => (
                        <GenericErrorPage
                            errorMessage="ERROR 404 - THIS SITE DOES NOT EXIST"
                        />
                    )}
                />
            </BrowserRouter>
        </div>
    </div>
);

export default App;
