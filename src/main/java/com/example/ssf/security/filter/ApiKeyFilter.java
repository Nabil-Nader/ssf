package com.example.ssf.security.filter;

import com.example.ssf.security.authentication.ApiKeyAuthentication;
import com.example.ssf.security.manger.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {


    private final String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // check on the key
        CustomAuthenticationManager manager = new CustomAuthenticationManager(key);

        var requestKey = request.getHeader("x-api-key"); //x-api-key is the stander

        if ("null".equals(requestKey) || requestKey == null) {
            filterChain.doFilter(request, response);
        }

        var auth = new ApiKeyAuthentication(requestKey);


        try {
            var authentication = manager.authenticate(auth);
            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            }
        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        filterChain.doFilter(request, response);
    }
}
