import React, { useState } from 'react';
import '../../styles/component-styles/voucherpanel/voucherPanel.scss';
import { useSelector } from 'react-redux';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { Button } from '../General/Button/Button';
import { AppStoreState } from '../../store/Store';
import { authenticatedFetchGet, authenticatedFetchPut } from '../../utils/fetch.utils';
import { AccountState } from '../../data/account/account-state.type';

export const VoucherPanel = () => {
    const [revealed, setRevealed] = useState(false);
    const [voucherCode, setVoucherCode] = useState('empty');

    const accountState: AccountState = useSelector((store: AppStoreState) => store.account);

    const extractMeetingIdFromURL = () => {
        const url = String(window.location.pathname);
        return Number(url.substring(14, 100));
    };

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
        } catch (error) {
            return false;
        }
    };

    const retrieveVoucherCode = () => {
        const meetingId = extractMeetingIdFromURL();
        const profileId = 0;
        // TODO: Replace profileId with actual profileID
        if (claimVoucher(1, meetingId)) {
            getVoucher(1, 3).then((voucher) => {
                setVoucherCode(voucher[0].voucherCode);
                setRevealed(true);
            });
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
                                { voucherCode }
                            </span>
                        </div>
                    )
                    : <Button label="FREISCHALTEN" onClick={() => retrieveVoucherCode()} />
            }
        </div>
    );
};
