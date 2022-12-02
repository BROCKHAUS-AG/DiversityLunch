package de.brockhausag.diversitylunchspringboot.voucher.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AdminVoucherDto {
    private String voucherCode;
    private String email;
}
