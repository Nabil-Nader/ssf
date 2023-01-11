package com.example.ssf.security.providers;

import com.example.ssf.security.authentication.ApiKeyAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@AllArgsConstructor
public class ApiKeyProvider implements AuthenticationProvider {

    private final String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ApiKeyAuthentication apiKeyAuthentication = (ApiKeyAuthentication) authentication;

        if (key.equals(apiKeyAuthentication.getKey())) {
            apiKeyAuthentication.setAuthenticated(true);
            return apiKeyAuthentication;
        } else {
            throw new BadCredentialsException("it is not working");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        // if the ApiKeyAuthentication is the given authentication then it means this provider supports the authentication type
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
