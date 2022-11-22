import { render, screen } from '@testing-library/react';
import { VoucherList } from '../VoucherList';
import { AdminVoucher } from '../../../types/AdminVoucher';

describe('Admin Panel', () => {
    it('should render 3 vouchers in table', async () => {
        const adminVouchers: AdminVoucher[] = [
            { voucherCode: 'code1', email: 'beispiel1@email.de' },
            { voucherCode: 'code2', email: 'beispiel2@email.de' },
            { voucherCode: 'code3', email: 'beispiel3@email.de' },
        ];

        render(<VoucherList vouchers={adminVouchers} />);

        expect(screen.getAllByText('code1').length).toBe(1);
        expect(screen.getAllByText('code2').length).toBe(1);
        expect(screen.getAllByText('code3').length).toBe(1);
    });
});
