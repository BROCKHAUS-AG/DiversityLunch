package de.brockhausag.diversitylunchspringboot.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AccountPermission {
    PROFILE_OPTION_READ("profile_option:read"),
    PROFILE_OPTION_WRITE("profile_option:write");

    private final String name;
}
