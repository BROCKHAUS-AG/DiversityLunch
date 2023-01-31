import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import './App.scss';
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
                <BodyContainer />
            </BrowserRouter>
        </div>
    </div>
);

export default App;
