import React from 'react';
import './voucher-list.scss';
import { AdminVoucher } from '../../../model/AdminVoucher';

interface voucherListProps {
    vouchers: AdminVoucher []
}

export const VoucherList: ({ vouchers }: voucherListProps) => any = ({ vouchers }:voucherListProps) => {
    let counter: number;
    counter = 1;
    return (
        <table className="voucherListTable">
            <thead>
                <tr>
                    <th> </th>
                    <th>Gutscheincode</th>
                    <th>E-Mail</th>
                </tr>
            </thead>
            <tbody>
                {vouchers.map((key:any) => (
                    <tr key={counter}>
                        <td>{counter++}</td>
                        <td>{key.voucherCode}</td>
                        <td>{key.email}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
};
