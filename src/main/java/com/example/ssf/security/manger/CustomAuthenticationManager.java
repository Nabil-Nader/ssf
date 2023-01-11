package com.example.ssf.security.manger;

import com.example.ssf.security.providers.ApiKeyProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
//it is recommended to make it a security authentication manager, that is why we implement it
public class CustomAuthenticationManager implements AuthenticationManager {


    private final String key;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       var provider = new ApiKeyProvider(key);
       if(provider.supports(authentication.getClass())) {
            return provider.authenticate(authentication);
       }
       return authentication;
    }
}
