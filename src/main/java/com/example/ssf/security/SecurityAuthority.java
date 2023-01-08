package com.example.ssf.security;

import com.example.ssf.entity.Authority;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
//we could implement this in Authority entity, but we need entity to a singleton

   private final Authority authority;
    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
