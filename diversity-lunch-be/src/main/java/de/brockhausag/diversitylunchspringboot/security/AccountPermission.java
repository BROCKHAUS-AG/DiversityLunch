package de.brockhausag.diversitylunchspringboot.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AccountPermission {
    PROFILE_OPTION_READ("profile_option_read:read"),
    PROFILE_OPTION_WRITE("profile_option_read:write");

    private final String name;
}
