package de.brockhausag.diversitylunchspringboot.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public enum AccountRole {
    STANDARD(Sets.newHashSet(AccountPermission.PROFILE_OPTION_READ)),
    ADMIN(Sets.newHashSet(AccountPermission.PROFILE_OPTION_READ, AccountPermission.PROFILE_OPTION_WRITE)),
    AZURE_ADMIN(Sets.newHashSet(AccountPermission.PROFILE_OPTION_READ, AccountPermission.PROFILE_OPTION_WRITE));

    private final Set<AccountPermission> permissions;
}
