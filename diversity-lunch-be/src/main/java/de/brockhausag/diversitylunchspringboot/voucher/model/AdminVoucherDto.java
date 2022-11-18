package de.brockhausag.diversitylunchspringboot.voucher.model;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.maven.model.Profile;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class AdminVoucherDto {
    private String voucherCode;
    private String email;
}