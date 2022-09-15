package de.brockhausag.diversitylunchspringboot.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum AccountRole {
    STANDARD(Sets.newHashSet(AccountPermission.PROFILE_OPTION_READ)),
    ADMIN(Sets.newHashSet(AccountPermission.PROFILE_OPTION_READ, AccountPermission.PROFILE_OPTION_WRITE));

    private final Set<AccountPermission> permissions;

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        final Set<SimpleGrantedAuthority> permissions = this.getPermissions()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.getName())).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
