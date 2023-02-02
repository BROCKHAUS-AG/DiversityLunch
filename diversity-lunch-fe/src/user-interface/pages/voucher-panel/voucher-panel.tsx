import React, { useEffect, useState } from 'react';
import './voucher-panel.scss';
import { DiversityIcon } from '../../components/diversity-icon/diversity-icon';
import { authenticatedFetchGet } from '../../../utils/fetch.utils';
import { PopUp } from '../../components/pop-up/pop-up';
import { CloseSite } from '../../components/close-site/close-site';
import { GetVoucher } from '../../components/get-voucher/get-voucher';

export const VoucherPanel = () => {
    const [isError, setError] = useState(false);
    const [isVouchersEmpty, setIsVouchersEmpty] = useState(false);

    useEffect(() => {
        checkIfVouchersAvailable();
    }, []);

    const checkIfVouchersAvailable = async () => {
        const responseAmount = await authenticatedFetchGet('/api/voucher/amount?claimed=false');
        setIsVouchersEmpty(await responseAmount.text() === '0');
    };
    return (
        <section className="view">
            <CloseSite />
            <div className="ShowVoucher">
                <DiversityIcon title="GUTSCHEIN" />
                {
                    isVouchersEmpty
                        ? (
                            <p className="ShowVoucher-text">Die Gutscheinaktion ist ausgelaufen</p>
                        )
                        : (
                            <GetVoucher setError={setError} />
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
