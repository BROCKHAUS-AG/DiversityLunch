package de.brockhausag.diversitylunchspringboot.voucher.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoucherClaimDto {
    private String voucherCode;
    private boolean claimedNewVoucher;
}

