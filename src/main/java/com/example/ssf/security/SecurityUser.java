package com.example.ssf.security;

import com.example.ssf.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SecurityUser implements UserDetails {


    // rapped it over user
    private final User user;

    @Override
    // for the moment  GrantedAuthority is an interface that represents what we call in ss authorities or roles
    // they represent what the user is allowed to do
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //we will skip this today, and return list of  one implementation of grandAuthority
        // later we will enhance it with roles or authorities
        return user.getAuthorities()
                .stream()
                .map(SecurityAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
