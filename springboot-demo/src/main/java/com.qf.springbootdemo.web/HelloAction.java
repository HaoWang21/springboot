package com.qf.springbootdemo.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloAction {
    @RequestMapping("/hello")
    private String index(){
        return "Hello World!";
    }
}
