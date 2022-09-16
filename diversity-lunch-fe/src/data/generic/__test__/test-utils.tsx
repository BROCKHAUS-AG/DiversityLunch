// This type interface extends the default options for render from RTL, as well
// as allows the user to specify other things such as initialState, store.
// import { render, RenderOptions } from '@testing-library/react';
// import { Provider } from 'react-redux';
// import { configureStore } from '@reduxjs/toolkit';
// import React, { PropsWithChildren } from 'react';
// import type { PreloadedState } from '@reduxjs/toolkit';
// import countryReducer from '../../country/country-reducer';
//
// import { APP_STORE, AppStoreState } from '../../../store/Store';
//
// interface ExtendedRenderOptions extends Omit<RenderOptions, 'queries'> {
//     preloadedState?: PreloadedState<AppStoreState>
//     store?: typeof APP_STORE
// }
//
// export function renderWithProviders(
//     ui: React.ReactElement,
//     {
//         preloadedState = {},
//         // Automatically create a store instance if no store was passed in
//         store = configureStore({ reducer: { country: countryReducer }, preloadedState }),
//         ...renderOptions
//     }: ExtendedRenderOptions = {},
// ) {
//     const Wrapper = ({ children }: PropsWithChildren<{}>): JSX.Element => <Provider store={store}>{children}</Provider>;
//
//     // Return an object with the store and all of RTL's query functions
//     return { store, ...render(ui, { wrapper: Wrapper, ...renderOptions }) };
// }
