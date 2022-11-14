import React, { FC, useEffect, useState } from 'react';

interface voucherListProps {
    vouchers: []
}

export const VoucherList: ({ vouchers }: voucherListProps) => any = ({ vouchers }:voucherListProps) => {
    return vouchers.map((val) => {
        console.log(val);
        return <div>{val.uuid}</div>;
    });
};



