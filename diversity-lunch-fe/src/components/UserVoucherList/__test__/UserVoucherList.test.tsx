import { render, screen } from '@testing-library/react';
import { VoucherList } from '../VoucherList';
import { UserVoucher } from '../../../types/UserVoucher';

describe('Admin Panel', () => {
    it('should render 3 vouchers in table', async () => {
        const adminVouchers: UserVoucher[] = [
            { voucherCode: 'code1' },
            { voucherCode: 'code2' },
            { voucherCode: 'code3' },
        ];

        render(<VoucherList vouchers={adminVouchers} />);

        expect(screen.getAllByText('code1').length).toBe(1);
        expect(screen.getAllByText('code2').length).toBe(1);
        expect(screen.getAllByText('code3').length).toBe(1);
    });
});
