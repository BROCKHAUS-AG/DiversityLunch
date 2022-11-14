import React from 'react';

interface voucherListProps {
    vouchers: []
}


export const VoucherList: ({ vouchers }: voucherListProps) => any = ({ vouchers }:voucherListProps) => {
    console.log(vouchers);
    return vouchers.map((key:any, val:any) => {
        return (
            <div>{key.voucherCode}</div>

        );
    });
};



