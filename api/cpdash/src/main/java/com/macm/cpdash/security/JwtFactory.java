package com.macm.cpdash.security;



import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.macm.cpdash.domain.dto.User;
import com.macm.cpdash.domain.entities.AuthorityEntity;

/**
 * create a JWTUser from a User instance
 */
public class JwtFactory {

    private JwtFactory() {
    }

    /**
     * creating the Costume user details from the current user instance
     *
     * @param currentUser instance of {User} representing the current connected user
     */
    public static UserDetails createUser(User currentUser) {
        return new JwtUser(currentUser.getId(),
                currentUser.getUsername(),
                currentUser.getEmail(),
                currentUser.getPassword(),
                getAuthorities(currentUser.getAuthorities()),
                currentUser.getEnabled(),
                currentUser.getLastPasswordResetDate());
    }

    /**
     * returns a set of authorities mapped by name
     *
     * @param authorities the user authorities
     */
    private static Set<? extends GrantedAuthority> getAuthorities(Set<AuthorityEntity> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toSet());
    }
}
