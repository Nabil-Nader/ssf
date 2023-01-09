package com.example.ssf.security.filter;

import com.example.ssf.security.authentication.CustomAuthentication;
import com.example.ssf.security.manger.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Component
public class CustomerAuthenticationFilter extends OncePerRequestFilter {

    /* when I relay on the filter interface,'implements Filter from import javax.servlet.*; ' I don't necessarily know that the filter will be only called once
        one thing you should remember is that in the filter chain there is no guarantee
        that the filter will be only once called.
        if I want a filter to be once Called we need to extend OncePreRequestFilter -> it has a doFilterInternal we need to override
     */

    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. create an authentication object which is not yet authenticated
        // 2. delegate the authentication object to the manager
        // 3. get back the authentication from the manager
        // 4. if the object is authenticated then send request to the next filter in the chain
        String key = String.valueOf(request.getHeader("key")); // Step 1
        CustomAuthentication ca = new CustomAuthentication(false, key); // step 1

        var a = customAuthenticationManager.authenticate(ca);//step 2,3

        if (a.isAuthenticated()) {
            //step 4
            SecurityContextHolder.getContext().setAuthentication(a);// this line is very important,
            // at the very end we will store authentication somewhere, so that the authorization mechanism can check out who authenticated,
            // and then they will grant different access
            filterChain.doFilter(request, response); // only when authentication worked
        }
    }
}
