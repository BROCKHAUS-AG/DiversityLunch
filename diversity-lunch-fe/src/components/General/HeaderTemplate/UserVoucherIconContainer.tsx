import { Link } from 'react-router-dom';
import React from 'react';
import iconVoucher from '../../../resources/icons/icon-voucher-percent.svg';

export const UserVoucherIconContainer = () => (
    <div className="IconHeader">
        <Link to="/myVouchers">
            <img alt="icon voucher percent" className="IconHeader-icon-voucher-percent" src={iconVoucher} />
        </Link>
    </div>
);
