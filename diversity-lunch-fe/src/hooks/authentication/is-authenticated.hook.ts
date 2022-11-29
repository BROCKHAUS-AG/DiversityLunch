import { useSelector } from 'react-redux';
import { AppStoreState } from '../../data/app-store';
import { AuthenticationState } from '../../data/authentication/authentication-state.type';

export const useIsAuthenticated = () => {
    const authState = useSelector<AppStoreState, AuthenticationState>(
        (state) => state.authentication,
    );

    return authState.status === 'OK';
};
