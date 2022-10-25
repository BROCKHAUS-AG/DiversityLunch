package de.brockhausag.diversitylunchspringboot.voucher.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class VoucherDTO {
    private UUID uuid;
    private String voucherCode;
    private long profileId;
    private long meetingId;

}
