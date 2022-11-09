import React, { useState } from 'react';
import '../../styles/component-styles/voucherpanel/voucherPanel.scss';
import { useSelector } from 'react-redux';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { Button } from '../General/Button/Button';
import { AppStoreState } from '../../store/Store';
import { authenticatedFetchGet, authenticatedFetchPut } from '../../utils/fetch.utils';
import { AccountState, AccountStateOk } from '../../data/account/account-state.type';
import { Account } from '../../types/Account';

export const VoucherPanel = () => {
    const [revealed, setRevealed] = useState(false);
    const [voucherCode, setVoucherCode] = useState('empty');
    const accountState: AccountState = useSelector((store: AppStoreState) => store.account);
    const account: Account = (accountState as AccountStateOk).accountData;

    const extractMeetingIdFromURL = () => {
        const url = String(window.location.pathname);
        return Number(url.split('/').pop());
    };

    const claimVoucher = (profileId: number, meetingId: number) => async () => {
        try {
            const response = await authenticatedFetchPut(`/api/voucher/claim/${profileId}/${meetingId}`, '');
            return response.ok === true;
        } catch (error) {
            // error
        }
        return false;
    };

    const getVoucher = async (profileId: number, meetingId: number) => {
        try {
            const response = await authenticatedFetchGet(`/api/voucher/get/${profileId}`);
            if (response.ok) {
                return response.json().then((jsonRes) => jsonRes.filter((data: { meetingId: number; }) => data.meetingId === meetingId));
            }
        } catch (error) {
            // error
        }
        return false;
    };

    const retrieveVoucherCode = () => {
        const meetingId = extractMeetingIdFromURL();
        const { profileId } = account;
        if (meetingId > -1 && claimVoucher(profileId, meetingId)) {
            getVoucher(profileId, meetingId).then((voucher) => {
                setVoucherCode(voucher[0].voucherCode);
                setRevealed(true);
            });
            return;
        }
        // TBD: Hier sollte eine vernünftige Fehlermeldung hin
        console.log('Fehlermeldung');
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
