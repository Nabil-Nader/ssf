package com.example.ssf.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo(){

        var u = SecurityContextHolder.getContext().getAuthentication();
        log.info(String.format("Here we will show the security authority :%s",u.getAuthorities().toString()));
        return "demo";
    }
}
