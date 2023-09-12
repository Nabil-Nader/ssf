package com.example.ssf.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/test1")
    @PreAuthorize("hasAuthority('read')")

    public String test1() {

        return "read authority ";
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAuthority('write')")
    public String test2() {

        return "write authority";
    }

    @GetMapping("/test3")
    public String test23() {

        return "no authority";
    }
}
