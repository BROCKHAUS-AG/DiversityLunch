package de.brockhausag.diversitylunchspringboot.account.model;

import de.brockhausag.diversitylunchspringboot.security.AccountRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    private Long id;

    private Long profileId;

    private AccountRole role;
}
