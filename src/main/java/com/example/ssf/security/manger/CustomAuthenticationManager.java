package com.example.ssf.security.manger;

import com.example.ssf.security.providers.CustomAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {


    /*
        what will actually happen, is that from the filter 'CustomAuthenticationFilter extend OncePreRequestFilter '
        where we have 4 steps, after complete 4 step we go back to the mange
        but to have the manger here we  need to have a provider
     */

    private final CustomAuthenticationProvider provider;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(provider.supports(authentication.getClass())){
            return provider.authenticate(authentication);
        }
        throw new BadCredentialsException("Oh No!");
     }
}
