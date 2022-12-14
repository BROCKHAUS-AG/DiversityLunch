/* eslint-disable camelcase */
import { useSelector } from 'react-redux';
import { AppStoreState } from '../../data/app-store';

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
                    unique_name, // WARNING: the unique_name field is not guaranteed to be the email address of the user.
                    // If this is the case, you have permission to the email payload claim to be read from the id token
                    // https://learn.microsoft.com/en-us/azure/active-directory/develop/id-tokens
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
