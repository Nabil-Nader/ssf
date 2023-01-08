package com.example.ssf.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo(){
        var u = SecurityContextHolder.getContext().getAuthentication();
        u.getAuthorities().forEach(System.out::println);

        /*
            u contain principal => user data -> name, id , password, authorities
             we still have  the password, which is not expected, we expected t the filter to erase the password before
             it went to security context this is might be another 'bug' ->

             solution "we took the password from the db and this should have been hashed "

             whenever we implement the custom filter recommendation is do not keep the password

            u contain authorities -> contain list of  entities of  authorities
         */
        return "demo";
    }
}
