import React, { useState } from 'react';
import '../../pages/voucher-panel/voucher-panel.scss';
import { useParams } from 'react-router';
import { useSelector } from 'react-redux';
import { Button } from '../button/button';
import { authenticatedFetchPut } from '../../../utils/fetch.utils';
import { Account } from '../../../model/Account';
import { AccountState } from '../../../data/account/account-state.type';
import { AppStoreState } from '../../../data/app-store';

type MeetingParams = {
    meetingId: string;
};

type GetVoucherProps = {
    setError: (_:boolean)=>void,
}

export const GetVoucher = ({ setError } : GetVoucherProps) => {
    const [isRevealed, setIsRevealed] = useState(false);
    const [voucherCode, setVoucherCode] = useState('empty');
    const accountState: AccountState = useSelector((store: AppStoreState) => store.account);

    const { meetingId } = useParams<MeetingParams>();
    let account: Account;
    if (accountState.status === 'OK') {
        account = accountState.accountData;
    }

    const retrieveVoucherCode = async () => {
        const { profileId } = account;

        const response = await authenticatedFetchPut(`/api/voucher/claim/${profileId}/${meetingId}`, '');

        if (response.ok) {
            const voucher = await response.json();
            setVoucherCode(voucher.voucherCode);
            setIsRevealed(true);
        } else {
            setError(true);
        }
    };
    const copyToClipboard = async () => {
        await navigator.clipboard.writeText(voucherCode);
    };

    return (

        isRevealed
            ? (
                <>
                    <p className="ShowVoucher-text">Dein persönlicher Gutschein-Code</p>
                    <div className="ShowVoucher-code">
                        <span className="ShowVoucher-code-text">
                            {voucherCode}
                        </span>
                    </div>
                    <button type="button" className="copyButton" onClick={copyToClipboard}>Kopieren</button>
                    <p className="lieferando-text">
                        Bestelle jetzt dein Essen bei:&nbsp;
                        <a
                            className="lieferando-link"
                            href="src/user-interface/components/get-voucher/get-voucher.tsx"
                            target="_blank"
                            rel="noopener noreferrer"
                        >
                            lieferando.de
                        </a>
                    </p>
                </>
            )
            : (
                <>
                    <p className="ShowVoucher-text">Hier kannst du deinen Gutschein bekommen</p>
                    <Button label="FREISCHALTEN" onClick={() => retrieveVoucherCode()} />
                </>
            )

    );
};
