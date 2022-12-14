import { useSelector } from 'react-redux';
import { AppStoreState } from '../../data/app-store';

type AccountInfo = {
  id: number,
  profileId: number,
}
export const useGetAccountInformation = () => {
    const {
        id,
        profileId,
    } = useSelector<AppStoreState, AccountInfo>(({ account }) => {
        if (account.status === 'OK') {
            const {
                accountData: {
                    // eslint-disable-next-line @typescript-eslint/no-shadow
                    id,
                    // eslint-disable-next-line @typescript-eslint/no-shadow
                    profileId,
                },
            } = account;
            return {
                id,
                profileId,
            };
        }
        return {
            id: 0,
            profileId: 0,
        };
    });
    return {
        id,
        profileId,
    };
};
