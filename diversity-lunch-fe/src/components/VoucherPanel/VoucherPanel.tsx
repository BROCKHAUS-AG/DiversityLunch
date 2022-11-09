import React, { useState } from 'react';
import '../../styles/component-styles/voucherpanel/voucherPanel.scss';
import { useSelector } from 'react-redux';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { Button } from '../General/Button/Button';
import { AppStoreState } from '../../store/Store';
import { ProfileState } from '../../data/profile/profile-state.type';
import { authenticatedFetchGet, authenticatedFetchPut } from '../../utils/fetch.utils';

export const VoucherPanel = () => {
    const [revealed, setRevealed] = useState(false);
    const profileState: ProfileState = useSelector((store: AppStoreState) => store.profile);

    let abc = String(window.location.pathname);
    abc = abc.substring(14, 100);

    const claimVoucher = (profileId: number, meetingId: number) => async () => {
        try {
            const response = await authenticatedFetchPut(`/api/voucher/claim/${profileId}/${meetingId}`, '');

            if (response.ok) {
                return true;
            }
            return false;
        } catch (error) {
            //
        }
    };

    const getVoucher = async (profileId: number, meetingId: number) => {
        try {
            const response = await authenticatedFetchGet(`/api/voucher/get/${profileId}`);

            if (response.ok) {
                return response.json().then((jsonRes) => jsonRes.filter((data: { meetingId: number; }) => data.meetingId === meetingId));
            }
            console.log('Fehler!!!!!');
        } catch (error) {
            return false;
        }
    };

    const claim = () => {
        if (claimVoucher(1, 3)) {
            getVoucher(1, 3).then((key) => {
                console.log(key);
            });
            setRevealed(true);
        }
    };

    return (
        <div className="ShowVoucher">
            <DiversityIconContainer title="GUTSCHEIN" />
            <p className="ShowVoucher-text">Hier kannst du deinen Gutschein bekommen</p>
            {
                revealed
                    ? (
                        <div className="ShowVoucher-code">
                            <span className="ShowVoucher-code-text">
                                { abc }
                            </span>
                        </div>
                    )
                    : <Button label="FREISCHALTEN" onClick={() => claim()} />
            }
        </div>
    );
};
