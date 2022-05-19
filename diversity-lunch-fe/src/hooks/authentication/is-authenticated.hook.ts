import { useSelector } from 'react-redux';
import { AppStoreState } from '../../store/Store';
import { AuthenticationState } from '../../Redux/authentication/authentication-state.type';

export const useIsAuthenticated = () => {
  const authState = useSelector<AppStoreState, AuthenticationState>(
    (state) => state.authentication,
  );

  return authState.status === 'OK';
};
