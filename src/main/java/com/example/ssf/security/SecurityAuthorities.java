package com.example.ssf.security;

import com.example.ssf.entity.Authorities;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityAuthorities implements GrantedAuthority {

    private final Authorities authorities;
    @Override
    public String getAuthority() {
        return authorities.getName();
    }
}
