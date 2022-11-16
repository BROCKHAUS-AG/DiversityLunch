import React, { useState } from 'react';
import '../../styles/component-styles/voucherpanel/voucherPanel.scss';
import { useSelector } from 'react-redux';
import { useParams } from 'react-router';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { Button } from '../General/Button/Button';
import { AppStoreState } from '../../store/Store';
import { authenticatedFetchPut } from '../../utils/fetch.utils';
import { AccountState } from '../../data/account/account-state.type';
import { Account } from '../../types/Account';
import { PopUp } from '../AdminPanel/userAdministration/PopUp';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';

export type MeetingParams = {
    id: string;
};

export const VoucherPanel = () => {
    const [revealed, setRevealed] = useState(false);
    const [voucherCode, setVoucherCode] = useState('empty');
    const [isError, setError] = useState(false);
    const accountState: AccountState = useSelector((store: AppStoreState) => store.account);

    const { id } = useParams<MeetingParams>();
    let account: Account;
    if (accountState.status === 'OK') {
        account = accountState.accountData;
    }

    const retrieveVoucherCode = async () => {
        const { profileId } = account;

        const response = await authenticatedFetchPut(`/api/voucher/claim/${profileId}/${id}`, '');
        if (response.ok) {
            const voucher = await response.json();
            setVoucherCode(voucher);
            setRevealed(true);
        } else {
            setError(true);
        }
    };
    const copyToClipboard = async () => {
        await navigator.clipboard.writeText(voucherCode);
    };
    return (
        <section className="view">
            <CloseSiteContainer />
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
                                <button className="copyButton" onClick={copyToClipboard}>Kopieren</button>
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
        </section>

    );
};
