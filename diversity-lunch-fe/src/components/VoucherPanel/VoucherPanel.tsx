import React, { useState } from 'react';
import '../../styles/component-styles/voucherpanel/voucherPanel.scss';
import { useSelector } from 'react-redux';
import { Dispatch } from '@reduxjs/toolkit';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { Button } from '../General/Button/Button';
import { AppStoreState } from '../../store/Store';
import { ProfileState } from '../../data/profile/profile-state.type';
import { authenticatedFetchGet, authenticatedFetchPut } from '../../utils/fetch.utils';
import { globalErrorSlice } from '../../data/error/global-error-slice';
import { Account } from '../../types/Account';
import { accountsAction } from '../../data/accounts/accounts-reducer';

export const VoucherPanel = () => {
    const [revealed, setRevealed] = useState(false);
    const profileState: ProfileState = useSelector((store: AppStoreState) => store.profile);
    let abc = String(window.location.pathname);
    abc = abc.substring(14, 100);
    function claimVoucher(profileId: number, meetingId: number) {
        return async () => {
            try {
                const response = await authenticatedFetchPut(`/api/voucher/claim/${profileId}/${meetingId}`, '');

                if (response.ok) {
                    //
                } else {
                    //
                }
            } catch (error) {
                //
            }
        };
    }

    function getVoucher(profileId: number) {
        return async () => {
            try {
                const response = await authenticatedFetchGet(`/api/voucher/get/${profileId}`);

                if (response.ok) {
                    //
                } else {
                    //
                }
            } catch (error) {
                //
            }
        };
    }

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
                    : <Button label="FREISCHALTEN" onClick={() => setRevealed(true)} />
            }
        </div>
    );
};
