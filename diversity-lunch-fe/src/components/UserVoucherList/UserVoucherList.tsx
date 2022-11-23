import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import '../../styles/component-styles/UserVoucherList/UserVoucherList.scss';
import { AccountState, AccountStateOk } from '../../data/account/account-state.type';
import { AppStoreState } from '../../store/Store';
import { Account } from '../../types/Account';
import { authenticatedFetchGet } from '../../utils/fetch.utils';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import { UserVoucher } from '../../types/UserVoucher';

export const UserVoucherList = () => {
    const accountState: AccountState = useSelector((store: AppStoreState) => store.account);
    const account: Account = (accountState as AccountStateOk).accountData;
    const [voucherList, setVoucherList] = useState<UserVoucher[]>([]);

    const getVoucherList = async (profileId: number) => {
        const response = await authenticatedFetchGet(`/api/voucher/all/${profileId}`);
        if (response.ok) {
            const jsonRes = await response.json();
            setVoucherList(jsonRes);
        }
    };

    useEffect(() => {
        if (account !== undefined) getVoucherList(account.profileId);
    }, [account]);

    return (
        <div className="ShowVouchers">
            <CloseSiteContainer />
            <DiversityIconContainer title="GUTSCHEIN-ÜBERSICHT" />
            <p className="ShowVoucher-text">Hier findest du deine persönlichen Gutscheine</p>
            <ul className="ShowVoucherList">
                {voucherList.map((voucher: UserVoucher) => (
                    <li className="voucherItem" key={voucherList.indexOf(voucher)}>
                        {voucher.voucherCode}
                    </li>
                ))}
            </ul>
        </div>
    );
};
