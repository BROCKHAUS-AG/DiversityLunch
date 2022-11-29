import React from 'react';
import { useDispatch } from 'react-redux';
import { loginAfterRedirectAction } from '../../../data/authentication/authentication.actions';
import { OidcAuthData } from '../../../data/authentication/oidc-auth-data.type';
import { LoadingAnimation } from '../../Shared/LoadingAnimation';

type RedirectHandlerProps = {
    authData: OidcAuthData,
};

export const RedirectHandler: React.FC<RedirectHandlerProps> = (props: RedirectHandlerProps) => {
    const { authData } = props;
    const dispatch = useDispatch();

    React.useEffect(() => {
        dispatch(loginAfterRedirectAction(authData));
    }, [dispatch, authData]);

    return (
        <div>
            <LoadingAnimation size="block-app" />
        </div>
    );
};
