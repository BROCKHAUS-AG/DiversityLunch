/* eslint-disable camelcase */
import { useSelector } from 'react-redux';
import { AppStoreState } from '../../store/Store';

type UserInfo = {
  firstName: string,
  lastName: string,
  fullName: string,
  email: string,
}

/**
 * Will default all names to empty string if unknown
 */
export const useGetUserInformation = () => {
    const {
        firstName,
        lastName,
        fullName,
        email,
    } = useSelector<AppStoreState, UserInfo>(({ authentication }) => {
        if (authentication.status === 'OK') {
            const {
                accessTokenPayload: {
                    given_name,
                    family_name,
                    unique_name,
                },
            } = authentication;
            const name = `${given_name} ${family_name}`;
            return {
                firstName: given_name,
                lastName: family_name,
                fullName: name,
                email: unique_name,
            };
        }
        return {
            firstName: '',
            lastName: '',
            fullName: '',
            email: '',
        };
    });
    return {
        firstName,
        lastName,
        fullName,
        email,
    };
};
