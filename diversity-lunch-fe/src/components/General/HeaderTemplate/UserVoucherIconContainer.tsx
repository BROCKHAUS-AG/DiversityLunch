import { Link } from 'react-router-dom';
import React from 'react';
import iconAdmin from '../../../resources/icons/user_voucher_icon.svg';

export const UserVoucherIconContainer = () => (
    <div className="IconHeader">
        <Link to="/myVouchers">
            <img alt="user voucher icon" className="IconHeader-user-voucher-icon" src={iconAdmin} />
        </Link>
    </div>
);
