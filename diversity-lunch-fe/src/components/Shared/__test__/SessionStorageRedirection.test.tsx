import { render } from '@testing-library/react';
import { BrowserRouter, Switch } from 'react-router-dom';
import {
    clearStoredPath,
    getStoredPath,
    SessionStorageRedirection,
    STORED_PATH_KEY,
    storePath,
} from '../SessionStorageRedirection';

describe('SessionStorageRedirection', () => {
    beforeEach(() => {
        sessionStorage.clear();
    });

    it('storePath should store path in local storage', () => {
        const path = '/i/am/a/happy/path';

        storePath(path);

        expect(sessionStorage.getItem(STORED_PATH_KEY))
            .toStrictEqual(path);
    });

    it('getPath should retrieve the data from storePath ', () => {
        const path = '/i/am/a/happy/path';

        storePath(path);
        const result = getStoredPath();

        expect(result)
            .toStrictEqual(path);
    });

    it('getPath should return null if storePath wasn\'t called', () => {
        const result = getStoredPath();

        expect(result)
            .toStrictEqual(null);
    });

    it('after clearPath was called, getPath should return null even if storedPath was called prior', () => {
        const path = '/i/am/a/happy/path';

        storePath(path);
        clearStoredPath();
        const result = getStoredPath();

        expect(result)
            .toStrictEqual(null);
    });

    it('SessionStorageRedirection consumes the stored Path on unmount', () => {
        const path = '/i/am/a/happy/path';

        storePath(path);
        const { unmount } = render((
          <BrowserRouter>
              <Switch>
                  <SessionStorageRedirection />
                </Switch>
            </BrowserRouter>
        ));
        unmount();
        const result = getStoredPath();

        expect(result)
            .toStrictEqual(null);
    });

    it('SessionStorageRedirection doesn\'t consume the stored Path if not unmounted', () => {
        const path = '/i/am/a/happy/path';

        storePath(path);
        render((
          <BrowserRouter>
              <Switch>
                  <SessionStorageRedirection />
                </Switch>
            </BrowserRouter>
        ));
        const result = getStoredPath();

        expect(result)
            .toStrictEqual(path);
    });

    it('SessionStorageRedirection routes to the correct path', () => {
        const path = '/i/am/a/happy/path';

        storePath(path);
        render((
          <BrowserRouter>
              <Switch>
                  <SessionStorageRedirection />
                </Switch>
            </BrowserRouter>
        ));
        const result = window.location.pathname;

        expect(result)
            .toStrictEqual(path);
    });

    it('should route to the components default path, if no path was stored and no defaultPath was given', () => {
        render((
          <BrowserRouter>
              <Switch>
                  <SessionStorageRedirection />
                </Switch>
            </BrowserRouter>
        ));

        const result = window.location.pathname;

        expect(result)
            .toStrictEqual(SessionStorageRedirection.defaultProps.defaultPath);
    });

    it('should route to the given defaultPath, if no path was stored and defaultPath was given', () => {
        const path = '/another/path/to/be/tested';

        render((
          <BrowserRouter>
              <Switch>
                  <SessionStorageRedirection defaultPath={path} />
                </Switch>
            </BrowserRouter>
        ));
        const result = window.location.pathname;

        expect(result)
            .toStrictEqual(path);
    });
});
