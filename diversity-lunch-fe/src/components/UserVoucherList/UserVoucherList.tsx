import React, { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { AccountState, AccountStateOk } from '../../data/account/account-state.type';
import { AppStoreState } from '../../store/Store';
import { Account } from '../../types/Account';
import { authenticatedFetchGet } from '../../utils/fetch.utils';

export const UserVoucherList = () => {
    const accountState: AccountState = useSelector((store: AppStoreState) => store.account);
    const account: Account = (accountState as AccountStateOk).accountData;
    const voucherList:string[] = [];

    const FillVoucherList = async (profileId: number) => {
        const response = await authenticatedFetchGet(`/api/voucher/get/${profileId}`);
        if (response.ok) {
            response.json().then(((jsonRes) => jsonRes.forEach((element: any, i: any) => voucherList.push(element[i].voucherCode))));
        } throw Error();
    };

    useEffect(() => { FillVoucherList(account.profileId); }, []);

    return (
        <ul>
            {voucherList.map((voucher) => (<li>{voucher}</li>))}
        </ul>
    );
};
