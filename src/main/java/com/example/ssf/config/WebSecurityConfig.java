package com.example.ssf.config;

import com.example.ssf.security.filter.ApiKeyFilter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
public class WebSecurityConfig {

 /*
    since WebSecurityConfigureAdaptor is deprecated, we can no longer use
    we can not Override AuthenticationManger  method as a bean and inject it with other

    in previous we could get authentication manger and put it into the context then we could have injected it anyWhere
    or in any Filter
  */

    /*
        as for now we will use security web filter chain
     */

    /*
        what is exactly HttpSecurity -> is object currently provided //todo: look for it
     */

    @Value("$(the.secret)")
    private String key;

    // we want our API to be accessible through http basic and with secret key
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        return http.httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated() // without this line by API can send a request and receive a 200
                .and()
                .build();
//        Matcher method + authorization rule
//        1- Which matcher you should use and how
//        2- how to apply different authorization rule
    }

    @Bean
    public UserDetailsService userDetailsService (){
        var uds = new InMemoryUserDetailsManager();

        var user  = User.withUsername("billy")
                .password(passwordEncoder().encode("pass"))
                .authorities("read")
                .build();

        uds.createUser(user);
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
