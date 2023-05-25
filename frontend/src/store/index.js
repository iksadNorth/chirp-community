import { configureStore } from '@reduxjs/toolkit';
import logger from 'redux-logger';

import rootReducer from "./reducers";
import { loadStates, saveStates } from './util';

const store = configureStore({
    reducer: rootReducer,
    preloadedState: loadStates(),
    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(logger),
});

store.subscribe(() => {
    saveStates(store.getState());
});

export default store;
