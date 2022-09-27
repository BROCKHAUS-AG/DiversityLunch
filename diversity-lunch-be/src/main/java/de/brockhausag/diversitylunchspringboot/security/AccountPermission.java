package de.brockhausag.diversitylunchspringboot.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AccountPermission {
    PROFILE_OPTION_READ("profile_option:read"),
    PROFILE_OPTION_WRITE("profile_option:write"),
    ADMIN_ROLE_ASSIGN("admin_role:assign"),
    ADMIN_ROLE_WITHDRAW("admin_role:with_draw");

    private final String name;
}
