import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import '../../styles/component-styles/UserVoucherList/UserVoucherList.scss';
import { AccountState, AccountStateOk } from '../../data/account/account-state.type';
import { AppStoreState } from '../../store/Store';
import { Account } from '../../types/Account';
import { authenticatedFetchGet } from '../../utils/fetch.utils';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';

export const UserVoucherList = () => {
    const accountState: AccountState = useSelector((store: AppStoreState) => store.account);
    const account: Account = (accountState as AccountStateOk).accountData;
    const [voucherList, setVoucherList] = useState([]);

    const getVoucherList = async (profileId: number) => {
        const response = await authenticatedFetchGet(`/api/voucher/all/${profileId}`);
        if (response.ok) {
            setVoucherList(await response.json());
        }
    };

    useEffect(() => { getVoucherList(account.profileId); }, []);

    return (
        <div className="ShowVouchers">
            <CloseSiteContainer />
            <DiversityIconContainer title="GUTSCHEIN-ÜBERSICHT" />
            <p className="ShowVoucher-text">Hier findest du deine persönlichen Gutscheine</p>
            <ul className="ShowVoucherList">
                {voucherList.map((voucher: any) => (<li className="voucherItem" key={voucher.uuid}>{voucher.voucherCode}</li>))}
            </ul>
        </div>
    );
};
