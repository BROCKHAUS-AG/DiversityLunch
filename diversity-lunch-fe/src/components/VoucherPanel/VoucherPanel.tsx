import React, { useState } from 'react';
import '../../styles/component-styles/voucherpanel/voucherPanel.scss';
import { useSelector } from 'react-redux';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { Button } from '../General/Button/Button';
import { AppStoreState } from '../../store/Store';
import { authenticatedFetchGet, authenticatedFetchPut } from '../../utils/fetch.utils';
import { AccountState, AccountStateOk } from '../../data/account/account-state.type';
import { Account } from '../../types/Account';
import { PopUp } from '../AdminPanel/userAdministration/PopUp';

export const VoucherPanel = () => {
    const [revealed, setRevealed] = useState(false);
    const [voucherCode, setVoucherCode] = useState('empty');
    const [isError, setError] = useState(false);
    const accountState: AccountState = useSelector((store: AppStoreState) => store.account);
    const account: Account = (accountState as AccountStateOk).accountData;

    const extractMeetingIdFromURL = () => {
        const url = String(window.location.pathname);
        return Number(url.split('/').pop());
    };

    const claimVoucher = async (profileId: number, meetingId: number) => {
        try {
            const response = await authenticatedFetchPut(`/api/voucher/claim/${profileId}/${meetingId}`, '');
            return response.ok;
        } catch (error) {
            // error
        }
        return false;
    };

    const getVoucher = async (profileId: number, meetingId: number) => {
        const response = await authenticatedFetchGet(`/api/voucher/get/${profileId}`);
        if (response.ok) {
            return response.json().then((jsonRes) => jsonRes.filter((data: { meetingId: number; }) => data.meetingId === meetingId));
        } throw Error();
    };

    const retrieveVoucherCode = async () => {
        const meetingId = extractMeetingIdFromURL();
        const { profileId } = account;
        if (meetingId > -1) {
            await claimVoucher(profileId, meetingId);
            try {
                const voucher = await getVoucher(profileId, meetingId);
                setVoucherCode(voucher[0].voucherCode);
                setRevealed(true);
            } catch (error) {
                setError(true);
            }
        } else setError(true);
    };

    return (
        <div className="ShowVoucher">
            <DiversityIconContainer title="GUTSCHEIN" />
            {
                revealed
                    ? (
                        <>
                            <p className="ShowVoucher-text">Dein persönlicher Gutschein-Code</p>
                            <div className="ShowVoucher-code">
                                <span className="ShowVoucher-code-text">
                                    {voucherCode}
                                </span>
                            </div>
                        </>
                    )
                    : (
                        <>
                            <p className="ShowVoucher-text">Hier kannst du deinen Gutschein bekommen</p>
                            <Button label="FREISCHALTEN" onClick={() => retrieveVoucherCode()} />
                        </>
                    )
            }
            {
                isError && (
                    <PopUp
                        onButtonClick={() => setError(false)}
                        message="Bei der Abfrage ist ein Fehler aufgetreten"
                        buttonText="Okay"
                    />
                )
            }
        </div>
    );
};