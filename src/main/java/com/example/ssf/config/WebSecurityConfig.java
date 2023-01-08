package com.example.ssf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig {

    /*
        here we are using the default configuration,which is implemented by spring boot
        webSecurityConfigureAdapter is npt used anymore , we will do it in a new way => it is still a very easy way
        using security filter chain bean which will allow to set a different authentication provider not a manager
        tha manger => is always the same
        authentication provider -> is what you will implement your self -> this will be later

     */

    /*
        this is a default lesson 4 will be custom authentication
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


}
