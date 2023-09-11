package com.example.ssf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/test1")
    public String test1(){

        return "demo 1";
    }
    @GetMapping("/test2")
    public String test2(){

        return "demo 2";
    }
}
