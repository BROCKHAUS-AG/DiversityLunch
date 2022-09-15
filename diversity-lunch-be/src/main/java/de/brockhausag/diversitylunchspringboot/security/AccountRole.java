package de.brockhausag.diversitylunchspringboot.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public enum AccountRole {
    STANDARD(Sets.newHashSet()),
    ADMIN(Sets.newHashSet());

    private final Set<AccountPermission> permissions;
}
