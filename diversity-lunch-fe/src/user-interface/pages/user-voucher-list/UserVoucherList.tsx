import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import '../../../styles/component-styles/UserVoucherList/UserVoucherList.scss';
import { AccountState, AccountStateOk } from '../../../data/account/account-state.type';
import { AppStoreState } from '../../../data/app-store';
import { Account } from '../../../model/Account';
import { authenticatedFetchGet } from '../../../utils/fetch.utils';
import { DiversityIcon } from '../../components/diversity-icon/diversity-icon';
import { CloseSite } from '../../components/close-site/close-site';
import { UserVoucher } from '../../../model/UserVoucher';

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
            <CloseSite />
            <DiversityIcon title="GUTSCHEINE" />
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
