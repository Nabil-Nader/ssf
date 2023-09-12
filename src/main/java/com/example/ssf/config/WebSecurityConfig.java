package com.example.ssf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        return http.httpBasic()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .build();
//        Matcher method + authorization rule
//        1- Which matcher you should use and how
//        2- how to apply different authorization rule
    }

    @Bean
    public UserDetailsService userDetailsService() {
//        class InMemoryUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService
//        interface UserDetailsManager extends UserDetailsService
        InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();

        UserDetails u1 = getUserDetails("billy", "pass", "read");


        UserDetails u2 = getUserDetails("nader", "pass", "write");

        uds.createUser(u1);
        uds.createUser(u2);
        return uds;
    }

    private UserDetails getUserDetails(String name, String password, String authority) {
        return User.withUsername(name)
                .password(passwordEncoder().encode(password))
                .authorities(authority)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
