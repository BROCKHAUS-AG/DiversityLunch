import React, { useEffect, useState } from 'react';
import '../../styles/component-styles/voucherpanel/voucherPanel.scss';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { authenticatedFetchGet } from '../../utils/fetch.utils';
import { PopUp } from '../components/pop-up/pop-up';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { GetVoucher } from './GetVoucher';

export const VoucherPanel = () => {
    const [isError, setError] = useState(false);
    const [vouchersEmpty, setVouchersEmpty] = useState(false);

    useEffect(() => {
        checkIfVouchersAvailable();
    }, []);

    const checkIfVouchersAvailable = async () => {
        const responseAmount = await authenticatedFetchGet('/api/voucher/amount?claimed=false');
        setVouchersEmpty(await responseAmount.text() === '0');
    };
    return (
        <section className="view">
            <CloseSiteContainer />
            <div className="ShowVoucher">
                <DiversityIconContainer title="GUTSCHEIN" />
                {
                    vouchersEmpty
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
